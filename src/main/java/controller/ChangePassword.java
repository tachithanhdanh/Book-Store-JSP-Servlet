package controller;

import dao.CustomerDAO;
import dao.CustomerDAOImpl;
import dao.CustomerAuthDAO;
import dao.CustomerAuthDAOImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Customer;
import model.CustomerAuth;
import org.apache.commons.lang3.RandomStringUtils;
import utils.HashGeneratorUtils;
import utils.RegexCheckerUtils;

import java.io.IOException;

@WebServlet(name = "ChangePassWord", value = "/change-password-servlet")
public class ChangePassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("user/change-password.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String newPasswordRetype = request.getParameter("newPasswordRetype");
        boolean logOutDevices = "true".equals(request.getParameter("logOutDevices"));
        HttpSession session = request.getSession();
        String url = "user/change-password.jsp"; // default url
        Customer customer = (Customer) session.getAttribute("loggedCustomer");

        // check if the user is logged in
        if (customer == null) {
            session.setAttribute("error", "You need to login to change password!");
            response.sendRedirect("login.jsp");
            return;
        }

        String currentPasswordHash = HashGeneratorUtils.generateSHA256DefaultSalt(currentPassword);

        // get the customer from the database because the password might be changed by another device
        // and the session object might not be updated
        Customer customerFromDB = new CustomerDAOImpl().selectById(customer);

        // update the session object
        session.setAttribute("loggedCustomer", customerFromDB);

        // get the password hash from the database
        String databasePasswordHash = customerFromDB.getPassword();

        boolean hasError = false;
        if (!newPassword.equals(newPasswordRetype)) {
            session.setAttribute("errorNewPasswordRetype", "Password does not match! Enter new password again here.");
            hasError = true;
        }
        if (!currentPasswordHash.equals(databasePasswordHash)) {
            session.setAttribute("errorCurrentPassword", "Current password is incorrect. Please try again.");
            hasError = true;
        }
        if (!RegexCheckerUtils.checkPassword(newPassword)) {
            session.setAttribute("errorNewPassword", "Choose a more secure password. It should be at least 8 characters and difficult for others to guess.");
            hasError = true;
        }
        if (newPassword.equals(currentPassword)) {
            session.setAttribute("errorNewPassword", "New password must be different from the current password.");
            hasError = true;
        }

        if (hasError) {
            session.setAttribute("currentPassword", currentPassword);
            session.setAttribute("newPassword", newPassword);
            session.setAttribute("newPasswordRetype", newPasswordRetype);
        } else {
            customer.setPassword(HashGeneratorUtils.generateSHA256DefaultSalt(newPassword));
            session.setAttribute("loggedCustomer", customer);
            CustomerDAO customerDAO = new CustomerDAOImpl();
            // if the password is updated successfully
            if (customerDAO.updatePassword(customer)) {
                session.setAttribute("message", "Password changed successfully!");
                // implement the log out all devices feature
                if (logOutDevices) {
//                    session.removeAttribute("loggedCustomer");
//                    session.invalidate();
                    CustomerAuthDAO customerAuthDAO = new CustomerAuthDAOImpl();
                    customerAuthDAO.deleteAllTokens(customer); // delete all tokens from the database

                    // create a secret token to send request to logout-all servlet
                    String token = RandomStringUtils.randomAlphanumeric(12);
                    session.setAttribute("token", token);
                    url = "/logout-all" + "?token=" + token;
                }
            } else {
                session.setAttribute("message", "An error occurred while changing password. Please try again.");
            }
        }

        response.sendRedirect(url);
    }
}
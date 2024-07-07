package controller;

import dao.CustomerAuthDAO;
import dao.CustomerAuthDAOImpl;
import dao.CustomerDAO;
import dao.CustomerDAOImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import listener.SessionListener;
import model.Customer;
import model.CustomerAuth;
import org.apache.commons.lang3.RandomStringUtils;
import utils.HashGeneratorUtils;
import utils.RegexCheckerUtils;

import java.io.IOException;
import java.sql.Date;
import java.util.concurrent.CopyOnWriteArrayList;

@WebServlet(name = "UserController", urlPatterns = {"/user"})
public class UserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            response.sendRedirect("/");
        } else {
            switch (action) {
                case "change-password":
                    changePassword(request, response);
                    break;
                case "logout-all":
                    logoutAll(request, response);
                    break;
                case "change-information":
                    changeInformation(request, response);
                    break;
                case "logout":
                    logout(request, response);
                    break;
                default:
                    response.sendRedirect("/");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void changePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            response.sendRedirect("account/login.jsp");
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
                    url = "/user?action=logout-all&token=" + token;
                }
            } else {
                session.setAttribute("message", "An error occurred while changing password. Please try again.");
            }
        }

        response.sendRedirect(url);
    }

    protected void logoutAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get token from query string
        String token = request.getParameter("token");

        // Get token from session
        HttpSession currentSession = request.getSession();
        String sessionToken = (String) currentSession.getAttribute("token");

        // prevent CSRF attack
        if (token == null || !token.equals(sessionToken)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid token");
            return;
        }

        Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer");
        String userId = customer.getCustomerId(); // Get user ID from the current session or request

        CopyOnWriteArrayList<String> sessionIds = SessionListener.getSessions(userId);

        for (String sessionId : sessionIds) {
            HttpSession session = SessionListener.getSessionById(sessionId);
            if (session != null && !currentSession.equals(session)) {
                System.out.println("Session ID: " + sessionId + " - " + session);
                session.removeAttribute("loggedCustomer");
                session.invalidate();
            }
        }

        currentSession.setAttribute("message", "Password changed successfully! All other sessions are logged out.");
        currentSession.removeAttribute("token"); // token is no longer needed
        response.sendRedirect("/user/change-password.jsp");
    }

    protected void changeInformation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get the new information from the form
        String fullName = request.getParameter("fullName");
        String gender = request.getParameter("gender");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String billingAddress = request.getParameter("billingAddress");
        String shippingAddress = request.getParameter("shippingAddress");
        String invoiceAddress = request.getParameter("invoiceAddress");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String subscribeToNewsletter = request.getParameter("subscribeToNewsletter");

        // update the user's information in the database
        CustomerDAO customerDAO = new CustomerDAOImpl();
        HttpSession session = request.getSession();
        String message = "";
        boolean result = false;

        Customer customer = (Customer) session.getAttribute("loggedCustomer");
        if (customer != null) {
            customer.setFullName(fullName);
            customer.setGender(gender);
            customer.setDateOfBirth(Date.valueOf(dateOfBirth));
            customer.setBillingAddress(billingAddress);
            customer.setShippingAddress(shippingAddress);
            customer.setInvoiceAddress(invoiceAddress);
            customer.setPhoneNumber(phoneNumber);
            customer.setEmail(email);
            customer.setSubscribeToNewsletter(Boolean.parseBoolean(subscribeToNewsletter));
            result = customerDAO.updateInformation(customer);
            session.setAttribute("loggedCustomer", customer);
        }
        if (result)
            message = "Information updated successfully!";
        else
            message = "Failed to update information! Please try again later";
        session.setAttribute("message", message);
        response.sendRedirect("/user/change-info.jsp");
    }

    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("loggedCustomer");
        session.invalidate();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            String selector = null;
            for (Cookie cookie : cookies) {
                if ("selector".equals(cookie.getName())) {
                    selector = cookie.getValue();
                }
            }
            if (selector != null) {
                CustomerAuthDAO customerAuthDAO = new CustomerAuthDAOImpl();
                CustomerAuth customerAuth = customerAuthDAO.selectBySelector(selector);
                if (customerAuth != null) {
                    customerAuthDAO.deleteBySelector(selector);
                }
                // remove cookies
                Cookie selectorCookie = new Cookie("selector", "");
                selectorCookie.setMaxAge(0); // delete the cookie
                Cookie validatorCookie = new Cookie("validator", "");
                validatorCookie.setMaxAge(0); // delete the cookie
                response.addCookie(selectorCookie);
                response.addCookie(validatorCookie);
            }
        }
        response.sendRedirect("/");
    }


}
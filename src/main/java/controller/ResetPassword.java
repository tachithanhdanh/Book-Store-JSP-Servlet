package controller;

import dao.CustomerDAO;
import dao.CustomerDAOImpl;
import dao.TokenForgetPasswordDAO;
import dao.TokenForgetPasswordDAOImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Customer;
import model.TokenForgetPassword;
import utils.HashGeneratorUtils;
import utils.RegexCheckerUtils;

import java.io.IOException;

@WebServlet(name = "ResetPassword", value = "/account/reset-password")
public class ResetPassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (getTokenForgetPassword(request.getParameter("token"), request.getSession()) != null) {
            getServletContext().getRequestDispatcher("/account/reset-password.jsp").forward(request, response);
        } else {
            response.sendRedirect("/account/request-password");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newPassword = request.getParameter("newPassword");
        String newPasswordRetype = request.getParameter("newPasswordRetype");
        HttpSession session = request.getSession();
        String token = (String) session.getAttribute("token");

        boolean hasError = false;

        // validate password
        if (!newPassword.equals(newPasswordRetype)) {
            request.setAttribute("errorNewPasswordRetype", "Passwords do not match");
            hasError = true;
        } else if (RegexCheckerUtils.checkPassword(newPassword)) {
            request.setAttribute("errorNewPassword", "Choose a more secure password " +
                    "that you don't use anywhere else. " +
                    "It should be at least 8 characters and difficult for others to guess.");
            hasError = true;
        }
        if (hasError) {
            request.setAttribute("newPassword", newPassword);
            request.setAttribute("newPasswordRetype", newPasswordRetype);
            getServletContext().getRequestDispatcher("/account/reset-password.jsp").forward(request, response);
            return;
        }

        TokenForgetPassword tokenForgetPassword = getTokenForgetPassword(token, session);
        if (tokenForgetPassword == null) {
            response.sendRedirect("/account/request-password");
            return;
        }

        // update password
        Customer customer = tokenForgetPassword.getCustomer();
        customer.setPassword(HashGeneratorUtils.generateSHA256DefaultSalt(newPassword));

        CustomerDAO customerDAO = new CustomerDAOImpl();
        customerDAO.updatePassword(customer);

        // set token as used
        tokenForgetPassword.setUsed(true);
        TokenForgetPasswordDAO tokenForgetPasswordDAO = new TokenForgetPasswordDAOImpl();
        tokenForgetPasswordDAO.updateStatus(tokenForgetPassword);

        request.getSession().setAttribute("error", "Password has been reset successfully" +
                "<br>Please login with your new password");
        response.sendRedirect("/account/login.jsp");
    }

    protected TokenForgetPassword getTokenForgetPassword(String token, HttpSession session) {
        if (token == null) {
            return null;
        }
        TokenForgetPasswordDAO tokenDAO = new TokenForgetPasswordDAOImpl();
        TokenForgetPassword tokenForgetPassword = tokenDAO.getToken(token);
        if (tokenForgetPassword == null) {
            session.setAttribute("message", "Invalid token");
        } else if (tokenForgetPassword.isUsed()) {
            session.setAttribute("message", "Token has been used");
        } else if (tokenForgetPassword.getExpiryDate().isBefore(java.time.LocalDateTime.now())) {
            session.setAttribute("message", "Token has expired");
        } else {
            session.setAttribute("token", tokenForgetPassword.getToken());
            return tokenForgetPassword;
        }
        return null;
    }
}
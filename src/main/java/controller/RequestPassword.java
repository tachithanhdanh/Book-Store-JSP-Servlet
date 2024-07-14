package controller;

import dao.CustomerDAO;
import dao.CustomerDAOImpl;
import dao.TokenForgetPasswordDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Customer;
import model.TokenForgetPassword;
import org.apache.commons.lang3.RandomStringUtils;
import utils.EmailUtils;
import utils.HashGeneratorUtils;
import dao.TokenForgetPasswordDAOImpl;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@WebServlet(name = "RequestPassword", value = "/account/request-password")
public class RequestPassword extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final int TIME_TO_LIVE = 15; // 15 minutes

    private String host;
    private String port;
    private String email;
    private String name;
    private String pass;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        email = context.getInitParameter("email");
        name = context.getInitParameter("name");
        pass = context.getInitParameter("pass");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/account/request-password.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String recipient = request.getParameter("email");

        String message = "";

        CustomerDAO customerDAO = new CustomerDAOImpl();
        Customer customer = customerDAO.selectByUsername(username);
        if (customer != null && customer.getEmail().equals(recipient)) {
            // Generate a random token
            String token = RandomStringUtils.randomAlphanumeric(20);
            // Insert the token into the database
            TokenForgetPassword tokenForgetPassword = new TokenForgetPassword();
            tokenForgetPassword.setCustomer(customer);
            tokenForgetPassword.setToken(token);
            tokenForgetPassword.setExpiryDate(LocalDateTime.now().plusMinutes(TIME_TO_LIVE));
            tokenForgetPassword.setUsed(false);

            System.out.println(tokenForgetPassword.toString());

            // Save the token to the database
            TokenForgetPasswordDAO tokenDAO = new TokenForgetPasswordDAOImpl();
            if (!tokenDAO.insertToken(tokenForgetPassword)) {
                request.setAttribute("message", "An error occurred while processing your request. Please try again later.");
            }

            // Send the email
            LocalDate today = LocalDate.now();
            String subject = "[Bookstore - " + today +"] Reset your password";
            String content = "<p>Dear <strong>" + customer.getUsername() + "</strong>,</p>"
                    + "<p>You have requested to reset your password. "
                    + "Please click "
                    + "<a href=\"http://localhost:8080/account/reset-password?token=" + token + "\">here</a>"
                    + " to reset your password:</p>"
                    + "<p>If you did not request to reset your password, please ignore this email.</p>"
                    + "<p>Thank you,</p>"
                    + "<p><strong>Bookstore Team</strong></p>";
            try {
                EmailUtils.sendEmail(host, port, email, name, pass, recipient, subject, content);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            message = "An email has been sent to your email address. Please check your email to reset your password.";
        } else {
            // Redirect to the reset-password page with an error message
            message = "Invalid username or email!";
        }
        request.setAttribute("message", message);
        getServletContext().getRequestDispatcher("/account/request-password.jsp").forward(request, response);
    }
}
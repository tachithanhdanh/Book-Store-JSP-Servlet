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
import utils.CookiesGeneratorUtils;
import utils.EmailUtils;
import utils.HashGeneratorUtils;
import utils.RegexCheckerUtils;

import java.io.IOException;
import java.sql.Date;
import java.util.Random;

@WebServlet(name = "LoginController", value = "/account")
public class AccountController extends HttpServlet {
    private static final long serialVersionUID = 1L;

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
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            response.sendRedirect("/");
        } else {
            switch (action) {
                case "login":
                    login(request, response);
                    break;
                case "register":
                    register(request, response);
                    break;
                case "reset-password":
                    resetPassword(request, response);
                default:
                    break;
            }
        }
    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        password = HashGeneratorUtils.generateSHA256DefaultSalt(password);
        boolean stayLoggedIn = "true".equals(request.getParameter("stayLoggedIn"));

        CustomerDAO customerDAO = new CustomerDAOImpl();

        Customer customer = customerDAO.selectByUsernameAndPassword(username, password);
        HttpSession session = request.getSession();
        String url = request.getParameter("redirect");
        if (url == null || url.isEmpty()) {
            url = "/home";
        }
        if (customer != null) {
            session.setAttribute("loggedCustomer", customer);
            // Save sessionId and userId to the session listener
            SessionListener.addSession(customer.getCustomerId(), session);
//            url = "/home";
            if (stayLoggedIn) {
                // Create cookies to store logged-in status
                CookiesGeneratorUtils.generateStayLoggedInCookies(response, customer);
            }
        } else {
            session.setAttribute("error", "Invalid username or password!");
            url = "/account/login.jsp";
        }
        // Prevent back button
        // for HTTP 1.1 and after
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        // HTTP 1.0
        response.setHeader("Pragma", "no-cache");
        // Proxy Server
        response.setHeader("Expires", "0");
        System.out.println("Redirecting to: " + url);

        response.sendRedirect(url);
    }

    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordConfirm = request.getParameter("passwordConfirm");
        String fullName = request.getParameter("fullName");
        String gender = request.getParameter("gender");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String billingAddress = request.getParameter("billingAddress");
        String shippingAddress = request.getParameter("shippingAddress");
        String invoiceAddress = request.getParameter("invoiceAddress");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String subscribeToNewsletter = request.getParameter("subscribeToNewsletter");

        HttpSession session = request.getSession();

        String errorUsername = "";
        String errorPasswordConfirm = "";
        String errorPassword = "";

        if (!RegexCheckerUtils.checkUsername(username)) {
            errorUsername = "Invalid username!";
        } else {
            CustomerDAO customerDAO = new CustomerDAOImpl();
            if (customerDAO.selectByUsername(username) != null) {
                errorUsername = "Username already exists!";
            }
        }

        if (!password.equals(passwordConfirm)) {
            errorPasswordConfirm = "Passwords do not match!";
        }

        if (RegexCheckerUtils.checkPassword(password)) {
            errorPassword = "Choose a more secure password. It should be at least 8 characters and difficult for others to guess.";
        }

        String url = "";

        if (!errorUsername.isEmpty() || !errorPasswordConfirm.isEmpty() || !errorPassword.isEmpty()) {
            url = "/account/register.jsp";
            session.setAttribute("username", username);
            session.setAttribute("fullName", fullName);
            session.setAttribute("gender", gender);
            session.setAttribute("dateOfBirth", dateOfBirth);
            session.setAttribute("billingAddress", billingAddress);
            session.setAttribute("shippingAddress", shippingAddress);
            session.setAttribute("invoiceAddress", invoiceAddress);
            session.setAttribute("phoneNumber", phoneNumber);
            session.setAttribute("email", email);
            session.setAttribute("subscribeToNewsletter", subscribeToNewsletter);
            session.setAttribute("errorUsername", errorUsername);
            session.setAttribute("errorPasswordConfirm", errorPasswordConfirm);
            session.setAttribute("errorPassword", errorPassword);
        } else {
            // Create a new customer
            // Insert the new customer into the database
            // Redirect to the register-success page
//            url = "/register-success";
            url = "/account/login.jsp";
            session.invalidate();
            session = request.getSession();
            session.setAttribute("error", "Registration successful! Please login to continue.");
            Random rd = new Random();
            String customerId = "" + System.currentTimeMillis() + rd.nextInt(1000);
            password = HashGeneratorUtils.generateSHA256DefaultSalt(password);
            Customer customer = new Customer(customerId, username, password,
                    fullName, gender, billingAddress,
                    shippingAddress, invoiceAddress,
                    Date.valueOf(dateOfBirth),
                    phoneNumber, email,
                    Boolean.parseBoolean(subscribeToNewsletter));
            CustomerDAO customerDAO = new CustomerDAOImpl();
            customerDAO.insert(customer);
        }

//        this.getServletContext().getRequestDispatcher(url).forward(request, response);

        response.sendRedirect(url);
    }

    protected void resetPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String recipient = request.getParameter("email");

        String message = "";

        CustomerDAO customerDAO = new CustomerDAOImpl();
        Customer customer = customerDAO.selectByUsername(username);
        if (customer != null && customer.getEmail().equals(recipient)) {
            String newPassword = RandomStringUtils.randomAlphanumeric(12) + "1Aa!";
            String hashedPassword = HashGeneratorUtils.generateSHA256DefaultSalt(newPassword);
            customer.setPassword(hashedPassword);
            customerDAO.update(customer);
            // Send email to the customer
            Date today = new Date(System.currentTimeMillis());
            String subject = "[Bookstore - " + today +"] Your password has been reset!";
            System.out.println(subject);
            String content = "Hi, this is your new password: " + newPassword;
            content += "\t\nNote: for security reason, "
                    + "you must change your password after logging in.";

            message = "Your password has been reset.<br>Please check your e-mail.";
            // Redirect to the reset-password-success page
            try {
                EmailUtils.sendEmail(host, port, email, name, pass, recipient, subject, content);
            } catch (Exception e) {
                System.out.println("Error sending email: " + e.getMessage());
                message = "There was an error sending the email: " + e.getMessage();
            }
        } else {
            // Redirect to the reset-password page with an error message
            message = "Invalid username or email!";
        }
        HttpSession session = request.getSession();
        session.setAttribute("message", message);
        response.sendRedirect("/account/reset-password.jsp");
    }
}
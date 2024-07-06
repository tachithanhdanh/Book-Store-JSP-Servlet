package controller;

import dao.CustomerAuthDAO;
import dao.CustomerAuthDAOImpl;
import dao.CustomerDAO;
import dao.CustomerDAOImpl;
import model.Customer;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.CustomerAuth;
import org.apache.commons.lang3.RandomStringUtils;
import utils.HashGenerationException;
import utils.HashGeneratorUtils;

import java.io.IOException;

@WebServlet(name = "Login", value = "/login-servlet")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean rememberMe = "true".equals(request.getParameter("rememberMe"));

        CustomerDAO customerDAO = new CustomerDAOImpl();

        Customer customer = customerDAO.selectByUsernameAndPassword(username, password);
        HttpSession session = request.getSession();
        String url = "";
        if (customer != null) {
            session.setAttribute("loggedCustomer", customer);
            url = "/home";
            if (rememberMe) {
                // Create new token (selector and validator)
                CustomerAuth newToken = new CustomerAuth();

                String selector = RandomStringUtils.randomAlphanumeric(12);
                String rawValidator = RandomStringUtils.randomAlphanumeric(64);
                String hashedValidator = null;
                try {
                    hashedValidator = HashGeneratorUtils.generateSHA256(rawValidator);
                } catch (HashGenerationException e) {
                    System.out.println("Error occurred when hashing the validator");
                    System.out.println(e.getMessage());
                }

                newToken.setSelector(selector);
                newToken.setValidator(hashedValidator);
                newToken.setCustomer(customer);

                // Save the token to the database
                CustomerAuthDAO customerAuthDAO = new CustomerAuthDAOImpl();
                customerAuthDAO.insert(newToken);

                // Create cookies
                Cookie selectorCookie = new Cookie("selector", selector);
                selectorCookie.setMaxAge(60 * 60 * 24 * 7); // 7 days
                Cookie validatorCookie = new Cookie("validator", rawValidator);
                validatorCookie.setMaxAge(60 * 60 * 24 * 7); // 7 days

                // add cookies to the response
                response.addCookie(selectorCookie);
                response.addCookie(validatorCookie);
            }
        } else {
            session.setAttribute("error", "Invalid username or password!");
            url = "/login";
        }
        // Prevent back button
        // for HTTP 1.1 and after
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        // HTTP 1.0
        response.setHeader("Pragma", "no-cache");
        // Proxy Server
        response.setHeader("Expires", "0");

        response.sendRedirect(url);
    }
}
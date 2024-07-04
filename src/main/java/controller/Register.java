package controller;

import java.io.IOException;
import java.util.Random;
import java.sql.Date;

import model.Customer;
import dao.CustomerDAO;
import dao.CustomerDAOImpl;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.ServletException;

@WebServlet(name = "Register", value = "/register-servlet")
public class Register extends HttpServlet {

    public void init() {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        request.setAttribute("username", username);
        request.setAttribute("fullName", fullName);
        request.setAttribute("gender", gender);
        request.setAttribute("dateOfBirth", dateOfBirth);
        request.setAttribute("billingAddress", billingAddress);
        request.setAttribute("shippingAddress", shippingAddress);
        request.setAttribute("invoiceAddress", invoiceAddress);
        request.setAttribute("phoneNumber", phoneNumber);
        request.setAttribute("email", email);
        request.setAttribute("subscribeToNewsletter", subscribeToNewsletter);

        String errorUsername = "";
        String errorPasswordConfirm = "";

        String usernameRegex = "^[a-z][a-z0-9]{5,29}$";
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
        String emailRegex = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6})*$";

        if (!username.matches(usernameRegex)) {
            errorUsername = "Invalid username!";
        } else {
            CustomerDAO customerDAO = new CustomerDAOImpl();
            if (customerDAO.selectByUsername(username)) {
                errorUsername = "Username already exists!";
            }
        }

        if (!password.equals(passwordConfirm)) {
            errorPasswordConfirm = "Passwords do not match!";
        }

        String url = "";

        if (!errorUsername.isEmpty() || !errorPasswordConfirm.isEmpty()) {
            url = "/register";
            request.setAttribute("errorUsername", errorUsername);
            request.setAttribute("errorPasswordConfirm", errorPasswordConfirm);
        } else {
            // Create a new customer
            // Insert the new customer into the database
            // Redirect to the register-success page
            url = "/register-success";
            Random rd = new Random();
            String customerId = "" + System.currentTimeMillis() + rd.nextInt(1000);
            Customer customer = new Customer(customerId, username, password,
                    fullName, gender, billingAddress,
                    shippingAddress, invoiceAddress,
                    Date.valueOf(dateOfBirth),
                    phoneNumber, email,
                    subscribeToNewsletter != null);
            CustomerDAO customerDAO = new CustomerDAOImpl();
            customerDAO.insert(customer);
        }

        this.getServletContext().getRequestDispatcher(url).forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/register");
    }

    public void destroy() {
    }
}
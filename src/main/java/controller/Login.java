package controller;

import dao.CustomerDAO;
import dao.CustomerDAOImpl;
import model.Customer;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
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

        CustomerDAO customerDAO = new CustomerDAOImpl();

        Customer customer = customerDAO.selectByUsernameAndPassword(username, password);
        HttpSession session = request.getSession();
        String url = "";
        if (customer != null) {
            session.setAttribute("customer", customer);
            url = "/home";
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
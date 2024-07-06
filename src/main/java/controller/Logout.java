package controller;

import dao.CustomerAuthDAO;
import dao.CustomerAuthDAOImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.CustomerAuth;

import java.io.IOException;

@WebServlet(name = "Logout", value = "/logout")
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        response.sendRedirect("/home");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
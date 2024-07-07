package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import listener.SessionListener;
import model.Customer;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

@WebServlet("/logout-all")
public class LogoutAll extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response); // No POST request, so redirect to GET request to avoid duplicate code
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer");
        String userId = customer.getCustomerId(); // Get user ID from the current session or request

        // Get token from query string
        String token = request.getParameter("token");

        // Get token from session
        HttpSession currentSession = request.getSession();
        String sessionToken = (String) currentSession.getAttribute("token");

        if (token == null || !token.equals(sessionToken)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid token");
            return;
        }

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
        response.sendRedirect("/user/change-password.jsp");
    }
}

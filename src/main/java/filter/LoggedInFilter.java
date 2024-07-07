package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter(filterName = "LoggedInFilter", urlPatterns = {"/account/*"})
public class LoggedInFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
        System.out.println("Filter initialized");
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        System.out.println("Filter called");
        if (session.getAttribute("loggedCustomer") != null) {
            res.sendRedirect("/home");
            return;
        }
        chain.doFilter(request, response);
    }
}
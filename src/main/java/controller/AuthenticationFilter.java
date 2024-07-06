package controller;

import dao.CustomerAuthDAO;
import dao.CustomerAuthDAOImpl;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CustomerAuth;
import org.apache.commons.lang3.RandomStringUtils;
import utils.HashGeneratorUtils;

import java.io.IOException;

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = {"/userOnlyTest.jsp", "/", "/home", "/index.jsp"})
public class AuthenticationFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("AuthenticationFilter called");

        // check user's login status via session
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        // get Cookie array from request
        Cookie[] cookies = httpRequest.getCookies();

        boolean loggedIn = session != null && session.getAttribute("loggedCustomer") != null;

        // if the user is not logged in, check the stayed logged in cookie
        if (!loggedIn && cookies != null) {
            Cookie selectorCookie = null;
            Cookie validatorCookie = null;

            for (Cookie cookie : cookies) {
                if ("selector".equals(cookie.getName())) {
                    selectorCookie = cookie;
                } else if ("validator".equals(cookie.getName())) {
                    validatorCookie = cookie;
                }
            }

            if (selectorCookie != null && validatorCookie != null && !selectorCookie.getValue().isEmpty()) {
                // get the token from the database
                CustomerAuthDAO customerAuthDAO = new CustomerAuthDAOImpl();
                CustomerAuth customerToken = customerAuthDAO.selectBySelector(selectorCookie.getValue());

                if (customerToken != null) {
                    // validate the token
                    String hashedValidatorDatabase = customerToken.getValidator();
                    String rawValidator = validatorCookie.getValue();
                    String hashedValidatorCookie = HashGeneratorUtils.generateSHA256(rawValidator);


                    // Update the token in database
                    if (hashedValidatorDatabase.equals(hashedValidatorCookie)) {
                        // if the token is valid, set the session
                        session = httpRequest.getSession();
                        session.setAttribute("loggedCustomer", customerToken.getCustomer());
                        loggedIn = true;

                        // create new token to replace the old one
                        String newSelector = RandomStringUtils.randomAlphanumeric(12);
                        String newRawValidator = RandomStringUtils.randomAlphanumeric(64);
                        String newHashedValidator = HashGeneratorUtils.generateSHA256(newRawValidator);

                        // update the token in database
                        customerToken.setSelector(newSelector);
                        customerToken.setValidator(newHashedValidator);
                        customerAuthDAO.update(customerToken);

                        // update cookie
                        selectorCookie.setValue(newSelector);
                        selectorCookie.setMaxAge(60 * 60 * 24 * 7); // 7 days
                        validatorCookie.setValue(newRawValidator);
                        validatorCookie.setMaxAge(60 * 60 * 24 * 7); // 7 days
                        httpResponse.addCookie(selectorCookie);
                        httpResponse.addCookie(validatorCookie);
                    }
                }
            }

        }
        String url = httpRequest.getRequestURI().substring(1);
        if (!loggedIn && "userOnlyTest.jsp".equals(url)) {
            HttpSession newSession = httpRequest.getSession();
            newSession.setAttribute("error", "You have to log in first to access the page!");
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login" + "?redirect=" + url);
            return;
        }
        chain.doFilter(request, response);
    }
}
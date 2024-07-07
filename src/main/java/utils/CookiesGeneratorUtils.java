package utils;

import dao.CustomerAuthDAO;
import dao.CustomerAuthDAOImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import model.Customer;
import model.CustomerAuth;
import org.apache.commons.lang3.RandomStringUtils;

public class CookiesGeneratorUtils {
    public static void generateStayLoggedInCookies(HttpServletResponse response, Customer customer) {
        // Create new token (selector and validator)
        CustomerAuth newToken = new CustomerAuth();

        String selector = RandomStringUtils.randomAlphanumeric(12);
        String rawValidator = RandomStringUtils.randomAlphanumeric(64);
        String hashedValidator = null;
        hashedValidator = HashGeneratorUtils.generateSHA256(rawValidator);

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
}

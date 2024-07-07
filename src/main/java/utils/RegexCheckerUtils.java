package utils;

public class RegexCheckerUtils {
    private static final String usernameRegex = "^[a-z][a-z0-9]{5,29}$";
    // https://uibakery.io/regex-library/password
    private static final String passwordRegex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
    private static final String emailRegex = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6})*$";

    public static boolean checkUsername(String username) {
        return username.matches(usernameRegex);
    }

    public static boolean checkPassword(String password) {
        return password.matches(passwordRegex);
    }

    public static boolean checkEmail(String email) {
        return email.matches(emailRegex);
    }
}

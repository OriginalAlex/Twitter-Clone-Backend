package io.github.originalalex.twitter.server.verification;

public class RegistrationVerification {

    public static String isPasswordValid(String password) {
        if (password.length() <= 4) {
            return "Password is too short";
        }
        if (password.length() >= 65) {
            return "Password is too long";
        }
        boolean hasCapital = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasCapital = true;
                break;
            }
        }
        if (!hasCapital) {
            return "Passwords require at least one capital letter.";
        }
        return "Valid";
    }

}

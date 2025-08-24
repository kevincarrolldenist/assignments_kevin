package org.assignment5;

public class FormValidationApp {

    public static void main(String[] args) {
        System.out.println("--- Demonstrating Interface Static Method ---");

        String testString1 = "hello";
        String testString2 = "";
        String testString3 = null;

        System.out.println("Is \"" + testString1 + "\" not empty? " + DataValidator.isNotEmpty(testString1));
        System.out.println("Is \"" + testString2 + "\" not empty? " + DataValidator.isNotEmpty(testString2));
        System.out.println("Is null not empty? " + DataValidator.isNotEmpty(testString3));

        System.out.println("\n--- Demonstrating Interface Abstract Method Implementation ---");

        DataValidator emailValidator = new EmailValidator();
        DataValidator phoneValidator = new PhoneValidator();

        String validEmail = "test@example.com";
        String invalidEmail = "invalid-email";
        String validPhone = "123-456-7890";
        String invalidPhone = "123";

        System.out.println("Is \"" + validEmail + "\" a valid email? " + emailValidator.isValid(validEmail));
        System.out.println("Is \"" + invalidEmail + "\" a valid email? " + emailValidator.isValid(invalidEmail));

        System.out.println("Is \"" + validPhone + "\" a valid phone? " + phoneValidator.isValid(validPhone));
        System.out.println("Is \"" + invalidPhone + "\" a valid phone? " + phoneValidator.isValid(invalidPhone));
    }
}

interface DataValidator {
    static boolean isNotEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }

    boolean isValid(String input);
}

class EmailValidator implements DataValidator {
    @Override
    public boolean isValid(String input) {
        if (!DataValidator.isNotEmpty(input)) {
            return false;
        }
        return input.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    }
}

class PhoneValidator implements DataValidator {
    @Override
    public boolean isValid(String input) {
        if (!DataValidator.isNotEmpty(input)) {
            return false;
        }
        return input.matches("^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$");
    }
}

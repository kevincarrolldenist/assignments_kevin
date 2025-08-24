package org.assignment7;

import java.util.Scanner;

public class ATMValidationApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATM atm = new ATM();

        System.out.print("Enter PIN: ");
        String enteredPin = scanner.nextLine();

        try {
            atm.validatePin(enteredPin);
            System.out.println("Access Granted. Welcome!");
        } catch (InvalidPinException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}

class InvalidPinException extends Exception {
    public InvalidPinException(String message) {
        super(message);
    }
}

class ATM {
    private static final String VALID_PIN = "1234";

    public void validatePin(String enteredPin) throws InvalidPinException {
        if (enteredPin == null || enteredPin.length() != 4) {
            throw new InvalidPinException("PIN must be exactly 4 digits.");
        }

        if (!enteredPin.equals(VALID_PIN)) {
            throw new InvalidPinException("Incorrect PIN.");
        }
    }
}


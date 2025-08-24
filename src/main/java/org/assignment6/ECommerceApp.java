package org.assignment6;

import java.util.Scanner;

public class ECommerceApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Checkout checkout = new Checkout();
        double price = 0.0;
        int quantity = 0;

        try {
            System.out.print("Enter item price: ");
            String priceStr = scanner.nextLine();
            price = Double.parseDouble(priceStr);

            System.out.print("Enter item quantity: ");
            String quantityStr = scanner.nextLine();
            quantity = Integer.parseInt(quantityStr);

            double total = checkout.calculateTotal(price, quantity);
            System.out.println("Calculated Total: $" + String.format("%.2f", total));

        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid number format. Please enter numeric values for price and quantity.");
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Checkout process complete.");
            scanner.close();
        }
    }
}

class Checkout {
    public double calculateTotal(double price, int quantity) {
        if (price <= 0 || quantity <= 0) {
            throw new IllegalArgumentException("Price and quantity must be positive values.");
        }

        double total = price * quantity;

        if (Double.isInfinite(total) || total > 1_000_000_000_000_000.0) {
            throw new ArithmeticException("Calculation overflow occurred. Total exceeds system limits.");
        }

        return total;
    }
}

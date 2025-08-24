package org.assignment7;

public class ECommerceOrderProcessingApp {

    public static void main(String[] args) {
        OrderService orderService = new OrderService();

        System.out.println("Placing order for Laptop (Quantity: 1)...");
        try {
            orderService.placeOrder("Laptop", 1);
            System.out.println("Order placed successfully.");
        } catch (InvalidOrderQuantityException e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println("\nPlacing order for Phone (Quantity: 0)...");
        try {
            orderService.placeOrder("Phone", 0);
            System.out.println("Order placed successfully.");
        } catch (InvalidOrderQuantityException e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println("\nPlacing order for Mouse (Quantity: -5)...");
        try {
            orderService.placeOrder("Mouse", -5);
            System.out.println("Order placed successfully.");
        } catch (InvalidOrderQuantityException e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println("\nPlacing order for Keyboard (Quantity: 3)...");
        try {
            orderService.placeOrder("Keyboard", 3);
            System.out.println("Order placed successfully.");
        } catch (InvalidOrderQuantityException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

class InvalidOrderQuantityException extends RuntimeException {
    public InvalidOrderQuantityException(String message) {
        super(message);
    }
}

class OrderService {
    public void placeOrder(String productName, int quantity) {
        if (quantity <= 0) {
            throw new InvalidOrderQuantityException("Order quantity for '" + productName + "' must be greater than zero.");
        }
    }
}


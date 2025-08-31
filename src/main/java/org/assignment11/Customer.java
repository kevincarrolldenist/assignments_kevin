package org.assignment11;

// Model Layer
// Customer.java
public class Customer {
    private int customerId;
    private String name;
    private String email;
    private double balance;

    public Customer(int customerId, String name, String email, double balance) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.balance = balance;
    }

    // Getters
    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public double getBalance() {
        return balance;
    }

    // Setters
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Customer ID: " + customerId +
                ", Name: " + name +
                ", Email/Mobile: " + email +
                ", Balance: $" + String.format("%.2f", balance);
    }
}

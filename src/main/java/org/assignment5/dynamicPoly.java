package org.assignment5;

class Payment {
    public void processPayment(double amount) {
        System.out.println("Processing generic payment of $" + amount);
    }
}

class CreditCardPayment extends Payment {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing Credit Card payment of $" + amount + ". Charging card...");
    }
}

class UPIPayment extends Payment {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing UPI payment of $" + amount + ". Initiating UPI transaction...");
    }
}

public class dynamicPoly {
    public static void main(String[] args) {
        Payment creditCard = new CreditCardPayment();
        Payment upiPayment = new UPIPayment();

        creditCard.processPayment(150.75);
        upiPayment.processPayment(50.00);
    }
}

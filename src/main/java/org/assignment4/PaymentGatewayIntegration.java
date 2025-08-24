package org.assignment4;

abstract class PaymentGateway {
    protected String gatewayName;
    protected String apiKey;
    protected String connectionUrl;

    public PaymentGateway(String gatewayName, String apiKey, String connectionUrl) {
        this.gatewayName = gatewayName;
        this.apiKey = apiKey;
        this.connectionUrl = connectionUrl;
    }

    public void connect() {
        System.out.println("Connected to " + gatewayName + " API");
    }

    public void disconnect() {
        System.out.println("Disconnected from " + gatewayName + " API");
    }
}

interface PaymentProcessor {
    void processPayment(double amount);
}

interface RefundProcessor {
    void processRefund(double amount);
}

class StripeGateway extends PaymentGateway implements PaymentProcessor, RefundProcessor {
    public StripeGateway() {
        super("Stripe", "sk_test_stripe_key", "https://api.stripe.com");
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing payment of $" + amount + " via Stripe...");
    }

    @Override
    public void processRefund(double amount) {
        System.out.println("Processing refund of $" + amount + " via Stripe...");
    }
}

class PayPalGateway extends PaymentGateway implements PaymentProcessor, RefundProcessor {
    public PayPalGateway() {
        super("PayPal", "paypal_client_id", "https://api.paypal.com");
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing payment of $" + amount + " via PayPal...");
    }

    @Override
    public void processRefund(double amount) {
        System.out.println("Processing refund of $" + amount + " via PayPal...");
    }
}

public class PaymentGatewayIntegration {
    public static void main(String[] args) {
        StripeGateway stripe = new StripeGateway();
        stripe.connect();
        stripe.processPayment(500.00);
        stripe.processRefund(100.00);
        stripe.disconnect();

        PayPalGateway paypal = new PayPalGateway();
        paypal.connect();
        paypal.processPayment(250.00);
        paypal.processRefund(50.00);
        paypal.disconnect();
    }
}

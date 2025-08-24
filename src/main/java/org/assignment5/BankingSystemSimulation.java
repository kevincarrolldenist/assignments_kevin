package org.assignment5;

public class BankingSystemSimulation {

    public static void main(String[] args) {
        System.out.println("--- 1. Static Polymorphism (Overloading) ---");
        TransactionProcessor processor = new TransactionProcessor();
        System.out.println("Processing single account transaction:");
        processor.process(1001, 500.0);
        System.out.println("\nProcessing transfer transaction:");
        processor.process(1001, 1002, 250.0);

        System.out.println("\n--- 2. Dynamic Polymorphism (Overriding) ---");
        BankAccount savings = new SavingsAccount(1003, 1000.0);
        BankAccount current = new CurrentAccount(1004, 2000.0);
        BankAccount generic = new BankAccount(1005, 500.0);

        System.out.println("Calculating interest for Savings Account:");
        savings.calculateInterest();

        System.out.println("Calculating interest for Current Account:");
        current.calculateInterest();

        System.out.println("Calculating interest for Generic Bank Account:");
        generic.calculateInterest();

        System.out.println("\n--- 3. Interface Static & Default Methods ---");
        System.out.println("Logging security attempts:");
        SecurityCheck.logAttempt("user123");
        SecurityCheck.logAttempt("admin_attempt");

        UserAuthenticator authenticator = new UserAuthenticator();
        System.out.println("\nChecking security status:");
        authenticator.showSecurityStatus();
        System.out.println("\nVerifying users:");
        System.out.println("User 'testuser' authenticated: " + authenticator.verifyUser("testuser", "password123"));
        System.out.println("User 'admin' authenticated: " + authenticator.verifyUser("admin", "adminpass"));
        System.out.println("User 'wrong' authenticated: " + authenticator.verifyUser("wrong", "pass"));
    }
}

class TransactionProcessor {
    public void process(int accountNumber, double amount) {
        System.out.println("  Processing deposit/withdrawal of $" + amount + " for account " + accountNumber);
    }

    public void process(int fromAccount, int toAccount, double amount) {
        System.out.println("  Processing transfer of $" + amount + " from account " + fromAccount + " to account " + toAccount);
    }
}

class BankAccount {
    protected int accountNumber;
    protected double balance;

    public BankAccount(int accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void calculateInterest() {
        System.out.println("  Account " + accountNumber + ": Base interest calculation (no specific rate). Current balance: $" + balance);
    }
}

class SavingsAccount extends BankAccount {
    public SavingsAccount(int accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public void calculateInterest() {
        double interestRate = 0.02;
        double interest = balance * interestRate;
        balance += interest;
        System.out.println("  Savings Account " + accountNumber + ": Applied 2% interest. New balance: $" + String.format("%.2f", balance));
    }
}

class CurrentAccount extends BankAccount {
    public CurrentAccount(int accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public void calculateInterest() {
        double interestRate = 0.005;
        double interest = balance * interestRate;
        balance += interest;
        System.out.println("  Current Account " + accountNumber + ": Applied 0.5% interest. New balance: $" + String.format("%.2f", balance));
    }
}

interface SecurityCheck {
    static void logAttempt(String user) {
        System.out.println("  Security log: Attempt by user '" + user + "' at " + java.time.LocalDateTime.now());
    }

    default void showSecurityStatus() {
        System.out.println("  Secure connection established.");
    }

    boolean verifyUser(String username, String password);
}

class UserAuthenticator implements SecurityCheck {
    @Override
    public boolean verifyUser(String username, String password) {
        if (username.equals("testuser") && password.equals("password123")) {
            return true;
        }
        if (username.equals("admin") && password.equals("adminpass")) {
            return true;
        }
        return false;
    }
}

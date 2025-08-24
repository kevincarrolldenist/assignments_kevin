package org.assignment3;

// BankAccount.java
class BankAccount {
    protected double balance; // Changed to protected to allow subclasses to modify directly for overdraft
    private String accountNumber;
    private String ownerName;

    public BankAccount(String accountNumber, double initialBalance, String ownerName) {
        this.accountNumber = accountNumber;
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
        this.balance = initialBalance;
        this.ownerName = ownerName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.printf("Deposited $%.2f. New balance: $%.2f%n", amount, this.balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return false;
        }
        if (this.balance >= amount) {
            this.balance -= amount;
            System.out.printf("Withdrew $%.2f. New balance: $%.2f%n", amount, this.balance);
            return true;
        } else {
            System.out.printf("Insufficient funds. Current balance: $%.2f. Attempted withdrawal: $%.2f%n", this.balance, amount);
            return false;
        }
    }
}

// SavingsAccount.java
 class SavingsAccount extends BankAccount {
    private double interestRate;

    public SavingsAccount(String accountNumber, double initialBalance, String ownerName, double interestRate) {
        super(accountNumber, initialBalance, ownerName);
        if (interestRate < 0) {
            throw new IllegalArgumentException("Interest rate cannot be negative.");
        }
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        if (interestRate >= 0) {
            this.interestRate = interestRate;
        } else {
            System.out.println("Interest rate cannot be negative.");
        }
    }

    public void calculateInterest() {
        double interest = getBalance() * interestRate;
        deposit(interest);
        System.out.printf("Interest of $%.2f applied. New balance: $%.2f%n", interest, getBalance());
    }
}

// CurrentAccount.java
 class CurrentAccount extends BankAccount {
    private double overdraftLimit;

    public CurrentAccount(String accountNumber, double initialBalance, String ownerName, double overdraftLimit) {
        super(accountNumber, initialBalance, ownerName);
        if (overdraftLimit < 0) {
            throw new IllegalArgumentException("Overdraft limit cannot be negative.");
        }
        this.overdraftLimit = overdraftLimit;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        if (overdraftLimit >= 0) {
            this.overdraftLimit = overdraftLimit;
        } else {
            System.out.println("Overdraft limit cannot be negative.");
        }
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return false;
        }

        double availableFunds = getBalance() + overdraftLimit;

        if (availableFunds >= amount) {
            this.balance -= amount;
            System.out.printf("Withdrew $%.2f. New balance: $%.2f%n", amount, this.balance);
            return true;
        } else {
            System.out.printf("Withdrawal of $%.2f exceeds available funds ($%.2f) including overdraft limit ($%.2f).%n",
                    amount, getBalance(), overdraftLimit);
            return false;
        }
    }
}

// BankDemo.java
public class BankDemo {
    public static void main(String[] args) {
        System.out.println("--- Demonstrating BankAccount ---");
        BankAccount account1 = new BankAccount("BA001", 1000.00, "John Doe");
        System.out.println("Account 1 Details: " + account1.getOwnerName() + " - " + account1.getAccountNumber() + " - Balance: $" + String.format("%.2f", account1.getBalance()));

        account1.deposit(200.00);
        account1.withdraw(500.00);
        account1.withdraw(800.00);

        System.out.println("\n--- Demonstrating SavingsAccount ---");
        SavingsAccount savings1 = new SavingsAccount("SA001", 1500.00, "Jane Smith", 0.03);
        System.out.println("Savings Account 1 Details: " + savings1.getOwnerName() + " - " + savings1.getAccountNumber() + " - Balance: $" + String.format("%.2f", savings1.getBalance()) + " - Interest Rate: " + (savings1.getInterestRate() * 100) + "%");

        savings1.deposit(300.00);
        savings1.withdraw(100.00);
        savings1.calculateInterest();
        System.out.println("Savings Account 1 Balance after interest: $" + String.format("%.2f", savings1.getBalance()));

        System.out.println("\n--- Demonstrating CurrentAccount ---");
        CurrentAccount current1 = new CurrentAccount("CA001", 500.00, "Alice Brown", 200.00);
        System.out.println("Current Account 1 Details: " + current1.getOwnerName() + " - " + current1.getAccountNumber() + " - Balance: $" + String.format("%.2f", current1.getBalance()) + " - Overdraft Limit: $" + String.format("%.2f", current1.getOverdraftLimit()));

        current1.deposit(150.00);
        current1.withdraw(600.00);
        System.out.println("Current Account 1 Balance after withdrawal: $" + String.format("%.2f", current1.getBalance()));

        current1.withdraw(100.00);
        System.out.println("Current Account 1 Balance after second withdrawal: $" + String.format("%.2f", current1.getBalance()));

        current1.withdraw(100.00);
        System.out.println("Current Account 1 Balance after failed withdrawal: $" + String.format("%.2f", current1.getBalance()));

        current1.deposit(300.00);
        System.out.println("Current Account 1 Balance after deposit: $" + String.format("%.2f", current1.getBalance()));
    }
}

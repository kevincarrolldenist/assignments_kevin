package org.assignment6;

import java.util.Scanner;

class ATM {
    double balance;

    public ATM(double initialBalance) {
        this.balance = initialBalance;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid amount entered.");
        }
        if (amount > balance) {
            throw new ArithmeticException("Insufficient balance. Available: " + balance);
        }
        balance -= amount;
        System.out.println("Withdrawal successful. Remaining balance: " + balance);
    }
}

public class bankATMDemo
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATM atm = new ATM(10000.00); // Initial balance

        System.out.println("Current ATM balance: " + atm.balance);
        System.out.print("Enter amount to withdraw: ");
        String input = scanner.nextLine();

        try {
            double amount = Double.parseDouble(input);
            atm.withdraw(amount);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number.");
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            scanner.close();
        }
    }
}



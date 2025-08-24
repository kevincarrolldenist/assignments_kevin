package com.demo.day4;

public class   bank {
    private String name;
    private String accountNumber;
    private double balance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "bank{" +
                "name='" + name + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                '}';
    }
    public void addfunds ( double amount){
        balance += amount;
    }
    public void withdrawFunds ( double amount){
        if (amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Insufficient funds for withdrawal.");

        }
    }
}

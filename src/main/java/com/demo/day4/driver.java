package com.demo.day4;

public class driver {
    public static void main(String[] args) {
//        employee emp=new employee();
//        employee emp2=new employee();
//
//        emp.setName("Kevin");
//        emp.setAge(21);
//        System.out.println(emp.getName());
//        System.out.println(emp.getAge());
//        System.out.println(emp2.toString());
//        System.out.println(emp.toString());
        bank b = new bank();
        b.setName("Kevin");
        b.setAccountNumber("123456789");
        b.setBalance(1000.50);
        double balance = b.getBalance();
        b.addfunds(200.00);
        System.out.println("Balance after adding funds: " + b.getBalance());
        b.withdrawFunds(150.00);
        System.out.println("Balance after withdrawal: " + b.getBalance());
        b.withdrawFunds(2000.00);
        System.out.println("Final balance: " + b.getBalance());
    }
}

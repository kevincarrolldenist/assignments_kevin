package com.demo.day4;

import java.util.EmptyStackException;

public class Inheritance {
    public static void main(String[] args) {
        Manager m = new Manager("John Doe", "john@example.com", 5000.00, 1000.00);
        Developer d = new Developer("Kevin", "kevin@example.com", 4500.00, 500.00);

        m.calculateSalary();

        d.calculateSalary();
    }
}
// class parent {
//     public void display() {
//         System.out.println("Parent class method");
//     }
// }
// class child extends parent {
//     @Override
//     public void display() {
//         System.out.println("Child class method");
//     }
// }
//    class grandChild extends child {
//        @Override
//        public void display() {
//            System.out.println("Grandchild class method");
//        }
//    }
class Emp {
    String name;
    String mail;
    double salary;

    Emp(String name, String mail, double salary) {
        this.name = name;
        this.mail = mail;
        this.salary = salary;
    }

    public double calculateSalary() {
        System.out.println("Name is: " + name);
        System.out.println("Mail is: " + mail);
        System.out.println("Salary is: " + salary);
        return salary;
    }
}
class Manager extends Emp {
    double bonus;

    public Manager(String name, String mail, double salary, double bonus) {
        super(name, mail, salary);
        this.bonus = bonus;
    }

    @Override
    public double calculateSalary() {
        double totalSalary = super.calculateSalary() + this.bonus;
        System.out.println("Bonus is: " + bonus);
        System.out.println("Total salary with bonus is: " + totalSalary);
        return totalSalary;
    }
}
class Developer extends Emp {
    double projectAllowance;

    public Developer(String name, String mail, double salary, double projectAllowance) {
        super(name, mail, salary);
        this.projectAllowance = projectAllowance;
    }

    @Override
    public double calculateSalary() {
        double totalSalary = super.calculateSalary() + this.projectAllowance;
        System.out.println("Project allowance is: " + projectAllowance);
        System.out.println("Total salary with allowance is: " + totalSalary);
        return totalSalary;
    }
}

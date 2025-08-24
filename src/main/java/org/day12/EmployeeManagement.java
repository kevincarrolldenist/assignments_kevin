package org.day12;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;
    private int experience;

    public Employee(int id, String name, String department, double salary, int experience) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.experience = experience;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public int getExperience() {
        return experience;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                ", experience=" + experience +
                '}';
    }
}

public class EmployeeManagement {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Alice", "IT", 60000.0, 5));
        employees.add(new Employee(2, "Bob", "HR", 45000.0, 3));
        employees.add(new Employee(3, "Charlie", "IT", 75000.0, 12));
        employees.add(new Employee(4, "David", "Finance", 55000.0, 7));
        employees.add(new Employee(5, "Eve", "HR", 50000.0, 10));
        employees.add(new Employee(6, "Frank", "IT", 90000.0, 15));
        employees.add(new Employee(7, "Grace", "Finance", 48000.0, 4));

        employees.stream()
                .filter(e -> e.getDepartment().equals("IT"))
                .forEach(e-> System.out.println(e));


        List<Employee> highEarners = employees.stream()
                .filter(e -> e.getSalary() > 50000.0)
                .toList();
        System.out.println("High Earners (salary > $50,000): " + highEarners);

        List<Employee> sortedBySalaryDesc = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .toList();
        System.out.println("Employees sorted by Salary (Descending): " + sortedBySalaryDesc);

        List<String> employeeNames = employees.stream()
                .map(Employee::getName)
                .toList();
        System.out.println("Employee Names: " + employeeNames);

        Optional<Employee> highestPaidEmployee = employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary));
        highestPaidEmployee.ifPresent(employee -> System.out.println("Highest Paid Employee: " + employee));

        Map<String, Double> averageSalaryByDepartment = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                ));
        System.out.println("Average Salary in Each Department: " + averageSalaryByDepartment);

        boolean hasExperiencedEmployee = employees.stream()
                .anyMatch(e -> e.getExperience() > 10);
        System.out.println("Any employee has more than 10 years of experience? " + hasExperiencedEmployee);

        long hrEmployeeCount = employees.stream()
                .filter(e -> e.getDepartment().equals("HR"))
                .count();
        System.out.println("Number of employees in HR Department: " + hrEmployeeCount);
    }
}

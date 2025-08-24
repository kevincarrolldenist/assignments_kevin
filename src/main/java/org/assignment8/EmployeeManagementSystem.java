package org.assignment8;

import java.util.ArrayList;
import java.util.Objects;

class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
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

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', department='" + department + "', salary=" + salary + "}";
    }
}

public class EmployeeManagementSystem {
    private ArrayList<Employee> employees;

    public EmployeeManagementSystem() {
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        System.out.println("Added employee: " + employee.getName());
    }

    public boolean updateEmployee(int id, double newSalary, String newDepartment) {
        for (Employee emp : employees) {
            if (emp.getId() == id) {
                emp.setSalary(newSalary);
                emp.setDepartment(newDepartment);
                System.out.println("Updated employee with ID: " + id);
                return true;
            }
        }
        System.out.println("Employee with ID " + id + " not found for update.");
        return false;
    }

    public boolean deleteEmployee(int id) {
        boolean removed = employees.removeIf(emp -> emp.getId() == id);
        if (removed) {
            System.out.println("Deleted employee with ID: " + id);
        } else {
            System.out.println("Employee with ID " + id + " not found for deletion.");
        }
        return removed;
    }

    public void displayAllEmployees() {
        System.out.println("\n--- Current Employees ---");
        if (employees.isEmpty()) {
            System.out.println("No employees to display.");
            return;
        }
        for (Employee emp : employees) {
            System.out.println(emp);
        }
        System.out.println("-------------------------");
    }

    public void searchEmployeeById(int id) {
        Employee searchDummy = new Employee(id, null, null, 0.0);

        if (employees.contains(searchDummy)) {
            int index = employees.indexOf(searchDummy);
            Employee foundEmployee = employees.get(index);
            System.out.println("\nFound employee by ID " + id + ": " + foundEmployee);
        } else {
            System.out.println("\nEmployee with ID " + id + " not found.");
        }
    }

    public static void main(String[] args) {
        EmployeeManagementSystem ems = new EmployeeManagementSystem();

        ems.addEmployee(new Employee(101, "Alice Smith", "IT", 75000.0));
        ems.addEmployee(new Employee(102, "Bob Johnson", "HR", 60000.0));
        ems.addEmployee(new Employee(103, "Charlie Brown", "Finance", 80000.0));

        ems.displayAllEmployees();

        ems.updateEmployee(101, 80000.0, "Software Development");
        ems.updateEmployee(999, 0.0, "NonExistent");

        ems.displayAllEmployees();

        ems.searchEmployeeById(102);
        ems.searchEmployeeById(500);

        ems.deleteEmployee(102);
        ems.deleteEmployee(999);

        ems.displayAllEmployees();
    }
}

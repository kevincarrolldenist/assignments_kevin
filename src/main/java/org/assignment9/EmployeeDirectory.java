package org.assignment9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

class Employee {
    private int id;
    private String name;
    private String department;

    public Employee(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
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
        return "Employee{id=" + id + ", name='" + name + "', department='" + department + "'}";
    }
}

public class EmployeeDirectory {
    private Set<Employee> employees;

    public EmployeeDirectory() {
        this.employees = new HashSet<>();
    }

    // Add a new employee
    public void addEmployee(Employee employee) {
        if (employees.add(employee)) {
            System.out.println("Added employee: " + employee);
        } else {
            System.out.println("Employee with ID " + employee.getId() + " already exists. Not added.");
        }
    }

    // Remove an employee by ID
    public boolean removeEmployee(int id) {
        Employee dummyEmployee = new Employee(id, null, null); // Create dummy for equals/hashCode comparison
        if (employees.remove(dummyEmployee)) {
            System.out.println("Removed employee with ID: " + id);
            return true;
        } else {
            System.out.println("Employee with ID " + id + " not found for removal.");
            return false;
        }
    }

    // Update an employee's department
    public boolean updateEmployeeDepartment(int id, String newDepartment) {
        for (Employee emp : employees) {
            if (emp.getId() == id) {
                emp.setDepartment(newDepartment);
                System.out.println("Updated department for employee ID " + id + " to " + newDepartment);
                return true;
            }
        }
        System.out.println("Employee with ID " + id + " not found for update.");
        return false;
    }

    // View all employees
    public void viewAllEmployees() {
        System.out.println("\n--- All Employees ---");
        if (employees.isEmpty()) {
            System.out.println("Directory is empty.");
            return;
        }
        for (Employee emp : employees) {
            System.out.println(emp);
        }
        System.out.println("---------------------");
    }

    // Find an employee by ID
    public Employee findEmployeeById(int id) {
        for (Employee emp : employees) {
            if (emp.getId() == id) {
                System.out.println("Found employee by ID " + id + ": " + emp);
                return emp;
            }
        }
        System.out.println("Employee with ID " + id + " not found.");
        return null;
    }

    // Find all employees in a given department
    public List<Employee> findAllEmployeesInDepartment(String department) {
        List<Employee> departmentEmployees = new ArrayList<>();
        for (Employee emp : employees) {
            if (emp.getDepartment().equalsIgnoreCase(department)) {
                departmentEmployees.add(emp);
            }
        }
        System.out.println("\n--- Employees in " + department + " Department ---");
        if (departmentEmployees.isEmpty()) {
            System.out.println("No employees found in " + department + " department.");
        } else {
            for (Employee emp : departmentEmployees) {
                System.out.println(emp);
            }
        }
        System.out.println("------------------------------------");
        return departmentEmployees;
    }

    // Display employees sorted by name
    public void displaySortedByName() {
        List<Employee> sortedList = new ArrayList<>(employees);
        Collections.sort(sortedList, Comparator.comparing(Employee::getName));
        System.out.println("\n--- Employees Sorted by Name ---");
        for (Employee emp : sortedList) {
            System.out.println(emp);
        }
        System.out.println("------------------------------");
    }

    // Display employees sorted by department and then by ID
    public void displaySortedByDepartmentAndId() {
        List<Employee> sortedList = new ArrayList<>(employees);
        Collections.sort(sortedList, Comparator.comparing(Employee::getDepartment)
                .thenComparing(Employee::getId));
        System.out.println("\n--- Employees Sorted by Department then ID ---");
        for (Employee emp : sortedList) {
            System.out.println(emp);
        }
        System.out.println("--------------------------------------------");
    }

    public static void main(String[] args) {
        EmployeeDirectory directory = new EmployeeDirectory();

        // Add employees
        directory.addEmployee(new Employee(101, "Alice Smith", "HR"));
        directory.addEmployee(new Employee(102, "Bob Johnson", "IT"));
        directory.addEmployee(new Employee(103, "Charlie Brown", "Finance"));
        directory.addEmployee(new Employee(104, "David Lee", "IT"));
        directory.addEmployee(new Employee(105, "Eve Davis", "HR"));
        directory.addEmployee(new Employee(101, "Alice Smith", "HR")); // Attempt to add duplicate

        directory.viewAllEmployees();

        // Update employee
        directory.updateEmployeeDepartment(102, "Software Engineering");
        directory.updateEmployeeDepartment(999, "NonExistent");

        directory.viewAllEmployees();

        // Search by ID
        directory.findEmployeeById(103);
        directory.findEmployeeById(999);

        // Search by Department
        directory.findAllEmployeesInDepartment("IT");
        directory.findAllEmployeesInDepartment("HR");
        directory.findAllEmployeesInDepartment("Marketing");

        // Remove employee
        directory.removeEmployee(104);
        directory.removeEmployee(999);

        directory.viewAllEmployees();

        // Sorting demonstrations
        directory.displaySortedByName();
        directory.displaySortedByDepartmentAndId();
    }
}


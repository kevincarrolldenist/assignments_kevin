package org.assignment3;

public class Employee {
    private int id;
    private String name;
    private double salary;
    private String department;

    public Employee(int id, String name, double salary, String department) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setId(int newId) {
        this.id = newId;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setSalary(double newSalary) {
        if (newSalary >= 0) {
            this.salary = newSalary;
        } else {
            System.out.println("Salary cannot be negative.");
        }
    }

    public void setDepartment(String newDepartment) {
        this.department = newDepartment;
    }

    public String displayEmployeeDetails() {
        return String.format("Employee ID: %d, Name: %s, Salary: $%.2f, Department: %s",
                getId(), getName(), getSalary(), getDepartment());
    }
}

 class Manager extends Employee {
    private int teamSize;
    private String projectName;

    public Manager(int id, String name, double salary, String department,
                   int teamSize, String projectName) {
        super(id, name, salary, department);
        this.teamSize = teamSize;
        this.projectName = projectName;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setTeamSize(int newTeamSize) {
        if (newTeamSize >= 0) {
            this.teamSize = newTeamSize;
        } else {
            System.out.println("Team size cannot be negative.");
        }
    }

    public void setProjectName(String newProjectName) {
        this.projectName = newProjectName;
    }

    public String displayManagerDetails() {
        return String.format("%s, Team Size: %d, Project Name: %s",
                displayEmployeeDetails(), getTeamSize(), getProjectName());
    }

    public static void main(String[] args) {
        System.out.println("--- Creating Employee and Manager Objects ---");

        Employee employee1 = new Employee(101, "Alice Wonderland", 65000.00, "Marketing");
        System.out.println("\nInitial Employee Details:");
        System.out.println(employee1.displayEmployeeDetails());

        Manager manager1 = new Manager(201, "Bob The Builder", 110000.00, "Engineering", 12, "Autonomous Driving System");
        System.out.println("\nInitial Manager Details:");
        System.out.println(manager1.displayManagerDetails());

        System.out.println("\n--- Demonstrating Setters ---");

        employee1.setSalary(70000.50);
        employee1.setDepartment("Digital Marketing");
        System.out.println("\nUpdated Employee Details:");
        System.out.println(employee1.displayEmployeeDetails());

        manager1.setTeamSize(15);
        manager1.setProjectName("Next-Gen Infotainment System");
        System.out.println("\nUpdated Manager Details:");
        System.out.println(manager1.displayManagerDetails());

        System.out.println("\n--- Accessing individual fields (using getters) ---");
        System.out.println("Employee 1's Name: " + employee1.getName());
        System.out.println("Manager 1's Project: " + manager1.getProjectName());
    }
}


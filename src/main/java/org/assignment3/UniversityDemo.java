package org.assignment3;

import java.util.ArrayList;
import java.util.List;

class Course {
    private String courseCode;
    private String courseName;

    public Course(String courseCode, String courseName) {
        this.courseCode = courseCode;
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return courseCode + " - " + courseName;
    }
}

 class Person {
    private String name;
    private String email;
    private int age;

    public Person(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void displayDetails() {
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Age: " + age);
    }
}

class Student extends Person {
    private String studentId;
    private List<Course> courseList;

    public Student(String name, String email, int age, String studentId) {
        super(name, email, age);
        this.studentId = studentId;
        this.courseList = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void registerCourse(Course course) {
        if (!courseList.contains(course)) {
            courseList.add(course);
            System.out.println(getName() + " (" + studentId + ") registered for: " + course.getCourseName());
        } else {
            System.out.println(getName() + " is already registered for: " + course.getCourseName());
        }
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Student ID: " + studentId);
        if (courseList.isEmpty()) {
            System.out.println("Courses: No courses registered.");
        } else {
            System.out.println("Courses Registered:");
            for (Course course : courseList) {
                System.out.println("  - " + course.getCourseCode() + ": " + course.getCourseName());
            }
        }
    }
}

class Professor extends Person {
    private String subjectTaught;
    private String employeeId;

    public Professor(String name, String email, int age, String subjectTaught, String employeeId) {
        super(name, email, age);
        this.subjectTaught = subjectTaught;
        this.employeeId = employeeId;
    }

    public String getSubjectTaught() {
        return subjectTaught;
    }

    public void setSubjectTaught(String subjectTaught) {
        this.subjectTaught = subjectTaught;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Subject Taught: " + subjectTaught);
    }
}

public class UniversityDemo {
    public static void main(String[] args) {
        Course mathCourse = new Course("MATH101", "Calculus I");
        Course csCourse = new Course("CS201", "Data Structures");
        Course physicsCourse = new Course("PHY101", "General Physics");

        System.out.println("--- Creating Students ---");
        Student student1 = new Student("Alice Smith", "alice.s@example.com", 20, "S001");
        Student student2 = new Student("Bob Johnson", "bob.j@example.com", 22, "S002");

        System.out.println("\n--- Registering Courses for Students ---");
        student1.registerCourse(mathCourse);
        student1.registerCourse(csCourse);
        student1.registerCourse(mathCourse);

        student2.registerCourse(csCourse);
        student2.registerCourse(physicsCourse);

        System.out.println("\n--- Creating Professors ---");
        Professor prof1 = new Professor("Dr. Emily White", "emily.w@example.com", 45, "Mathematics", "P001");
        Professor prof2 = new Professor("Dr. David Green", "david.g@example.com", 50, "Computer Science", "P002");

        System.out.println("\n--- Displaying Student Details ---");
        student1.displayDetails();
        System.out.println("--------------------");
        student2.displayDetails();

        System.out.println("\n--- Displaying Professor Details ---");
        prof1.displayDetails();
        System.out.println("--------------------");
        prof2.displayDetails();

        System.out.println("\n--- Demonstrating Setters ---");
        student1.setEmail("alice.smith@university.edu");
        System.out.println("\nUpdated Email for Alice: " + student1.getEmail());

        prof1.setSubjectTaught("Advanced Calculus");
        System.out.println("Updated Subject for Dr. White: " + prof1.getSubjectTaught());

        System.out.println("\n--- Displaying Updated Details ---");
        student1.displayDetails();
        System.out.println("--------------------");
        prof1.displayDetails();
    }
}

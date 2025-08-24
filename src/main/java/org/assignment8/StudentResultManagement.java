package org.assignment8;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

class Student implements Comparable<Student> {
    private int rollNo;
    private String name;
    private double marks;

    public Student(int rollNo, String name, double marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.marks = marks;
    }

    public int getRollNo() {
        return rollNo;
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    @Override
    public int compareTo(Student other) {
        return Double.compare(other.marks, this.marks);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return rollNo == student.rollNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rollNo);
    }

    @Override
    public String toString() {
        return "Student{rollNo=" + rollNo + ", name='" + name + "', marks=" + marks + "}";
    }
}

class StudentNameComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return s1.getName().compareTo(s2.getName());
    }
}

class StudentRollNoComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return Integer.compare(s1.getRollNo(), s2.getRollNo());
    }
}

public class StudentResultManagement {
    private List<Student> students;

    public StudentResultManagement() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        if (!students.contains(student)) {
            students.add(student);
            System.out.println("Added student: " + student.getName());
        } else {
            System.out.println("Student with Roll No " + student.getRollNo() + " already exists.");
        }
    }

    public boolean deleteStudent(int rollNo) {
        Student dummyStudent = new Student(rollNo, null, 0.0);
        boolean removed = students.remove(dummyStudent);
        if (removed) {
            System.out.println("Deleted student with Roll No: " + rollNo);
        } else {
            System.out.println("Student with Roll No " + rollNo + " not found for deletion.");
        }
        return removed;
    }

    public boolean updateMarks(int rollNo, double newMarks) {
        for (Student student : students) {
            if (student.getRollNo() == rollNo) {
                student.setMarks(newMarks);
                System.out.println("Updated marks for student Roll No " + rollNo + ". New marks: " + newMarks);
                return true;
            }
        }
        System.out.println("Student with Roll No " + rollNo + " not found for marks update.");
        return false;
    }

    public Student searchStudent(int rollNo) {
        Student dummyStudent = new Student(rollNo, null, 0.0);
        int index = students.indexOf(dummyStudent);
        if (index != -1) {
            Student foundStudent = students.get(index);
            System.out.println("Found student by Roll No " + rollNo + ": " + foundStudent);
            return foundStudent;
        } else {
            System.out.println("Student with Roll No " + rollNo + " not found.");
            return null;
        }
    }

    public void displayStudents(String title) {
        System.out.println("\n--- " + title + " ---");
        if (students.isEmpty()) {
            System.out.println("No students to display.");
            return;
        }
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println("-------------------------");
    }

    public static void main(String[] args) {
        StudentResultManagement srm = new StudentResultManagement();

        srm.addStudent(new Student(101, "Alice", 85.5));
        srm.addStudent(new Student(103, "Charlie", 92.0));
        srm.addStudent(new Student(102, "Bob", 78.0));
        srm.addStudent(new Student(105, "David", 88.0));
        srm.addStudent(new Student(104, "Eve", 95.0));
        srm.addStudent(new Student(101, "Alice Duplicate", 80.0));

        srm.displayStudents("Initial Student List");

        srm.updateMarks(102, 80.5);
        srm.updateMarks(999, 70.0);

        srm.displayStudents("After Marks Update");

        srm.searchStudent(103);
        srm.searchStudent(500);

        srm.deleteStudent(101);
        srm.deleteStudent(999);

        srm.displayStudents("After Deletion");


        Collections.sort(srm.students);
        srm.displayStudents("Students Sorted by Marks (Descending)");

        Collections.sort(srm.students, new StudentNameComparator());
        srm.displayStudents("Students Sorted by Name (Alphabetical)");

        Collections.sort(srm.students, new StudentRollNoComparator());
        srm.displayStudents("Students Sorted by Roll No (Ascending)");
    }
}


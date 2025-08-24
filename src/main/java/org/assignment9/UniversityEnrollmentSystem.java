package org.assignment9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

class Course {
    private int courseId;
    private String courseName;

    public Course(int courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return courseId == course.courseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId);
    }

    @Override
    public String toString() {
        return "Course{courseId=" + courseId + ", courseName='" + courseName + "'}";
    }
}

class Student {
    private int studentId;
    private String name;

    public Student(int studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentId == student.studentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }

    @Override
    public String toString() {
        return "Student{studentId=" + studentId + ", name='" + name + "'}";
    }
}

public class UniversityEnrollmentSystem {
    private Set<Course> availableCourses;
    private Map<Student, Set<Course>> studentRegistrations;

    public UniversityEnrollmentSystem() {
        this.availableCourses = new HashSet<>();
        this.studentRegistrations = new HashMap<>();
    }

    public void addCourse(Course course) {
        if (availableCourses.add(course)) {
            System.out.println("Added new course: " + course.getCourseName());
        } else {
            System.out.println("Course with ID " + course.getCourseId() + " already exists: " + course.getCourseName());
        }
    }

    public void registerStudentForCourses(Student student, Set<Course> coursesToRegister) {
        studentRegistrations.putIfAbsent(student, new HashSet<>());
        Set<Course> studentCourseSet = studentRegistrations.get(student);

        System.out.println("Registering " + student.getName() + " for courses:");
        for (Course course : coursesToRegister) {
            if (availableCourses.contains(course)) {
                if (studentCourseSet.add(course)) {
                    System.out.println("  - Enrolled in: " + course.getCourseName());
                } else {
                    System.out.println("  - Already enrolled in: " + course.getCourseName());
                }
            } else {
                System.out.println("  - Failed to enroll in: " + course.getCourseName() + " (Course not available)");
            }
        }
    }

    public void updateStudentRegistration(Student student, Set<Course> coursesToAdd, Set<Course> coursesToDrop) {
        if (!studentRegistrations.containsKey(student)) {
            System.out.println("Student " + student.getName() + " is not registered in the system.");
            return;
        }

        Set<Course> studentCurrentCourses = studentRegistrations.get(student);
        System.out.println("Updating registration for " + student.getName() + ":");

        if (coursesToDrop != null) {
            for (Course course : coursesToDrop) {
                if (studentCurrentCourses.remove(course)) {
                    System.out.println("  - Dropped: " + course.getCourseName());
                } else {
                    System.out.println("  - Not enrolled in (cannot drop): " + course.getCourseName());
                }
            }
        }

        if (coursesToAdd != null) {
            for (Course course : coursesToAdd) {
                if (availableCourses.contains(course)) {
                    if (studentCurrentCourses.add(course)) {
                        System.out.println("  - Added: " + course.getCourseName());
                    } else {
                        System.out.println("  - Already enrolled in (cannot add again): " + course.getCourseName());
                    }
                } else {
                    System.out.println("  - Failed to add (course not available): " + course.getCourseName());
                }
            }
        }
    }

    public void removeStudent(int studentId) {
        Student dummyStudent = new Student(studentId, null);
        if (studentRegistrations.remove(dummyStudent) != null) {
            System.out.println("Removed student with ID " + studentId + " from the system.");
        } else {
            System.out.println("Student with ID " + studentId + " not found for removal.");
        }
    }

    public Set<Course> findCoursesTakenByStudent(int studentId) {
        Student dummyStudent = new Student(studentId, null);
        for (Map.Entry<Student, Set<Course>> entry : studentRegistrations.entrySet()) {
            if (entry.getKey().equals(dummyStudent)) {
                System.out.println("\nCourses taken by " + entry.getKey().getName() + ":");
                if (entry.getValue().isEmpty()) {
                    System.out.println("  (No courses registered)");
                } else {
                    for (Course course : entry.getValue()) {
                        System.out.println("  - " + course.getCourseName());
                    }
                }
                return entry.getValue();
            }
        }
        System.out.println("\nStudent with ID " + studentId + " not found.");
        return null;
    }

    public Set<Student> findStudentsRegisteredForCourse(int courseId) {
        Course dummyCourse = new Course(courseId, null);
        Set<Student> registeredStudents = new HashSet<>();
        String courseName = "Unknown Course";

        for(Course c : availableCourses) {
            if (c.equals(dummyCourse)) {
                courseName = c.getCourseName();
                break;
            }
        }

        System.out.println("\nStudents registered for " + courseName + " (ID: " + courseId + "):");
        for (Map.Entry<Student, Set<Course>> entry : studentRegistrations.entrySet()) {
            if (entry.getValue().contains(dummyCourse)) {
                registeredStudents.add(entry.getKey());
                System.out.println("  - " + entry.getKey().getName());
            }
        }

        if (registeredStudents.isEmpty()) {
            System.out.println("  (No students registered for this course)");
        }
        return registeredStudents;
    }

    public void displayAllCoursesSortedByName() {
        List<Course> sortedCourses = new ArrayList<>(availableCourses);
        Collections.sort(sortedCourses, Comparator.comparing(Course::getCourseName));
        System.out.println("\n--- All Courses Sorted by Name ---");
        if (sortedCourses.isEmpty()) {
            System.out.println("No courses available.");
            return;
        }
        for (Course course : sortedCourses) {
            System.out.println(course);
        }
        System.out.println("----------------------------------");
    }

    public void displayAllStudentsSortedByName() {
        List<Student> sortedStudents = new ArrayList<>(studentRegistrations.keySet());
        Collections.sort(sortedStudents, Comparator.comparing(Student::getName));
        System.out.println("\n--- All Registered Students Sorted by Name ---");
        if (sortedStudents.isEmpty()) {
            System.out.println("No students registered.");
            return;
        }
        for (Student student : sortedStudents) {
            System.out.println(student);
        }
        System.out.println("--------------------------------------------");
    }

    public static void main(String[] args) {
        UniversityEnrollmentSystem ues = new UniversityEnrollmentSystem();

        System.out.println("--- Adding Courses ---");
        Course math = new Course(101, "Calculus I");
        Course physics = new Course(102, "Physics I");
        Course cs = new Course(103, "Introduction to CS");
        Course chemistry = new Course(104, "General Chemistry");
        Course literature = new Course(105, "World Literature");

        ues.addCourse(math);
        ues.addCourse(physics);
        ues.addCourse(cs);
        ues.addCourse(chemistry);
        ues.addCourse(literature);
        ues.addCourse(new Course(101, "Calculus I Duplicate"));

        ues.displayAllCoursesSortedByName();

        Student alice = new Student(1001, "Alice");
        Student bob = new Student(1002, "Bob");
        Student charlie = new Student(1003, "Charlie");

        System.out.println("\n--- Registering Students ---");
        Set<Course> aliceCourses = new HashSet<>();
        aliceCourses.add(math);
        aliceCourses.add(cs);
        aliceCourses.add(new Course(999, "NonExistentCourse"));
        ues.registerStudentForCourses(alice, aliceCourses);

        Set<Course> bobCourses = new HashSet<>();
        bobCourses.add(physics);
        bobCourses.add(math);
        ues.registerStudentForCourses(bob, bobCourses);

        Set<Course> charlieCourses = new HashSet<>();
        charlieCourses.add(chemistry);
        charlieCourses.add(literature);
        ues.registerStudentForCourses(charlie, charlieCourses);

        System.out.println("\n--- Re-registering Alice for CS ---");
        Set<Course> aliceReReg = new HashSet<>();
        aliceReReg.add(cs);
        ues.registerStudentForCourses(alice, aliceReReg);

        System.out.println("\n--- Updating Alice's Registration ---");
        Set<Course> aliceDrop = new HashSet<>();
        aliceDrop.add(math);
        aliceDrop.add(literature);
        Set<Course> aliceAdd = new HashSet<>();
        aliceAdd.add(physics);
        ues.updateStudentRegistration(alice, aliceAdd, aliceDrop);

        ues.findCoursesTakenByStudent(1001);
        ues.findCoursesTakenByStudent(1002);
        ues.findCoursesTakenByStudent(9999);

        ues.findStudentsRegisteredForCourse(101);
        ues.findStudentsRegisteredForCourse(103);
        ues.findStudentsRegisteredForCourse(999);

        System.out.println("\n--- Removing Bob ---");
        ues.removeStudent(1002);
        ues.removeStudent(9999);

        ues.findCoursesTakenByStudent(1002);
        ues.findCoursesTakenByStudent(1001);

        ues.displayAllStudentsSortedByName();
    }
}

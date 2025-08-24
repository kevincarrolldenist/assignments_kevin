package org.assignment4;

// Course.java
abstract class Course {
    String courseName;
    String duration;

    public Course(String courseName, String duration) {
        this.courseName = courseName;
        this.duration = duration;
    }

    public abstract void enrollStudent(String studentName);
    public abstract void startCourse();
}

// CertificateProvider.java
interface CertificateProvider {
    void generateCertificate(String studentName);
}

// ProgrammingCourse.java
class ProgrammingCourse extends Course implements CertificateProvider {
    public ProgrammingCourse(String courseName, String duration) {
        super(courseName, duration);
    }

    @Override
    public void enrollStudent(String studentName) {
        System.out.println("Enrolling " + studentName + " in " + courseName);
    }

    @Override
    public void startCourse() {
        System.out.println("Starting " + courseName);
    }

    @Override
    public void generateCertificate(String studentName) {
        System.out.println("Certificate generated for " + studentName + " in " + courseName);
    }
}

// DesignCourse.java
class DesignCourse extends Course implements CertificateProvider {
    public DesignCourse(String courseName, String duration) {
        super(courseName, duration);
    }

    @Override
    public void enrollStudent(String studentName) {
        System.out.println("Enrolling " + studentName + " in " + courseName);
    }

    @Override
    public void startCourse() {
        System.out.println("Starting " + courseName);
    }

    @Override
    public void generateCertificate(String studentName) {
        System.out.println("Certificate generated for " + studentName + " in " + courseName);
    }
}

// Main.java
public class OnlineLearning {
    public static void main(String[] args) {
        ProgrammingCourse javaCourse = new ProgrammingCourse("Java Programming Course", "8 weeks");
        DesignCourse graphicDesignCourse = new DesignCourse("Graphic Design Course", "6 weeks");

        javaCourse.enrollStudent("John");
        javaCourse.startCourse();
        javaCourse.generateCertificate("John");

        graphicDesignCourse.enrollStudent("Alice");
        graphicDesignCourse.startCourse();
        graphicDesignCourse.generateCertificate("Alice");
    }
}


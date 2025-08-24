package org.assignment3;

class Patient {
    private String patientId;
    private String name;
    private int age;

    public Patient(String patientId, String name, int age) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void displayPatientInfo() {
        System.out.println("Patient ID: " + patientId);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }
}

class InPatient extends Patient {
    private String roomNumber;
    private String admissionDate;

    public InPatient(String patientId, String name, int age, String roomNumber, String admissionDate) {
        super(patientId, name, age);
        this.roomNumber = roomNumber;
        this.admissionDate = admissionDate;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }

    @Override
    public void displayPatientInfo() {
        super.displayPatientInfo();
        System.out.println("Room Number: " + roomNumber);
        System.out.println("Admission Date: " + admissionDate);
        System.out.println("--------------------");
    }
}

class OutPatient extends Patient {
    private String visitDate;
    private double consultationFee;

    public OutPatient(String patientId, String name, int age, String visitDate, double consultationFee) {
        super(patientId, name, age);
        this.visitDate = visitDate;
        this.consultationFee = consultationFee;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(double consultationFee) {
        this.consultationFee = consultationFee;
    }

    @Override
    public void displayPatientInfo() {
        super.displayPatientInfo();
        System.out.println("Visit Date: " + visitDate);
        System.out.println("Consultation Fee: $" + consultationFee);
        System.out.println("--------------------");
    }
}

public class HospitalPatientRecords {
    public static void main(String[] args) {
        InPatient inPatient1 = new InPatient("IP001", "Alice Smith", 45, "201A", "2023-07-15");
        OutPatient outPatient1 = new OutPatient("OP001", "Bob Johnson", 30, "2023-08-10", 75.50);

        System.out.println("In-Patient Record:");
        inPatient1.displayPatientInfo();

        System.out.println("\nOut-Patient Record:");
        outPatient1.displayPatientInfo();
    }
}


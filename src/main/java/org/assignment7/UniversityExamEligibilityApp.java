package org.assignment7;

public class UniversityExamEligibilityApp {

    public static void main(String[] args) {
        ExamEligibility eligibilityChecker = new ExamEligibility();

        System.out.println("Checking eligibility for Alice (Attendance: 80%)...");
        try {
            eligibilityChecker.checkEligibility("Alice", 80.0);
            System.out.println("Eligible for exam.");
        } catch (LowAttendanceException e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println("\nChecking eligibility for Bob (Attendance: 65%)...");
        try {
            eligibilityChecker.checkEligibility("Bob", 65.0);
            System.out.println("Eligible for exam.");
        } catch (LowAttendanceException e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println("\nChecking eligibility for Charlie (Attendance: 75%)...");
        try {
            eligibilityChecker.checkEligibility("Charlie", 75.0);
            System.out.println("Eligible for exam.");
        } catch (LowAttendanceException e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println("\nChecking eligibility for David (Attendance: 92.5%)...");
        try {
            eligibilityChecker.checkEligibility("David", 92.5);
            System.out.println("Eligible for exam.");
        } catch (LowAttendanceException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

class LowAttendanceException extends Exception {
    public LowAttendanceException(String message) {
        super(message);
    }
}

class ExamEligibility {
    private static final double MIN_ATTENDANCE_PERCENT = 75.0;

    public void checkEligibility(String studentName, double attendancePercent) throws LowAttendanceException {
        if (attendancePercent < MIN_ATTENDANCE_PERCENT) {
            throw new LowAttendanceException("Attendance below " + MIN_ATTENDANCE_PERCENT + "%. Not eligible for exam.");
        }
    }
}


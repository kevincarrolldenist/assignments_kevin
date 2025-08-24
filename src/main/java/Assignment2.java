import java.util.Scanner;
public class Assignment2 {
    public static void main(String[] args) {
//        //assignment 6
//        char delivery[] = {'p', 'd', 'c', 'f', 'f','c','c','p','p','d'};
//        int countP = 0, countD = 0, countC = 0, countF = 0;
//        for (char c : delivery) {
//            switch (c) {
//                case 'p':
//                    countP++;
//                    break;
//                case 'd':
//                    countD++;
//                    break;
//                case 'c':
//                    countC++;
//                    break;
//                case 'f':
//                    countF++;
//                    break;
//                default:
//                    System.out.println("Invalid delivery type: " + c);
//
//    }
//        }
//        System.out.println("Count of pending deleveries: " + countP);
//        System.out.println("Count of Delivered deliveries " + countD);
//        System.out.println("Count of Cancelled deliveries: " + countC);
//        System.out.println("Count of Failed deliveries: " + countF);

//        //assignment7
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Welcome to the SaaS Product User Access Management System");
//        while (true) {
//            System.out.println("Select Your Role");
//            System.out.println("1. Admin");
//            System.out.println("2. Manager");
//            System.out.println("3. Viewer");
//            System.out.println("4. Exit");
//            System.out.print("Enter your choice (1-4): ");
//
//            int choice;
//                choice = Integer.parseInt(scanner.nextLine());
//                switch (choice) {
//                    case 1:
//                        System.out.println("Permissions for Admin: Can manage users and settings");
//                        break;
//                    case 2:
//                        System.out.println("Permissions for Manager: Can generate reports and approve requests");
//                        break;
//                    case 3:
//                        System.out.println("Permissions for Viewer: Read-only access");
//                        break;
//                    case 4:
//                        System.out.println("Exiting the User Access Management System");
//                        scanner.close();
//                        return;
//                    default:
//                        System.out.println("Invalid choice. Please enter a number between 1 and 4.");
//                        break;
//                }
//            }
//        //Assignment 8: Toll Booth Billing System
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Welcome to the Toll Booth Billing System");
//        System.out.print("Enter the vehicle type (car, truck, bus): ");
//        String vehicleType = scanner.nextLine().toLowerCase();
//        System.out.print("Enter the number of axles: ");
//        int axles = Integer.parseInt(scanner.nextLine());
//        double tollAmount = 0.0;
//        switch (vehicleType) {
//            case "car":
//                tollAmount = 2.0 * axles; // $2 per axle for cars
//                break;
//            case "truck":
//                tollAmount = 3.0 * axles; // $3 per axle for trucks
//                break;
//            case "bus":
//                tollAmount = 4.0 * axles; // $4 per axle for buses
//                break;
//            default:
//                System.out.println("Invalid vehicle type. Please enter car, truck, or bus.");
//                return;
//        }
//        System.out.printf("The toll amount for your %s with %d axles is: $%.2f%n", vehicleType, axles, tollAmount);
//        System.out.println("Thank you for using the Toll Booth Billing System. Have a safe journey!");
//        scanner.close();
        //Assignment 9: Employee Attendance Summary
//        For each employee:
//        Count Present (P), Absent (A), and Leave (L)
//        Display attendance summary
//        char[][] attendance = {
//                {'P','P','A','P','L','P','P'},
//                {'P','A','A','P','P','P','L'},
//                {'P','P','P','P','P','P','P'}
//        };
//        int empcount=1;
//        for (char[] row : attendance) {
//
//            int countP = 0, countA = 0, countL = 0;
//            for (char status : row) {
//                switch (status) {
//                    case 'P':
//                        countP++;
//                        break;
//                    case 'A':
//                        countA++;
//                        break;
//                    case 'L':
//                        countL++;
//                        break;
//                    default:
//                        System.out.println("Invalid attendance status: " + status);
//                }
//            }
//            System.out.println("Attendance Summary of employee no:"+empcount);
//            System.out.println("Present: " + countP);
//            System.out.println("Absent: " + countA);
//            System.out.println("Leave: " + countL);
//            empcount++;
//        }
//
//

//        Assignment 10: Dynamic Tax Bracket Calculator
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Dynamic Tax Bracket Calculator");
        System.out.print("Enter your monthly salary: ");
        double monthlySalary = Double.parseDouble(scanner.nextLine());
        double annualSalary = monthlySalary * 12;
        double tax = 0.0;

        if (annualSalary <= 250000) {
            tax = 0.0;
        } else if (annualSalary <= 500000) {
            tax += (annualSalary - 250000) * 0.05;
        } else if (annualSalary <= 1000000) {
            tax += (500000 - 250000) * 0.05;
            tax += (annualSalary - 500000) * 0.20;
        } else {
            tax += (500000 - 250000) * 0.05;
            tax += (1000000 - 500000) * 0.20;
            tax += (annualSalary - 1000000) * 0.30;
        }

        double netSalary = annualSalary - tax;

        System.out.printf("--- Salary Calculation Summary ---%n");
        System.out.printf("Monthly Salary: $%.2f%n", monthlySalary);
        System.out.printf("Annual Salary:  $%.2f%n", annualSalary);
        System.out.printf("Total Tax:      $%.2f%n", tax);
        System.out.printf("Net Salary:     $%.2f%n", netSalary);

        scanner.close();

    }
}

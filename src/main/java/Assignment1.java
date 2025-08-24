import java.sql.SQLOutput;

public class Assignment1 {
    public static void main(String[] args) {
//    //assignment 1
//        Double principal=200.54;
//        Float rate=0.5f;
//        Integer tenure=36;
//        Double emi = (principal * rate * Math.pow(1 + rate, tenure)) / (Math.pow(1 + rate, tenure) - 1);
//        String formattedEmi = String.format("%.2f", emi);
//
//        System.out.println("The EMI is: " + formattedEmi);
//
//        Double total=emi+principal;
//        String formattedTotal = String.format("%.2f", total);
//        System.out.println("The total amount to be paid is: " + formattedTotal);
//
//         //assignment 2
//        Integer signal=0b0010;
//        Integer light=signal & 1;
//        Integer motion=(signal>>1) & 1;
//        if(light==0 && motion==1)
//        {
//            Integer result=signal | 0b0100;
//            System.out.println("the light is on"+ " " + result);
//        }
//        //assignment 3
//        Integer price1=100;
//        Integer price2=200;
//        Integer price3=300;
//        Integer lowestPrice = price1;
//        if (price2 < lowestPrice) lowestPrice = price2;
//        if (price3 < lowestPrice) lowestPrice = price3;
//
//        Integer highestPrice = price1;
//        if (price2 > highestPrice) highestPrice = price2;
//        if (price3 > highestPrice) highestPrice = price3
//        Integer priceDifference = highestPrice - lowestPrice;
//        System.out.println("Lowest Price: " + lowestPrice);
//        System.out.println("price 1: " + price1);
//        System.out.println("price 2: " + price2);
//        System.out.println("price 3: " + price3);
//        System.out.println("Difference between highest and lowest price: " + priceDifference);
//        //assignment 4
//
//                double monthlySalary = 75000.00;
//
//                double annualSalary = monthlySalary * 12;
//
//                double tax = 0.0;
//
//                if (annualSalary <= 250000) {
//                    tax = 0.0;
//                } else if (annualSalary <= 500000) {
//                    tax += (annualSalary - 250000) * 0.05;
//                } else if (annualSalary <= 1000000) {
//                    tax += (500000 - 250000) * 0.05;
//                    tax += (annualSalary - 500000) * 0.20;
//                } else {
//                    tax += (500000 - 250000) * 0.05;
//                    tax += (1000000 - 500000) * 0.20;
//                    tax += (annualSalary - 1000000) * 0.30;
//                }
//
//                double netSalary = annualSalary;
//                netSalary -= tax;
//
//                int taxAsInt = (int) tax;
//
//                System.out.println("--- Salary Calculation Summary (Initialized Values) ---");
//                System.out.printf("Monthly Salary: $%.2f%n", monthlySalary);
//                System.out.printf("Annual Salary:  $%.2f%n", annualSalary);
//                System.out.printf("Total Tax:      $%.2f%n", tax);
//                System.out.printf("Net Salary:     $%.2f%n", netSalary);
//
        //assignment 5
//
//        List<String> patientAgesStr = new ArrayList<>(Arrays.asList(
//                        "5", "10", "15", "20", "30", "60", "70", "85", "1", "18", "25", "55", "65", "90", "12"
//                ));
//
//                int childCount = 0;
//                int teenCount = 0;
//                int adultCount = 0;
//                int seniorCount = 0;
//                int totalPatients = 0;
//
//                for (String ageStr : patientAgesStr) {
//                    int age = Integer.parseInt(ageStr);
//                    totalPatients++;
//
//                    if (age >= 0 && age <= 12) {
//                        childCount++;
//                    } else if (age >= 13 && age <= 19) {
//                        teenCount++;
//                    } else if (age >= 20 && age <= 64) {
//                        adultCount++;
//                    } else if (age >= 65) {
//                        seniorCount++;
//                    } else {
//                        totalPatients--;
//                    }
//                }
//
//                System.out.println("--- Patient Category Summary ---");
//                System.out.println("Children (0-12): " + childCount);
//                System.out.println("Teens (13-19):   " + teenCount);
//                System.out.println("Adults (20-64):  " + adultCount);
//                System.out.println("Seniors (65+):   " + seniorCount);
//                System.out.println("Total Patients:  " + totalPatients);
//
//                if (totalPatients > 0) {
//                    System.out.println("\n--- Category Percentages ---");
//                    System.out.printf("Children: %.2f%%%n", ((double) childCount / totalPatients) * 100);
//                    System.out.printf("Teens:    %.2f%%%n", ((double) teenCount / totalPatients) * 100);
//                    System.out.printf("Adults:   %.2f%%%n", ((double) adultCount / totalPatients) * 100);
//                    System.out.printf("Seniors:  %.2f%%%n", ((double) seniorCount / totalPatients) * 100);
//                }
    }
}

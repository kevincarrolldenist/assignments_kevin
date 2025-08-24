package org.assignment4;

abstract class Loan {
    public double calculateEMI(double principal, double rate, int tenureMonths) {
        double monthlyRate = (rate / 100) / 12;

        if (monthlyRate == 0) {
            return principal / tenureMonths;
        }

        double emi = principal * monthlyRate * Math.pow(1 + monthlyRate, tenureMonths) /
                (Math.pow(1 + monthlyRate, tenureMonths) - 1);
        return emi;
    }
}

interface ApprovalProcess {
    void approveLoan();
}

class HomeLoan extends Loan implements ApprovalProcess {
    @Override
    public void approveLoan() {
        System.out.println("Home Loan Approved");
    }
}

class CarLoan extends Loan implements ApprovalProcess {
    @Override
    public void approveLoan() {
        System.out.println("Car Loan Approved");
    }
}

public class LoanSystem {
    public static void main(String[] args) {
        HomeLoan homeLoan = new HomeLoan();
        CarLoan carLoan = new CarLoan();

        double homePrincipal = 50000;
        double homeRate = 15.0;
        int homeTenure = 60;

        double homeEMI = homeLoan.calculateEMI(homePrincipal, homeRate, homeTenure);
        System.out.printf("Home Loan EMI for $%.0f is $%.0f/month%n", homePrincipal, homeEMI);

        homeLoan.approveLoan();

        double carPrincipal = 20000;
        double carRate = 12.0;
        int carTenure = 60;

        double carEMI = carLoan.calculateEMI(carPrincipal, carRate, carTenure);
        System.out.printf("Car Loan EMI for $%.0f is $%.0f/month%n", carPrincipal, carEMI);

        carLoan.approveLoan();
    }
}

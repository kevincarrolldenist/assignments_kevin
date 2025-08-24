public class day6 {

    // Part 1: Static Polymorphism (Method Overloading)
    static class DiscountCalculator {

        public double calculateDiscount(double price, double discountPercent) {
            return price * (discountPercent / 100.0);
        }

        public double calculateDiscount(double price, double discountAmount, boolean isFlat) {
            if (isFlat) {
                return discountAmount;
            } else {
                return price * (discountAmount / 100.0);
            }
        }

        public double calculateDiscount(double price) {
            return price * 0.05;
        }
    }

    // Part 2: Dynamic Polymorphism (Method Overriding)
    static class Payment {
        public void processPayment(double amount) {
            System.out.println("Processing generic payment of Rs. " + amount);
        }
    }

    static class CreditCardPayment extends Payment {
        @Override
        public void processPayment(double amount) {
            System.out.println("Processing credit card payment of Rs. " + amount);
        }
    }

    static class UPIPayment extends Payment {
        @Override
        public void processPayment(double amount) {
            System.out.println("Processing UPI payment of Rs. " + amount);
        }
    }

    static class CashPayment extends Payment {
        @Override
        public void processPayment(double amount) {
            System.out.println("Processing cash payment of Rs. " + amount);
        }
    }

    public static void main(String[] args) {
        // Part 1: Static Polymorphism demonstration
        System.out.println("Part 1: Static Polymorphism (Method Overloading)");
        DiscountCalculator calculator = new DiscountCalculator();

        double price1 = 1000.0;
        double discountPercent = 10.0;
        double discountValue1 = calculator.calculateDiscount(price1, discountPercent);
        System.out.println("Discount using percent: " + discountValue1);

        double price2 = 500.0;
        double flatDiscountAmount = 50.0;
        double discountValue2 = calculator.calculateDiscount(price2, flatDiscountAmount, true);
        System.out.println("Discount using flat amount: " + discountValue2);

        double price3 = 500.0;
        double discountValue3 = calculator.calculateDiscount(price3);
        System.out.println("Discount using default: " + discountValue3);

        System.out.println();

        // Part 2: Dynamic Polymorphism demonstration
        System.out.println("Part 2: Dynamic Polymorphism (Method Overriding)");
        Payment payment1 = new CreditCardPayment();
        payment1.processPayment(500.0);

        Payment payment2 = new UPIPayment();
        payment2.processPayment(300.0);

        Payment payment3 = new CashPayment();
        payment3.processPayment(200.0);
    }
}


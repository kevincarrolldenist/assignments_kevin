package org.assignment5;

public class OnlineShoppingSystem {

    public static void main(String[] args) {
        double itemPrice = 100.0;

        System.out.println("--- Demonstrating Static Polymorphism (Overloading) ---");
        Customer genericCustomer = new Customer();
        System.out.println("Scenario 1: Customer purchase without loyalty points.");
        double priceAfterNoLoyaltyDiscount = genericCustomer.calculateDiscount(itemPrice);
        System.out.println("  Original Price: $" + itemPrice + ", Price after default calculation: $" + priceAfterNoLoyaltyDiscount + "\n");

        System.out.println("Scenario 2: Customer purchase with loyalty points.");
        double priceAfterLoyaltyDiscount = genericCustomer.calculateDiscount(itemPrice, 750);
        System.out.println("  Original Price: $" + itemPrice + ", Price after loyalty points calculation: $" + priceAfterLoyaltyDiscount + "\n");


        System.out.println("--- Demonstrating Dynamic Polymorphism (Overriding) ---");
        Customer customer1 = new RegularCustomer();
        Customer customer2 = new PremiumCustomer();
        Customer customer3 = new Customer();

        System.out.println("Scenario 3: Regular Customer purchase.");
        double regularCustomerPrice = customer1.calculateDiscount(itemPrice);
        System.out.println("  Original Price: $" + itemPrice + ", Price for Regular Customer: $" + regularCustomerPrice + "\n");

        System.out.println("Scenario 4: Premium Customer purchase.");
        double premiumCustomerPrice = customer2.calculateDiscount(itemPrice);
        System.out.println("  Original Price: $" + itemPrice + ", Price for Premium Customer: $" + premiumCustomerPrice + "\n");

        System.out.println("Scenario 5: Generic Customer purchase (via polymorphic reference).");
        double genericCustomerPolymorphicPrice = customer3.calculateDiscount(itemPrice);
        System.out.println("  Original Price: $" + itemPrice + ", Price for Generic Customer: $" + genericCustomerPolymorphicPrice + "\n");

        System.out.println("--- Combining Both ---");
        System.out.println("Scenario 6: Regular Customer with loyalty points (overloading on a subclass instance).");
        double regularCustomerLoyaltyPrice = ((RegularCustomer)customer1).calculateDiscount(itemPrice, 600);
        System.out.println("  Original Price: $" + itemPrice + ", Price for Regular Customer with loyalty points: $" + regularCustomerLoyaltyPrice + "\n");
    }
}

class Customer {

    public double calculateDiscount(double purchaseAmount) {
        System.out.println("  -> Customer: Applying no discount by default.");
        return purchaseAmount;
    }

    public double calculateDiscount(double purchaseAmount, int loyaltyPoints) {
        System.out.println("  -> Customer: Applying loyalty points discount.");
        double discount = 0.0;
        if (loyaltyPoints >= 1000) {
            discount = 0.10;
        } else if (loyaltyPoints >= 500) {
            discount = 0.05;
        }
        return purchaseAmount * (1 - discount);
    }
}

class RegularCustomer extends Customer {

    @Override
    public double calculateDiscount(double purchaseAmount) {
        System.out.println("  -> RegularCustomer: Applying 5% discount.");
        return purchaseAmount * 0.95;
    }
}

class PremiumCustomer extends Customer {

    @Override
    public double calculateDiscount(double purchaseAmount) {
        System.out.println("  -> PremiumCustomer: Applying 10% discount.");
        return purchaseAmount * 0.90;
    }
}


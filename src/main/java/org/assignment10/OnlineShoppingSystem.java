package org.assignment10;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class OnlineShoppingSystem {

    public static void main(String[] args) {
        List<Double> ordersUSD = Arrays.asList(50.0, 150.0, 300.0);

        Predicate<Double> qualifiesForFreeShipping = amountINR -> amountINR > 2000.0;

        Consumer<Double> printOrderConfirmation = amount -> System.out.println("Order placed successfully for ₹" + String.format("%.2f", amount));

        Supplier<String> couponCodeGenerator = () -> {
            String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            StringBuilder sb = new StringBuilder("SAVE");
            Random random = new Random();
            for (int i = 0; i < 3; i++) {
                sb.append(chars.charAt(random.nextInt(chars.length())));
            }
            return sb.toString();
        };

        Function<Double, Double> usdToInrConverter = usdAmount -> usdAmount * 83.0;

        BiFunction<Double, Double, Double> applyPercentageDiscount = (price, discountPercentage) -> price - (price * discountPercentage / 100.0);

        System.out.println("--- Online Shopping System ---");

        List<Double> ordersINR = ordersUSD.stream()
                .map(usdToInrConverter)
                .collect(Collectors.toList());

        System.out.println("Orders in USD: " + ordersUSD);
        System.out.println("Orders converted to INR: " + ordersINR);

        double discountRate = 10.0;

        for (Double orderAmountINR : ordersINR) {
            System.out.println("\nProcessing order for: ₹" + String.format("%.2f", orderAmountINR));

            printOrderConfirmation.accept(orderAmountINR);

            if (qualifiesForFreeShipping.test(orderAmountINR)) {
                System.out.println("Good news! This order qualifies for FREE shipping.");
            } else {
                System.out.println("Shipping charges may apply for this order.");
            }

            System.out.println("Your coupon code: " + couponCodeGenerator.get());

            Double finalPriceAfterDiscount = applyPercentageDiscount.apply(orderAmountINR, discountRate);
            System.out.println("Price after " + discountRate + "% discount: ₹" + String.format("%.2f", finalPriceAfterDiscount));
        }
    }
}


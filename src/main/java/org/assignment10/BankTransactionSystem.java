package org.assignment10;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class BankTransactionSystem {

    public static void main(String[] args) {
        List<Double> transactions = Arrays.asList(1200.0, 55000.0, 30000.0);

        Predicate<Double> isSuspicious = amount -> amount > 50000.0;

        Consumer<Double> printTransactionMessage = amount -> System.out.println("Processed transaction of ₹" + amount);

        Supplier<Integer> otpGenerator = () -> {
            Random random = new Random();
            return 1000 + random.nextInt(9000);
        };

        Function<Double, Double> deductProcessingFee = amount -> amount - (amount * 0.02);

        BiFunction<Double, Double, Double> applyDiscountedFee = (amount, feePercentage) -> amount - (amount * feePercentage / 100.0);

        System.out.println("--- Bank Transaction Processing ---");

        boolean hasLoyaltyPoints = true;

        for (Double transactionAmount : transactions) {
            System.out.println("\nProcessing transaction: ₹" + transactionAmount);

            if (isSuspicious.test(transactionAmount)) {
                System.out.println("ALERT: Transaction of ₹" + transactionAmount + " is suspicious!");
            }

            printTransactionMessage.accept(transactionAmount);

            System.out.println("Transaction OTP: " + otpGenerator.get());

            Double amountAfterBaseFee = deductProcessingFee.apply(transactionAmount);
            System.out.println("Amount after standard 2% fee: ₹" + String.format("%.2f", amountAfterBaseFee));

            double effectiveFeePercentage;
            if (hasLoyaltyPoints) {
                effectiveFeePercentage = 1.0;
                System.out.println("Loyalty points detected. Applying discounted fee of 1%.");
            } else {
                effectiveFeePercentage = 2.0;
                System.out.println("No loyalty points. Applying standard fee of 2%.");
            }

            Double amountAfterVariableFee = applyDiscountedFee.apply(transactionAmount, effectiveFeePercentage);
            System.out.println("Amount after variable fee (" + effectiveFeePercentage + "%): ₹" + String.format("%.2f", amountAfterVariableFee));
        }
    }
}


package org.assignment5;

class MathOperations {
    public int add(int a, int b) {
        return a + b;
    }

    public double add(double a, double b) {
        return a + b;
    }

    public int add(int[] numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }
}

public class staticPoly {
    public static void main(String[] args) {
        MathOperations mathOps = new MathOperations();

        int sumInt = mathOps.add(5, 10);
        System.out.println("Sum of two integers (5, 10): " + sumInt);

        double sumDouble = mathOps.add(3.5, 2.7);
        System.out.println("Sum of two doubles (3.5, 2.7): " + sumDouble);

        int[] intArray = {1, 2, 3, 4, 5};
        int sumArray = mathOps.add(intArray);
        System.out.println("Sum of integer array {1, 2, 3, 4, 5}: " + sumArray);
    }
}


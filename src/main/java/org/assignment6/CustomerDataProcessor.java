package org.assignment6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CustomerDataProcessor {

    public static void main(String[] args) {
        CustomerFileReader reader = new CustomerFileReader();
        String filename = "customers.txt";

        System.out.println("Attempting to read customer data from: " + filename);

        try {
            reader.readCustomerData(filename);
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found. Please ensure '" + filename + "' exists in the correct directory.");
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}

class CustomerFileReader {
    public void readCustomerData(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                String[] parts = line.split(",");
                try {

                    if (parts.length != 2) {
                        System.err.println("Skipping malformed line " + lineNumber + ": '" + line + "' (Expected 'Name,Balance')");
                        continue;
                    }

                    String name = parts[0].trim();
                    double balance = Double.parseDouble(parts[1].trim());

                    System.out.println("Customer: " + name + ", Balance: $" + String.format("%.2f", balance));

                } catch (NumberFormatException e) {
                    System.err.println("Skipping line " + lineNumber + ": '" + line + "' (Invalid balance format: " + parts[1].trim() + ")");
                }
            }
        }
    }
}

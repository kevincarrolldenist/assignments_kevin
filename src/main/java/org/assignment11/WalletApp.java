package org.assignment11;

// UI Layer
// WalletApp.java
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class WalletApp {
    private WalletService walletService;
    private Scanner scanner;

    public WalletApp() {
        this.walletService = new WalletService(new InMemoryWalletRepository());
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        WalletApp app = new WalletApp();
        app.run();
    }

    public void run() {
        int choice;
        do {
            printMenu();
            try {
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        registerCustomer();
                        break;
                    case 2:
                        addFunds();
                        break;
                    case 3:
                        withdrawFunds();
                        break;
                    case 4:
                        transferFunds();
                        break;
                    case 5:
                        getAccountDetails();
                        break;
                    case 6:
                        getAllUsers();
                        break;
                    case 7:
                        System.out.println("Exiting application. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume the invalid input
                choice = 0; // Set choice to 0 to re-enter loop
            } catch (RuntimeException e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println(); // Add a newline for better readability
        } while (choice != 7);

        scanner.close();
    }

    private void printMenu() {
        System.out.println("--- Digital Wallet Application Menu ---");
        System.out.println("1. Register Customer");
        System.out.println("2. Add Funds");
        System.out.println("3. Withdraw Funds");
        System.out.println("4. Transfer Funds");
        System.out.println("5. Get Account Details");
        System.out.println("6. View All Registered Users (Admin)");
        System.out.println("7. Exit");
        System.out.println("-------------------------------------");
    }

    private void registerCustomer() {
        System.out.println("\n--- Register New Customer ---");
        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Email / Mobile Number: ");
        String email = scanner.nextLine();

        System.out.print("Enter Initial Balance (press Enter for 0): ");
        String balanceInput = scanner.nextLine();
        double initialBalance = 0.0;
        if (!balanceInput.isEmpty()) {
            try {
                initialBalance = Double.parseDouble(balanceInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid balance format. Setting to 0.");
            }
        }

        walletService.registerCustomer(customerId, name, email, initialBalance);
    }

    private void addFunds() {
        System.out.println("\n--- Add Funds ---");
        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        System.out.print("Enter Amount to Add: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        walletService.addFunds(customerId, amount);
    }

    private void withdrawFunds() {
        System.out.println("\n--- Withdraw Funds ---");
        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        System.out.print("Enter Amount to Withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        walletService.withdrawFunds(customerId, amount);
    }

    private void transferFunds() {
        System.out.println("\n--- Transfer Funds ---");
        System.out.print("Enter Sender Customer ID: ");
        int senderId = scanner.nextInt();
        System.out.print("Enter Receiver Customer ID: ");
        int receiverId = scanner.nextInt();
        System.out.print("Enter Amount to Transfer: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        walletService.transferFunds(senderId, receiverId, amount);
    }

    private void getAccountDetails() {
        System.out.println("\n--- Get Account Details ---");
        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Customer customer = walletService.getAccountDetails(customerId);
        System.out.println(customer);
    }

    private void getAllUsers() {
        System.out.println("\n--- All Registered Users ---");
        List<Customer> users = walletService.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("No registered users found.");
        } else {
            users.forEach(System.out::println);
        }
    }
}

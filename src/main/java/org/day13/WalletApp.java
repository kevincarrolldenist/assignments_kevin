package org.day13;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// Custom Exceptions
class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}

class InvalidAmountException extends Exception {
    public InvalidAmountException(String message) {
        super(message);
    }
}

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}

class NoUsersFoundException extends Exception {
    public NoUsersFoundException(String message) {
        super(message);
    }
}

// Model Layer
class Customer {
    private int customerId;
    private String name;
    private String emailOrMobile;
    private double balance;

    public Customer(int customerId, String name, String emailOrMobile, double balance) {
        this.customerId = customerId;
        this.name = name;
        this.emailOrMobile = emailOrMobile;
        this.balance = balance;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getEmailOrMobile() {
        return emailOrMobile;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Customer ID: " + customerId + ", Name: " + name + ", Contact: " + emailOrMobile + ", Balance: $" + String.format("%.2f", balance);
    }
}

// DAO Layer


class WalletRepository {
    private Map<Integer, Customer> customers;

    public WalletRepository() {
        this.customers = new HashMap<>();
    }

    public void addCustomer(Customer customer) throws UserAlreadyExistsException {
        if (customers.containsKey(customer.getCustomerId())) {
            throw new UserAlreadyExistsException("Customer with ID " + customer.getCustomerId() + " already exists.");
        }
        customers.put(customer.getCustomerId(), customer);
    }

    public Customer getCustomer(int customerId) {
        return customers.get(customerId);
    }

    public void updateCustomer(Customer customer) {
        customers.put(customer.getCustomerId(), customer);
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers.values());
    }

    public boolean isCustomerExists(int customerId) {
        return customers.containsKey(customerId);
    }
}

// Service Layer
class WalletService {
    private WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public void registerCustomer(int customerId, String name, String emailOrMobile, double initialBalance) throws UserAlreadyExistsException, InvalidAmountException {
        if (initialBalance < 0) {
            throw new InvalidAmountException("Initial balance cannot be negative.");
        }
        Customer newCustomer = new Customer(customerId, name, emailOrMobile, initialBalance);
        walletRepository.addCustomer(newCustomer);
        System.out.println("Customer " + name + " registered successfully with ID " + customerId + ".");
    }

    public void addFunds(int customerId, double amount) throws UserNotFoundException, InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount to add must be positive.");
        }
        Customer customer = walletRepository.getCustomer(customerId);
        if (customer == null) {
            throw new UserNotFoundException("Customer with ID " + customerId + " not found.");
        }
        customer.setBalance(customer.getBalance() + amount);
        walletRepository.updateCustomer(customer);
        System.out.println("Successfully added $" + String.format("%.2f", amount) + " to customer ID " + customerId + ". New balance: $" + String.format("%.2f", customer.getBalance()));
    }

    public void withdrawFunds(int customerId, double amount) throws UserNotFoundException, InvalidAmountException, InsufficientBalanceException {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount to withdraw must be positive.");
        }
        Customer customer = walletRepository.getCustomer(customerId);
        if (customer == null) {
            throw new UserNotFoundException("Customer with ID " + customerId + " not found.");
        }
        if (customer.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance. Current balance: $" + String.format("%.2f", customer.getBalance()) + ", trying to withdraw: $" + String.format("%.2f", amount));
        }
        customer.setBalance(customer.getBalance() - amount);
        walletRepository.updateCustomer(customer);
        System.out.println("Successfully withdrew $" + String.format("%.2f", amount) + " from customer ID " + customerId + ". New balance: $" + String.format("%.2f", customer.getBalance()));
    }

    public void transferFunds(int senderId, int receiverId, double amount) throws UserNotFoundException, InvalidAmountException, InsufficientBalanceException {
        if (amount <= 0) {
            throw new InvalidAmountException("Transfer amount must be positive.");
        }
        if (senderId == receiverId) {
            throw new InvalidAmountException("Cannot transfer funds to the same account.");
        }

        Customer sender = walletRepository.getCustomer(senderId);
        Customer receiver = walletRepository.getCustomer(receiverId);

        if (sender == null) {
            throw new UserNotFoundException("Sender with ID " + senderId + " not found.");
        }
        if (receiver == null) {
            throw new UserNotFoundException("Receiver with ID " + receiverId + " not found.");
        }

        if (sender.getBalance() < amount) {
            throw new InsufficientBalanceException("Sender (ID: " + senderId + ") has insufficient balance. Current balance: $" + String.format("%.2f", sender.getBalance()) + ", trying to transfer: $" + String.format("%.2f", amount));
        }

        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        walletRepository.updateCustomer(sender);
        walletRepository.updateCustomer(receiver);
        System.out.println("Successfully transferred $" + String.format("%.2f", amount) + " from " + sender.getName() + " (ID: " + senderId + ") to " + receiver.getName() + " (ID: " + receiverId + ").");
        System.out.println(sender.getName() + "'s new balance: $" + String.format("%.2f", sender.getBalance()));
        System.out.println(receiver.getName() + "'s new balance: $" + String.format("%.2f", receiver.getBalance()));
    }

    public Customer getAccountDetails(int customerId) throws UserNotFoundException {
        Customer customer = walletRepository.getCustomer(customerId);
        if (customer == null) {
            throw new UserNotFoundException("Customer with ID " + customerId + " not found.");
        }
        return customer;
    }

    public List<Customer> getAllUsers() throws NoUsersFoundException {
        List<Customer> users = walletRepository.getAllCustomers();
        if (users.isEmpty()) {
            throw new NoUsersFoundException("No users registered yet.");
        }
        return users;
    }
}

// UI Layer

public class WalletApp {
    private static WalletService walletService = new WalletService(new WalletRepository());
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            displayMenu();
            choice = getUserChoice();

            try {
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
                        adminListUsers();
                        break;
                    case 0:
                        System.out.println("Exiting application. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
            System.out.println("\n------------------------------------\n");
        } while (choice != 0);

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("--- Online Digital Wallet Menu ---");
        System.out.println("1. Register Customer");
        System.out.println("2. Add Funds");
        System.out.println("3. Withdraw Funds");
        System.out.println("4. Transfer Funds");
        System.out.println("5. Get Account Details");
        System.out.println("6. Admin: List All Users");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Consume the invalid input
            return -1; // Return an invalid choice to re-display menu
        } finally {
            scanner.nextLine(); // Consume newline left-over
        }
    }

    private static void registerCustomer() {
        System.out.print("Enter Customer ID: ");
        int customerId = readIntInput();
        System.out.print("Enter Customer Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email/Mobile Number: ");
        String emailOrMobile = scanner.nextLine();
        System.out.print("Enter Initial Balance (press Enter for 0): ");
        String balanceInput = scanner.nextLine();
        double initialBalance = 0;
        if (!balanceInput.isEmpty()) {
            try {
                initialBalance = Double.parseDouble(balanceInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid balance format. Defaulting to 0.");
            }
        }

        try {
            walletService.registerCustomer(customerId, name, emailOrMobile, initialBalance);
        } catch (UserAlreadyExistsException | InvalidAmountException e) {
            System.err.println("Registration failed: " + e.getMessage());
        }
    }

    private static void addFunds() {
        System.out.print("Enter Customer ID: ");
        int customerId = readIntInput();
        System.out.print("Enter Amount to Add: ");
        double amount = readDoubleInput();

        try {
            walletService.addFunds(customerId, amount);
        } catch (UserNotFoundException | InvalidAmountException e) {
            System.err.println("Add funds failed: " + e.getMessage());
        }
    }

    private static void withdrawFunds() {
        System.out.print("Enter Customer ID: ");
        int customerId = readIntInput();
        System.out.print("Enter Amount to Withdraw: ");
        double amount = readDoubleInput();

        try {
            walletService.withdrawFunds(customerId, amount);
        } catch (UserNotFoundException | InvalidAmountException | InsufficientBalanceException e) {
            System.err.println("Withdrawal failed: " + e.getMessage());
        }
    }

    private static void transferFunds() {
        System.out.print("Enter Sender Customer ID: ");
        int senderId = readIntInput();
        System.out.print("Enter Receiver Customer ID: ");
        int receiverId = readIntInput();
        System.out.print("Enter Amount to Transfer: ");
        double amount = readDoubleInput();

        try {
            walletService.transferFunds(senderId, receiverId, amount);
        } catch (UserNotFoundException | InvalidAmountException | InsufficientBalanceException e) {
            System.err.println("Transfer failed: " + e.getMessage());
        }
    }

    private static void getAccountDetails() {
        System.out.print("Enter Customer ID: ");
        int customerId = readIntInput();

        try {
            Customer customer = walletService.getAccountDetails(customerId);
            System.out.println("\nAccount Details:");
            System.out.println(customer);
        } catch (UserNotFoundException e) {
            System.err.println("Fetch account details failed: " + e.getMessage());
        }
    }

    private static void adminListUsers() {
        try {
            List<Customer> users = walletService.getAllUsers();
            System.out.println("\n--- All Registered Customers ---");
            for (Customer customer : users) {
                System.out.println(customer);
            }
        } catch (NoUsersFoundException e) {
            System.err.println("Admin function failed: " + e.getMessage());
        }
    }

    private static int readIntInput() {
        while (true) {
            try {
                int value = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a whole number.");
                scanner.nextLine(); // Consume the invalid input
                System.out.print("Enter again: ");
            }
        }
    }

    private static double readDoubleInput() {
        while (true) {
            try {
                double value = scanner.nextDouble();
                scanner.nextLine(); // Consume newline
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume the invalid input
                System.out.print("Enter again: ");
            }
        }
    }
}

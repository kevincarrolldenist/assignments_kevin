package org.assignment11;

// Service Layer
// WalletService.java
import java.util.List;

public class WalletService {
    private WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public void registerCustomer(int customerId, String name, String email, double initialBalance) {
        if (walletRepository.customerExists(customerId)) {
            throw new UserAlreadyExistsException("Customer with ID " + customerId + " already exists.");
        }
        if (initialBalance < 0) {
            throw new InvalidAmountException("Initial balance cannot be negative.");
        }
        Customer newCustomer = new Customer(customerId, name, email, initialBalance);
        walletRepository.addCustomer(newCustomer);
        System.out.println("Customer " + name + " registered successfully with ID " + customerId + ".");
    }

    public void addFunds(int customerId, double amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount to add must be positive.");
        }
        Customer customer = walletRepository.getCustomer(customerId);
        if (customer == null) {
            throw new UserNotFoundException("Customer with ID " + customerId + " not found.");
        }
        customer.setBalance(customer.getBalance() + amount);
        walletRepository.updateCustomer(customer);
        System.out.println("Successfully added $" + String.format("%.2f", amount) + " to customer " + customerId + "'s wallet. New balance: $" + String.format("%.2f", customer.getBalance()));
    }

    public void withdrawFunds(int customerId, double amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount to withdraw must be positive.");
        }
        Customer customer = walletRepository.getCustomer(customerId);
        if (customer == null) {
            throw new UserNotFoundException("Customer with ID " + customerId + " not found.");
        }
        if (customer.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance for customer " + customerId + ". Current balance: $" + String.format("%.2f", customer.getBalance()));
        }
        customer.setBalance(customer.getBalance() - amount);
        walletRepository.updateCustomer(customer);
        System.out.println("Successfully withdrew $" + String.format("%.2f", amount) + " from customer " + customerId + "'s wallet. New balance: $" + String.format("%.2f", customer.getBalance()));
    }

    public void transferFunds(int senderId, int receiverId, double amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Transfer amount must be positive.");
        }
        Customer sender = walletRepository.getCustomer(senderId);
        if (sender == null) {
            throw new UserNotFoundException("Sender with ID " + senderId + " not found.");
        }
        Customer receiver = walletRepository.getCustomer(receiverId);
        if (receiver == null) {
            throw new UserNotFoundException("Receiver with ID " + receiverId + " not found.");
        }
        if (senderId == receiverId) {
            throw new InvalidAmountException("Cannot transfer funds to the same account.");
        }
        if (sender.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance for sender " + senderId + ". Current balance: $" + String.format("%.2f", sender.getBalance()));
        }

        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);
        walletRepository.updateCustomer(sender);
        walletRepository.updateCustomer(receiver);
        System.out.println("Transferred $" + String.format("%.2f", amount) + " from " + senderId + " to " + receiverId + " successfully.");
        System.out.println("Sender " + senderId + " new balance: $" + String.format("%.2f", sender.getBalance()));
        System.out.println("Receiver " + receiverId + " new balance: $" + String.format("%.2f", receiver.getBalance()));
    }

    public Customer getAccountDetails(int customerId) {
        Customer customer = walletRepository.getCustomer(customerId);
        if (customer == null) {
            throw new UserNotFoundException("Customer with ID " + customerId + " not found.");
        }
        return customer;
    }

    public List<Customer> getAllUsers() {
        List<Customer> customers = walletRepository.getAllCustomers();
        if (customers.isEmpty()) {
            throw new NoUsersFoundException("No registered users found.");
        }
        return customers;
    }
}


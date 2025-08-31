package org.assignment11;

// DAO Layer
// WalletRepository.java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.List;

public interface WalletRepository {
    void addCustomer(Customer customer);
    Customer getCustomer(int customerId);
    void updateCustomer(Customer customer);
    List<Customer> getAllCustomers();
    boolean customerExists(int customerId);
}

// InMemoryWalletRepository.java


 class InMemoryWalletRepository implements WalletRepository {
    private Map<Integer, Customer> customers;

    public InMemoryWalletRepository() {
        this.customers = new HashMap<>();
    }

    @Override
    public void addCustomer(Customer customer) {
        customers.put(customer.getCustomerId(), customer);
    }

    @Override
    public Customer getCustomer(int customerId) {
        return customers.get(customerId);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customers.put(customer.getCustomerId(), customer); // Overwrites if exists, effectively updates
    }

    @Override
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public boolean customerExists(int customerId) {
        return customers.containsKey(customerId);
    }
}

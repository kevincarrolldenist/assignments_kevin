package org.assignment8;

import java.util.ArrayList;
import java.util.Objects;

class CartItem {
    private int itemId;
    private String itemName;
    private int quantity;
    private double pricePerUnit;

    public CartItem(int itemId, String itemName, int quantity, double pricePerUnit) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
    }

    public int getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return itemId == cartItem.itemId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId);
    }

    @Override
    public String toString() {
        return "CartItem{itemId=" + itemId + ", itemName='" + itemName + "', quantity=" + quantity + ", pricePerUnit=" + pricePerUnit + "}";
    }
}

class ShoppingCart {
    private ArrayList<CartItem> cartItems;

    public ShoppingCart() {
        this.cartItems = new ArrayList<>();
    }

    public void addItem(CartItem newItem) {
        boolean found = false;
        for (CartItem item : cartItems) {
            if (item.equals(newItem)) { // Uses the overridden equals method based on itemId
                item.setQuantity(item.getQuantity() + newItem.getQuantity());
                found = true;
                System.out.println("Updated quantity for item: " + newItem.getItemName() + ". New quantity: " + item.getQuantity());
                break;
            }
        }
        if (!found) {
            cartItems.add(newItem);
            System.out.println("Added new item to cart: " + newItem.getItemName());
        }
    }

    public boolean removeItem(int itemId) {
        CartItem dummyItem = new CartItem(itemId, null, 0, 0.0); // Dummy item for comparison
        boolean removed = cartItems.remove(dummyItem); // Uses overridden equals
        if (removed) {
            System.out.println("Removed item with ID: " + itemId);
        } else {
            System.out.println("Item with ID " + itemId + " not found in cart.");
        }
        return removed;
    }

    public void updateQuantity(int itemId, int newQuantity) {
        boolean found = false;
        for (CartItem item : cartItems) {
            if (item.getItemId() == itemId) {
                found = true;
                if (newQuantity <= 0) {
                    removeItem(itemId);
                } else {
                    item.setQuantity(newQuantity);
                    System.out.println("Updated quantity for item ID " + itemId + ". New quantity: " + newQuantity);
                }
                break;
            }
        }
        if (!found) {
            System.out.println("Item with ID " + itemId + " not found to update quantity.");
        }
    }

    public CartItem searchItem(int itemId) {
        for (CartItem item : cartItems) {
            if (item.getItemId() == itemId) {
                System.out.println("Found item: " + item);
                return item;
            }
        }
        System.out.println("Item with ID " + itemId + " not found.");
        return null;
    }

    public double getTotalBill() {
        double totalBill = 0.0;
        for (CartItem item : cartItems) {
            totalBill += item.getQuantity() * item.getPricePerUnit();
        }
        return totalBill;
    }

    public void displayCart() {
        System.out.println("\n--- Current Shopping Cart ---");
        if (cartItems.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }
        for (CartItem item : cartItems) {
            System.out.println(item);
        }
        System.out.println("-----------------------------");
    }

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem(new CartItem(1, "Laptop", 1, 1200.00));
        cart.addItem(new CartItem(2, "Mouse", 2, 25.00));
        cart.addItem(new CartItem(3, "Keyboard", 1, 75.00));
        cart.addItem(new CartItem(1, "Laptop", 1, 1200.00)); // Adding existing item, should update quantity
        cart.addItem(new CartItem(4, "Monitor", 1, 300.00));

        cart.displayCart();

        cart.updateQuantity(2, 5); // Update quantity of Mouse
        cart.updateQuantity(3, 0); // Update quantity of Keyboard to 0, should remove
        cart.updateQuantity(99, 1); // Try to update non-existent item

        cart.displayCart();

        cart.searchItem(1); // Search for Laptop
        cart.searchItem(5); // Search for non-existent item

        cart.removeItem(4); // Remove Monitor
        cart.removeItem(99); // Try to remove non-existent item

        cart.displayCart();

        System.out.println("\nTotal Bill: $" + String.format("%.2f", cart.getTotalBill()));
    }
}


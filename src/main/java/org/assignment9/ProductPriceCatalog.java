package org.assignment9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

class Product {
    private int productId;
    private String name;
    private double price;

    public Product(int productId, String name, double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId == product.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }

    @Override
    public String toString() {
        return "Product{productId=" + productId + ", name='" + name + "', price=" + String.format("%.2f", price) + "}";
    }
}

public class ProductPriceCatalog {
    private Map<Integer, Product> products;

    public ProductPriceCatalog() {
        this.products = new HashMap<>();
    }

    // Add a new product
    public void addProduct(Product product) {
        if (products.containsKey(product.getProductId())) {
            System.out.println("Product with ID " + product.getProductId() + " already exists. Not added.");
        } else {
            products.put(product.getProductId(), product);
            System.out.println("Added product: " + product.getName());
        }
    }

    // Remove a product
    public boolean removeProduct(int productId) {
        if (products.remove(productId) != null) {
            System.out.println("Removed product with ID: " + productId);
            return true;
        } else {
            System.out.println("Product with ID " + productId + " not found for removal.");
            return false;
        }
    }

    // Update the price of a product
    public boolean updateProductPrice(int productId, double newPrice) {
        Product product = products.get(productId);
        if (product != null) {
            product.setPrice(newPrice);
            System.out.println("Updated price for product ID " + productId + " to $" + String.format("%.2f", newPrice));
            return true;
        } else {
            System.out.println("Product with ID " + productId + " not found for price update.");
            return false;
        }
    }

    // View all products
    public void viewAllProducts() {
        System.out.println("\n--- Current Product Catalog ---");
        if (products.isEmpty()) {
            System.out.println("Catalog is empty.");
            return;
        }
        for (Product product : products.values()) {
            System.out.println(product);
        }
        System.out.println("-----------------------------");
    }

    // Find a product by its ID
    public Product findProductById(int productId) {
        Product product = products.get(productId);
        if (product != null) {
            System.out.println("Found product by ID " + productId + ": " + product);
            return product;
        } else {
            System.out.println("Product with ID " + productId + " not found.");
            return null;
        }
    }

    // Find all products cheaper than a given price
    public List<Product> findProductsCheaperThan(double maxPrice) {
        List<Product> cheaperProducts = new ArrayList<>();
        System.out.println("\n--- Products Cheaper Than $" + String.format("%.2f", maxPrice) + " ---");
        boolean found = false;
        for (Product product : products.values()) {
            if (product.getPrice() < maxPrice) {
                cheaperProducts.add(product);
                System.out.println(product);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No products found cheaper than $" + String.format("%.2f", maxPrice) + ".");
        }
        System.out.println("----------------------------------------");
        return cheaperProducts;
    }

    // Display products sorted by product name
    public void displayProductsSortedByName() {
        List<Product> sortedList = new ArrayList<>(products.values());
        Collections.sort(sortedList, Comparator.comparing(Product::getName));
        System.out.println("\n--- Products Sorted by Name ---");
        for (Product product : sortedList) {
            System.out.println(product);
        }
        System.out.println("-----------------------------");
    }

    // Display products sorted by price (ascending/descending)
    public void displayProductsSortedByPrice(boolean ascending) {
        List<Product> sortedList = new ArrayList<>(products.values());
        Comparator<Product> priceComparator = Comparator.comparingDouble(Product::getPrice);
        if (!ascending) {
            priceComparator = priceComparator.reversed();
        }
        Collections.sort(sortedList, priceComparator);
        System.out.println("\n--- Products Sorted by Price (" + (ascending ? "Ascending" : "Descending") + ") ---");
        for (Product product : sortedList) {
            System.out.println(product);
        }
        System.out.println("--------------------------------------------------");
    }

    public static void main(String[] args) {
        ProductPriceCatalog catalog = new ProductPriceCatalog();

        // Add products
        catalog.addProduct(new Product(1, "Laptop", 1200.00));
        catalog.addProduct(new Product(2, "Mouse", 25.50));
        catalog.addProduct(new Product(3, "Keyboard", 75.00));
        catalog.addProduct(new Product(4, "Monitor", 300.00));
        catalog.addProduct(new Product(5, "Webcam", 49.99));
        catalog.addProduct(new Product(1, "Duplicate Laptop", 1100.00)); // Should not add

        catalog.viewAllProducts();

        // Update price
        catalog.updateProductPrice(2, 29.99);
        catalog.updateProductPrice(99, 100.00); // Non-existent product

        catalog.viewAllProducts();

        // Find by ID
        catalog.findProductById(3);
        catalog.findProductById(99);

        // Find cheaper products
        catalog.findProductsCheaperThan(100.00);
        catalog.findProductsCheaperThan(20.00);

        // Remove product
        catalog.removeProduct(4);
        catalog.removeProduct(99);

        catalog.viewAllProducts();

        // Sorting demonstrations
        catalog.displayProductsSortedByName();
        catalog.displayProductsSortedByPrice(true);  // Ascending
        catalog.displayProductsSortedByPrice(false); // Descending
    }
}

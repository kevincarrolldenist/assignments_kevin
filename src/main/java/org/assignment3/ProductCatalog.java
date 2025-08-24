package org.assignment3;

// Product.java
class Product {
    private String productId;
    private String name;
    private double price;
    private String description;

    public Product(String productId, String name, double price, String description) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void displayProductDetails() {
        System.out.println("Product ID: " + productId);
        System.out.println("Name: " + name);
        System.out.printf("Price: $%.2f%n", price);
        System.out.println("Description: " + description);
    }
}

// Electronics.java
class Electronics extends Product {
    private int warrantyPeriodMonths;

    public Electronics(String productId, String name, double price, String description, int warrantyPeriodMonths) {
        super(productId, name, price, description);
        this.warrantyPeriodMonths = warrantyPeriodMonths;
    }

    public int getWarrantyPeriodMonths() {
        return warrantyPeriodMonths;
    }

    public void setWarrantyPeriodMonths(int warrantyPeriodMonths) {
        this.warrantyPeriodMonths = warrantyPeriodMonths;
    }

    @Override
    public void displayProductDetails() {
        super.displayProductDetails();
        System.out.println("Warranty Period: " + warrantyPeriodMonths + " months");
    }
}

// Clothing.java
class Clothing extends Product {
    private String size;
    private String fabric;

    public Clothing(String productId, String name, double price, String description, String size, String fabric) {
        super(productId, name, price, description);
        this.size = size;
        this.fabric = fabric;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFabric() {
        return fabric;
    }

    public void setFabric(String fabric) {
        this.fabric = fabric;
    }

    @Override
    public void displayProductDetails() {
        super.displayProductDetails();
        System.out.println("Size: " + size);
        System.out.println("Fabric: " + fabric);
    }
}

// Groceries.java
class Groceries extends Product {
    private String expiryDate;

    public Groceries(String productId, String name, double price, String description, String expiryDate) {
        super(productId, name, price, description);
        this.expiryDate = expiryDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public void displayProductDetails() {
        super.displayProductDetails();
        System.out.println("Expiry Date: " + expiryDate);
    }
}

// ProductCatalog.java
public class ProductCatalog {
    public static void main(String[] args) {
        System.out.println("--- Product Catalog ---");

        Electronics laptop = new Electronics("E001", "Laptop Pro", 1200.00, "High-performance laptop for professionals", 24);
        Clothing tShirt = new Clothing("C001", "Cotton T-Shirt", 25.50, "Comfortable everyday t-shirt", "M", "Cotton");
        Groceries milk = new Groceries("G001", "Whole Milk 1L", 3.20, "Fresh pasteurized whole milk", "2025-08-20");
        Electronics headphones = new Electronics("E002", "Noise-Cancelling Headphones", 199.99, "Immersive audio experience", 12);

        System.out.println("\n--- Displaying Electronics: Laptop ---");
        laptop.displayProductDetails();

        System.out.println("\n--- Displaying Clothing: T-Shirt ---");
        tShirt.displayProductDetails();

        System.out.println("\n--- Displaying Groceries: Milk ---");
        milk.displayProductDetails();

        System.out.println("\n--- Displaying Electronics: Headphones ---");
        headphones.displayProductDetails();

        System.out.println("\n--- Demonstrating Setters ---");
        laptop.setPrice(1150.00);
        laptop.setDescription("High-performance laptop, now on sale!");
        System.out.println("\n--- Updated Laptop Details ---");
        laptop.displayProductDetails();

        tShirt.setSize("L");
        System.out.println("\n--- Updated T-Shirt Details ---");
        tShirt.displayProductDetails();
    }
}


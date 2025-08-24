package org.assignment3;
// Vehicle.java
class Vehicle {
    private String registrationNumber;
    private String brand;
    private double rentalRate;

    public Vehicle(String registrationNumber, String brand, double rentalRate) {
        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.rentalRate = rentalRate;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(double rentalRate) {
        this.rentalRate = rentalRate;
    }

    public double calculateRental(int days) {
        if (days < 0) {
            System.out.println("Days cannot be negative.");
            return 0.0;
        }
        return rentalRate * days;
    }

    public void displayDetails() {
        System.out.println("Registration Number: " + registrationNumber);
        System.out.println("Brand: " + brand);
        System.out.printf("Daily Rental Rate: $%.2f%n", rentalRate);
    }
}

// Car.java
class Car extends Vehicle {
    private int seatingCapacity;

    public Car(String registrationNumber, String brand, double rentalRate, int seatingCapacity) {
        super(registrationNumber, brand, rentalRate);
        this.seatingCapacity = seatingCapacity;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Seating Capacity: " + seatingCapacity + " persons");
    }
}

// Bike.java
class Bike extends Vehicle {
    private int engineCapacityCC;

    public Bike(String registrationNumber, String brand, double rentalRate, int engineCapacityCC) {
        super(registrationNumber, brand, rentalRate);
        this.engineCapacityCC = engineCapacityCC;
    }

    public int getEngineCapacityCC() {
        return engineCapacityCC;
    }

    public void setEngineCapacityCC(int engineCapacityCC) {
        this.engineCapacityCC = engineCapacityCC;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Engine Capacity: " + engineCapacityCC + "cc");
    }
}

// Truck.java
class Truck extends Vehicle {
    private double loadCapacityTons;

    public Truck(String registrationNumber, String brand, double rentalRate, double loadCapacityTons) {
        super(registrationNumber, brand, rentalRate);
        this.loadCapacityTons = loadCapacityTons;
    }

    public double getLoadCapacityTons() {
        return loadCapacityTons;
    }

    public void setLoadCapacityTons(double loadCapacityTons) {
        this.loadCapacityTons = loadCapacityTons;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.printf("Load Capacity: %.2f tons%n", loadCapacityTons);
    }
}

// RentalService.java
public class RentalService {
    public static void main(String[] args) {
        System.out.println("--- Vehicle Rental Service ---");

        Car car1 = new Car("XYZ-789", "Toyota", 50.00, 5);
        Bike bike1 = new Bike("ABC-123", "Honda", 25.00, 250);
        Truck truck1 = new Truck("DEF-456", "Volvo", 150.00, 10.5);
        Car car2 = new Car("GHI-012", "Ford", 65.00, 7);

        Vehicle[] vehicles = {car1, bike1, truck1, car2};

        int rentalDays = 7;

        System.out.println("\n--- Calculating Rentals for " + rentalDays + " Days ---");

        for (Vehicle vehicle : vehicles) {
            System.out.println("\n--------------------");
            vehicle.displayDetails();
            double totalRental = vehicle.calculateRental(rentalDays);
            System.out.printf("Total Rental Cost for %d days: $%.2f%n", rentalDays, totalRental);
        }

        System.out.println("\n--- Demonstrating Setters and Individual Access ---");
        car1.setRentalRate(55.00);
        car1.setSeatingCapacity(4);
        System.out.println("\nUpdated Car 1 Details:");
        car1.displayDetails();
        System.out.printf("New Rental Cost for Car 1 (%d days): $%.2f%n", rentalDays, car1.calculateRental(rentalDays));

        truck1.setLoadCapacityTons(12.0);
        System.out.println("\nUpdated Truck 1 Load Capacity: " + truck1.getLoadCapacityTons() + " tons");
    }
}

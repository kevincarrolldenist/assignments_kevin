// Vehicle.java
abstract class Vehicle {
    private String brand;
    private double rentalPricePerDay;

    public Vehicle(String brand, double rentalPricePerDay) {
        this.brand = brand;
        this.rentalPricePerDay = rentalPricePerDay;
    }

    public String getBrand() {
        return brand;
    }

    public double getRentalPricePerDay() {
        return rentalPricePerDay;
    }

    public void displayDetails() {
        System.out.println("Brand: " + brand);
        System.out.printf("Rental Price Per Day: $%.2f%n", rentalPricePerDay);
    }

    public abstract void start();
}

// Car.java
class Car extends Vehicle {
    public Car(String brand, double rentalPricePerDay) {
        super(brand, rentalPricePerDay);
    }

    @Override
    public void start() {
        System.out.println("Car is starting with a key ignition...");
    }
}

// Bike.java
 class Bike extends Vehicle {
    public Bike(String brand, double rentalPricePerDay) {
        super(brand, rentalPricePerDay);
    }

    @Override
    public void start() {
        System.out.println("Bike is starting with a self-start button...");
    }
}

// RentalCompany.java
public class Day5 {
    public static void main(String[] args) {
        Car myCar = new Car("Toyota", 50.00);
        Bike myBike = new Bike("Honda", 25.00);

        System.out.println("--- Car Details ---");
        myCar.displayDetails();
        myCar.start();

        System.out.println("\n--- Bike Details ---");
        myBike.displayDetails();
        myBike.start();
    }
}

package org.assignment4;

// Vehicle.java
abstract class Vehicle {
    String brand;
    String model;

    public Vehicle(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public abstract void service();
}

// Repairable.java
interface Repairable {
    void repair();
}

// Car.java
class Car extends Vehicle implements Repairable {
    public Car(String brand, String model) {
        super(brand, model);
    }

    @Override
    public void service() {
        System.out.println("Servicing car: " + brand + " " + model);
    }

    @Override
    public void repair() {
        System.out.println("Repairing car engine...");
    }
}

// Bike.java
class Bike extends Vehicle implements Repairable {
    public Bike(String brand, String model) {
        super(brand, model);
    }

    @Override
    public void service() {
        System.out.println("Servicing bike: " + brand + " " + model);
    }

    @Override
    public void repair() {
        System.out.println("Repairing bike brakes...");
    }
}

// Main.java
public class VehicleService {
    public static void main(String[] args) {
        Car car = new Car("Toyota", "Corolla");
        Bike bike = new Bike("Yamaha", "FZ");

        car.service();
        car.repair();

        bike.service();
        bike.repair();
    }
}


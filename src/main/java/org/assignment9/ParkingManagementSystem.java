package org.assignment9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

class Vehicle {
    private String plateNumber;
    private String ownerName;
    private String type;

    public Vehicle(String plateNumber, String ownerName, String type) {
        this.plateNumber = plateNumber;
        this.ownerName = ownerName;
        this.type = type;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(plateNumber, vehicle.plateNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plateNumber);
    }

    @Override
    public String toString() {
        return "Vehicle{plateNumber='" + plateNumber + "', ownerName='" + ownerName + "', type='" + type + "'}";
    }
}

class ParkingSlot {
    private int slotId;
    private String location;

    public ParkingSlot(int slotId, String location) {
        this.slotId = slotId;
        this.location = location;
    }

    public int getSlotId() {
        return slotId;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingSlot that = (ParkingSlot) o;
        return slotId == that.slotId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(slotId);
    }

    @Override
    public String toString() {
        return "ParkingSlot{slotId=" + slotId + ", location='" + location + "'}";
    }
}

public class ParkingManagementSystem {
    private Set<Vehicle> allRegisteredVehicles;
    private Map<ParkingSlot, Vehicle> occupiedSlots;

    public ParkingManagementSystem() {
        this.allRegisteredVehicles = new HashSet<>();
        this.occupiedSlots = new HashMap<>();
    }

    public void parkVehicle(Vehicle vehicle, ParkingSlot slot) {
        allRegisteredVehicles.add(vehicle);

        if (occupiedSlots.containsKey(slot)) {
            System.out.println("Slot " + slot.getSlotId() + " is already occupied by " + occupiedSlots.get(slot).getPlateNumber());
            return;
        }

        for (Map.Entry<ParkingSlot, Vehicle> entry : occupiedSlots.entrySet()) {
            if (entry.getValue().equals(vehicle)) {
                System.out.println("Vehicle " + vehicle.getPlateNumber() + " is already parked in slot " + entry.getKey().getSlotId());
                return;
            }
        }

        occupiedSlots.put(slot, vehicle);
        System.out.println("Vehicle " + vehicle.getPlateNumber() + " parked successfully in slot " + slot.getSlotId());
    }

    public void removeVehicle(String plateNumber) {
        ParkingSlot slotToRemove = null;
        for (Map.Entry<ParkingSlot, Vehicle> entry : occupiedSlots.entrySet()) {
            if (entry.getValue().getPlateNumber().equals(plateNumber)) {
                slotToRemove = entry.getKey();
                break;
            }
        }

        if (slotToRemove != null) {
            occupiedSlots.remove(slotToRemove);
            System.out.println("Vehicle " + plateNumber + " removed from slot " + slotToRemove.getSlotId());
        } else {
            System.out.println("Vehicle " + plateNumber + " not found in any parked slot.");
        }
    }

    public void updateVehicleDetails(String plateNumber, String newOwnerName, String newType) {
        boolean found = false;
        for (Vehicle v : occupiedSlots.values()) {
            if (v.getPlateNumber().equals(plateNumber)) {
                v.setOwnerName(newOwnerName);
                v.setType(newType);
                found = true;
                System.out.println("Updated details for parked vehicle " + plateNumber);
                break;
            }
        }
        if (!found) {
            for (Vehicle v : allRegisteredVehicles) {
                if (v.getPlateNumber().equals(plateNumber)) {
                    v.setOwnerName(newOwnerName);
                    v.setType(newType);
                    found = true;
                    System.out.println("Updated details for registered vehicle " + plateNumber + " (not currently parked)");
                    break;
                }
            }
        }

        if (!found) {
            System.out.println("Vehicle " + plateNumber + " not found in the system.");
        }
    }

    public void viewAllOccupiedSlots() {
        System.out.println("\n--- All Occupied Parking Slots ---");
        if (occupiedSlots.isEmpty()) {
            System.out.println("No slots are currently occupied.");
            return;
        }
        for (Map.Entry<ParkingSlot, Vehicle> entry : occupiedSlots.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        System.out.println("----------------------------------");
    }

    public ParkingSlot findSlotOfVehicle(String plateNumber) {
        for (Map.Entry<ParkingSlot, Vehicle> entry : occupiedSlots.entrySet()) {
            if (entry.getValue().getPlateNumber().equals(plateNumber)) {
                System.out.println("Vehicle " + plateNumber + " is parked in slot " + entry.getKey().getSlotId());
                return entry.getKey();
            }
        }
        System.out.println("Vehicle " + plateNumber + " is not currently parked.");
        return null;
    }

    public List<Vehicle> findAllVehiclesByType(String type) {
        List<Vehicle> vehiclesOfType = new ArrayList<>();
        System.out.println("\n--- Vehicles of type '" + type + "' ---");
        boolean found = false;
        for (Vehicle v : occupiedSlots.values()) {
            if (v.getType().equalsIgnoreCase(type)) {
                vehiclesOfType.add(v);
                System.out.println(v);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No vehicles of type '" + type + "' currently parked.");
        }
        System.out.println("----------------------------------");
        return vehiclesOfType;
    }

    public void displayVehiclesSortedByOwnerName() {
        List<Vehicle> sortedVehicles = new ArrayList<>(occupiedSlots.values());
        Collections.sort(sortedVehicles, Comparator.comparing(Vehicle::getOwnerName));
        System.out.println("\n--- Parked Vehicles Sorted by Owner Name ---");
        if (sortedVehicles.isEmpty()) {
            System.out.println("No vehicles currently parked to sort.");
            return;
        }
        for (Vehicle v : sortedVehicles) {
            System.out.println(v);
        }
        System.out.println("------------------------------------------");
    }

    public void displaySlotsSortedBySlotId() {
        List<ParkingSlot> sortedSlots = new ArrayList<>(occupiedSlots.keySet());
        Collections.sort(sortedSlots, Comparator.comparingInt(ParkingSlot::getSlotId));
        System.out.println("\n--- Occupied Slots Sorted by Slot ID ---");
        if (sortedSlots.isEmpty()) {
            System.out.println("No slots currently occupied to sort.");
            return;
        }
        for (ParkingSlot slot : sortedSlots) {
            System.out.println(slot + " -> " + occupiedSlots.get(slot));
        }
        System.out.println("--------------------------------------");
    }

    public static void main(String[] args) {
        ParkingManagementSystem pms = new ParkingManagementSystem();

        Vehicle car1 = new Vehicle("ABC123", "Alice", "Car");
        Vehicle suv1 = new Vehicle("XYZ789", "Bob", "SUV");
        Vehicle bike1 = new Vehicle("MNO456", "Charlie", "Bike");
        Vehicle car2 = new Vehicle("PQR000", "David", "Car");
        Vehicle suv2 = new Vehicle("GHI111", "Eve", "SUV");

        ParkingSlot s1 = new ParkingSlot(1, "A-1");
        ParkingSlot s2 = new ParkingSlot(2, "A-2");
        ParkingSlot s3 = new ParkingSlot(3, "B-1");
        ParkingSlot s4 = new ParkingSlot(4, "B-2");
        ParkingSlot s5 = new ParkingSlot(5, "C-1");

        System.out.println("--- Parking Vehicles ---");
        pms.parkVehicle(car1, s1);
        pms.parkVehicle(suv1, s2);
        pms.parkVehicle(bike1, s3);
        pms.parkVehicle(car2, s4);
        pms.parkVehicle(suv2, s5);

        pms.parkVehicle(new Vehicle("TEST1", "Test", "Car"), s1);
        pms.parkVehicle(car1, new ParkingSlot(6, "D-1"));

        pms.viewAllOccupiedSlots();

        System.out.println("\n--- Updating Vehicle Details ---");
        pms.updateVehicleDetails("ABC123", "Alice Smith", "Sedan");
        pms.updateVehicleDetails("XYZ789", "Robert Johnson", "Large SUV");
        pms.updateVehicleDetails("NONEXISTENT", "Someone", "Unknown");

        pms.viewAllOccupiedSlots();

        System.out.println("\n--- Searching for Vehicles ---");
        pms.findSlotOfVehicle("MNO456");
        pms.findSlotOfVehicle("GHI111");
        pms.findSlotOfVehicle("NOTHERE");

        pms.findAllVehiclesByType("SUV");
        pms.findAllVehiclesByType("Bike");
        pms.findAllVehiclesByType("Truck");

        System.out.println("\n--- Removing Vehicles ---");
        pms.removeVehicle("ABC123");
        pms.removeVehicle("NONEXISTENT");

        pms.viewAllOccupiedSlots();

        pms.displayVehiclesSortedByOwnerName();

        pms.displaySlotsSortedBySlotId();
    }
}


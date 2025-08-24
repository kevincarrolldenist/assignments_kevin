package org.assignment6;

import java.util.Scanner;

public class SeatAllocationApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FlightBooking bookingSystem = new FlightBooking();

        System.out.println("Welcome to Flight Seat Booking System!");
        System.out.println("There are " + bookingSystem.getTotalSeats() + " seats available (1-" + bookingSystem.getTotalSeats() + ").");
        bookingSystem.displaySeatStatus();

        while (true) {
            System.out.print("\nEnter seat number to book (or 'exit' to quit): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            try {
                int seatNumber = Integer.parseInt(input);
                bookingSystem.bookSeat(seatNumber);
                System.out.println("Seat " + seatNumber + " successfully booked!");
            } catch (NumberFormatException e) {
                System.err.println("Error: Invalid input. Please enter a numeric seat number.");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.err.println("Error: " + e.getMessage());
            } catch (IllegalStateException e) {
                System.err.println("Error: " + e.getMessage());
            } finally {
                bookingSystem.displaySeatStatus();
            }
        }

        System.out.println("Thank you for using the Flight Seat Booking System.");
        scanner.close();
    }
}

class FlightBooking {
    private boolean[] seats = new boolean[5];

    public boolean bookSeat(int seatNumber) {
        int arrayIndex = seatNumber - 1;

        if (arrayIndex < 0 || arrayIndex >= seats.length) {
            throw new ArrayIndexOutOfBoundsException("Seat number " + seatNumber + " is out of valid range (1-" + seats.length + ").");
        }

        if (seats[arrayIndex]) {
            throw new IllegalStateException("Seat " + seatNumber + " is already booked.");
        }

        seats[arrayIndex] = true;
        return true;
    }

    public int getTotalSeats() {
        return seats.length;
    }

    public void displaySeatStatus() {
        System.out.print("Current Seat Status: [");
        for (int i = 0; i < seats.length; i++) {
            System.out.print("Seat " + (i + 1) + ": " + (seats[i] ? "Booked" : "Available"));
            if (i < seats.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}


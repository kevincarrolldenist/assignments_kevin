package org.assignment7;

public class MovieBookingApp {

    public static void main(String[] args) {
        TicketBooking bookingSystem = new TicketBooking();

        System.out.println("Scenario 1: Booking 4 tickets for Avengers...");
        try {
            bookingSystem.bookTickets("Avengers", 4);
            System.out.println("Booking successful!");
        } catch (TicketLimitExceededException e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println("\nScenario 2: Booking 7 tickets for Oppenheimer...");
        try {
            bookingSystem.bookTickets("Oppenheimer", 7);
            System.out.println("Booking successful!");
        } catch (TicketLimitExceededException e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println("\nScenario 3: Booking 6 tickets for Barbie...");
        try {
            bookingSystem.bookTickets("Barbie", 6);
            System.out.println("Booking successful!");
        } catch (TicketLimitExceededException e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println("\nScenario 4: Booking 0 tickets for Dune...");
        try {
            bookingSystem.bookTickets("Dune", 0);
            System.out.println("Booking successful!");
        } catch (TicketLimitExceededException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

class TicketLimitExceededException extends RuntimeException {
    public TicketLimitExceededException(String message) {
        super(message);
    }
}

class TicketBooking {
    private static final int MAX_TICKETS_PER_BOOKING = 6;

    public void bookTickets(String movieName, int quantity) {
        System.out.print("Booking " + quantity + " tickets for " + movieName + "... ");
        if (quantity <= 0) {
            return;
        }
        if (quantity > MAX_TICKETS_PER_BOOKING) {
            throw new TicketLimitExceededException("Cannot book more than " + MAX_TICKETS_PER_BOOKING + " tickets at once.");
        }
    }
}

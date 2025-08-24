class UnderAgeException extends Exception {
    public UnderAgeException(String message) {
        super(message);
    }
}

class VotingSystem {
    public void checkAge(int age) throws UnderAgeException {
        if (age < 18) {
            throw new UnderAgeException("You must be at least 18 years old to vote.");
        } else {
            System.out.println("You are eligible to vote");
        }
    }
}

public class voting{
    public static void main(String[] args) {
        VotingSystem system = new VotingSystem();

        try {
            system.checkAge(20);
        } catch (UnderAgeException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        try {
            system.checkAge(15);
        } catch (UnderAgeException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}

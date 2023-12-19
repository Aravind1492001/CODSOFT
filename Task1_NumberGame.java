import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        int minimumValue = 1;
        int maximumValue = 100;
        int totalAttempts = 7; // total number of attempts allowed for each round

        int score = 0; // Score to keep track of number of attempts taken by the user

        do {
            System.out.println("Welcome to the Guessing Game!");
            int randomNumber = cerateRandomNumber(minimumValue, maximumValue);
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("Guess the number between " + minimumValue + " & " + maximumValue);

            //This allows the user to guess during the allotted number of tries.

            while (attempts < totalAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = sc.nextInt();
                attempts++;

                // Check if the user's guess is correct
                if (userGuess == randomNumber) {
                    guessedCorrectly = true;
                    System.out.println("Congratulations! You guessed the number correctly in " + attempts + " attempts.");
                    // Update the score based on the performance in this round
                    score += totalAttempts - attempts + 1;
                    break;
                } else if (userGuess < randomNumber) {
                    System.out.println("It's too low mate! guess high.");
                } else {
                    System.out.println("It's too high buddy! guess low.");
                }
            }

            // Display a message if the user couldn't guess the correct number within the attempts
            if (!guessedCorrectly) {
                System.out.println("uhhh Bad Luck Champ!!. The correct number was: " + randomNumber);
            }

            // Ask the user if they want to play again
            System.out.print("Do you want to play this again? (yes/no): ");
        } while (sc.next().equalsIgnoreCase("yes"));

        // Display the total score across all rounds
        System.out.println("Man, that was a great game!! Your total score is: " + score);
        System.out.println("Thanks for playing! Have a Nice Day.");
    }

    // Method to generate a random number within the specified range
    private static int cerateRandomNumber(int lowerBound, int upperBound) {
        return lowerBound + new Random().nextInt(upperBound - lowerBound + 1);
    }
}


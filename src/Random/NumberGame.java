package Random;

import java.util.Random;
import java.util.Scanner;

public class NumberGame {

    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        
        boolean playAgain;
        int winCount = 0; 

        do {
            int randomInt = random.nextInt(101);
            System.out.println("Guess a number between 1 to 100 \nYou have 5 attempts to guess");

            boolean hasGuessedCorrectly = false;

            for (int i = 0; i <5; i++) {
                int userGuess = scanner.nextInt();

                if (userGuess == randomInt) {
                    System.out.println("Oh! You got it right");
                    hasGuessedCorrectly = true;
                    winCount++;
                    break;
                } else if (userGuess > randomInt) {
                    System.out.println("You guessed too high");
                } else {
                    System.out.println("You guessed too low");
                }
            }

            if (!hasGuessedCorrectly) {
                System.out.println("Sorry! The correct number was: " + randomInt);
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String response = scanner.next();
            playAgain = response.equalsIgnoreCase("yes");

        } while (playAgain);

        System.out.println("Thank you for playing!");
        System.out.println("You won " + winCount + " times.");

        scanner.close(); // Close the scanner
    }
}

package methods;

import java.io.PrintWriter;
import java.util.Scanner;

public class BetterWordle {

    // open scanner for input
    static Scanner in = new Scanner(System.in);
    // PrintWrite to display Unicode
    static PrintWriter printWriter = new PrintWriter(System.out,true);

    public static boolean hasUserWon(String guess, String solution) {
        if (solution.equals(guess)) {
            return true;
        } else {
            return false;
        }
    }

    public static void displayOutput(String guess, String solution) {
        // check guess correctness
        String output = "";
        for (int i = 0; i < 5; i++) {
            if (guess.charAt(i) == solution.charAt(i)) {
                output = output + "🟩";
            } else if (solution.contains(Character.toString(guess.charAt(i))) ) {
                output = output + "🟨";
            } else {
                output = output + "⬛";
            }
        }
        printWriter.println(output);
    }

    public static void main(String[] args) {

        // set the word that to be guessed
        String solution = "CATCH";
        // TODO: different words String solution = getSolution();

        // Print welcome message
        System.out.println("Welcome to Wordle!");

        boolean game_won = false;
        int tries_left = 6;
        
        // Ask for input
        System.out.println("Please enter your guess in all caps:");
        while (!game_won && tries_left > 0) {

            // check input validity
            String guess = in.nextLine();
            if (guess.length() != 5) {
                System.out.println("Please enter a five-letter woCATCHrd.");
                continue;
            }; 
            // TODO: check for input of digits, special characters, ...
            // TODO: check for valid words from dictionary
            // TODO: Hardmode
            // TODO: Do not display duplicate characters

            displayOutput(guess, solution);
            tries_left--;
            game_won = hasUserWon(guess, solution);
            if (game_won) {
                System.out.println("You win :)");
            }
            // determine if user has lost
            if (tries_left == 0) {
                System.out.println("You lose :(");
            }
        }
        in.close(); 
    }
    
}

package methods;

import java.io.PrintWriter;
import java.util.Scanner;

public class Wordle {

    public static void main(String[] args) {
        // open scanner for input
        Scanner in = new Scanner(System.in);
        // PrintWrite to display Unicode
        PrintWriter printWriter = new PrintWriter(System.out,true);

        // set the word that to be guessed
        String solution = "CATCH";
        // TODO: different words

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
                System.out.println("Please enter a five-letter word.");
                continue;
            }; 
            // TODO: check for input of digits, special characters, ...
            // TODO: check for valid words from dictionary
            // TODO: Hardmode

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
            tries_left--;
            // display guess correctness
            printWriter.println(output);

            // determine if user has won
            if (solution.equals(guess)) {
                game_won = true;
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

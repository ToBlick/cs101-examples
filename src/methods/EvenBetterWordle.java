package methods;

import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Random;

public class EvenBetterWordle {

    // open scanner for input
    static Scanner in = new Scanner(System.in);
    // PrintWrite to display Unicode
    static PrintWriter printWriter = new PrintWriter(System.out,true);
    // Some five-letter words
    static String[] wordlist = {"CHAIR", "BREAD", "PLUMB", "TRUCK", "STORM", "TABLE", "VOICE", "WATER", 
                                "GRAPE", "PAPER", "SUNNY", "TIGER", "MUSIC", "EARTH", "JOKER", "FLOOR", 
                                "WATCH", "RIVER", "LIGHT", "HAPPY", "YOUTH", "QUEST", "DREAM", "PLATE", 
                                "WOMAN", "CHILD"};
    static String validcharsstring = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z";

    public static boolean inputIsValid(String input) {
        if (input.length() != 5) {
            System.out.println("Please enter a five-letter word.");
            return false;
        }
        for (int i = 0; i < 5; i++) {
            if (!charIsInString(input.charAt(i), validcharsstring)) {
                System.out.println("Please use only capital letters.");
                return false;
            }
        }
        return true;
    }

    public static boolean charIsInString(char c, String s) {
        return s.contains(Character.toString(c));
    }

    public static boolean charIsInCharArr(char c, char[] s) {
        for (int i = 0; i < s.length; i++) {
            if (c == s[i]) {
                s[i] = '0'; // patch it!
                return true;
            }
        }
        return false;
    }

    public static String getWord() {
        Random r = new Random();
        return wordlist[r.nextInt(wordlist.length)];
    }

    public static boolean hasUserWon(String guess, String solution) {
        if (solution.equals(guess)) {
            return true;
        } else {
            return false;
        }
    }

    public static void displayOutput(String guess, String solution) {
        // conver input into char array
        char[] guessArr = guess.toCharArray();
        char[] solutionArr = solution.toCharArray();

        // check guess correctness
        char[] output = new char[5];
        for (int i = 0; i < 5; i++) {
            if (guessArr[i] == solutionArr[i]) {
                solutionArr[i] = '0'; // patch it
                output[i] = 'g';
            } else if (charIsInCharArr(guessArr[i], solutionArr) ) {
                output[i] = 'o';
                /* TODO: If the string contains the same latter multiple times, this will output in a wrong way.
                * Example: True word is CATCH
                * User guess: CATTY
                * Current output: 🟩🟩🟩🟨⬛
                * Correct output: 🟩🟩🟩⬛⬛ (no second T present in solution)
                * Other example:
                * True word is TOUCH
                * User guess: CATTY
                * Current output: 🟨⬛🟨🟨⬛
                * Correct output: 🟨⬛🟨⬛⬛ (no second T present in solution)
                */ 
            } else {
                output[i] = 'b';
            }
        }
        printWriter.println(output);
    }

    public static void main(String[] args) {

        // set the word that to be guessed
        String solution = getWord();
        // Print welcome message
        System.out.println("Welcome to Wordle!");
        boolean game_won = false;
        int tries_left = 6;
        // Ask for input
        System.out.println("Please enter your guess in all caps:");
        while (!game_won && tries_left > 0) {

            String guess = in.nextLine();
            if (!inputIsValid(guess)) continue;
            displayOutput(guess, solution);
            tries_left--;
            game_won = hasUserWon(guess, solution);
            if (game_won) {
                System.out.println("You win :)");
            } else if (tries_left == 0) {
                System.out.println("You lose :(");
            }
        }
        in.close(); 
    }
    
}

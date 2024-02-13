package basic_programming;

import java.util.Scanner;

public class Exceptions {
    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter an Int:");
        String input = scn.nextLine();
        try {
            // try to do the conversion...
            int i = Integer.parseInt(input); // may fail and produce an Exception
            System.out.println(i);
        }
        catch(NumberFormatException e) {
            // this block of code will run if there was a failure
            System.out.println(e); // will output the Exception but not crash the program
        }
        System.out.println("Done!");
        scn.close();
    }
}
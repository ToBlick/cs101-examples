package branching;

import java.util.Scanner;

public class Switching{
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
        System.out.println("What language do you want to you speak?");
        String input = scnr.nextLine();
        switch (input) {
            case "English":
                // fallthrough
            case "Spanish":
            	// fallthrough
            case "Tamil":
            	System.out.println("I speak " + input + ", too!");
            case "German":
                // fallthrough
            case "Shona":
                // fallthrough
            case "Mandarin":
                // fallthrough
            case "French":
            	System.out.println("I speak a bit of " + input + " myself.");
            default:
            	System.out.println("I do not know " + input + ".");
        }
        scnr.close();
    }
}
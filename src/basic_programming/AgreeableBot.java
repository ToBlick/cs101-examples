package basic_programming;

import java.util.Scanner;

public class AgreeableBot {
    public static void main(String[] args) {
        System.out.println("What's on your mind? ");
        Scanner scnr = new Scanner(System.in);
        String response = scnr.nextLine();
        System.out.println("I'm also thinking about " +  response + "!");
        scnr.close();
    }
}
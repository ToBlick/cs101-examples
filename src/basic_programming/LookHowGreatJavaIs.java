package basic_programming;

import java.util.Scanner;

public class LookHowGreatJavaIs {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        // get user's age
        System.out.println("Please enter your age: ");
        int ageAsInt = scnr.nextInt(); // an int
        // get user's name
        System.out.println("Please enter your name: ");
        String name = scnr.nextLine(); // a String
        // print out a friendly welcome message
        System.out.println("Welcome, " +  name + "! You are " + ageAsInt/7 + " years old in dog years!");
        scnr.close(); // close the Scanner to conserve resources
    }
}
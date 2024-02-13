package basic_programming;

import java.util.Scanner;

public class NoExceptions {
    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter an Int:");
        String input = scn.nextLine();
        int i = Integer.parseInt(input);
        System.out.println(i);
        System.out.println("Done!");
        scn.close();
    }
}
package basic_programming;

import java.util.Scanner;

public class StringComparison {
    public static void main(String[] args) throws Exception {
        String x = "hello";
        String y = "hello";
        boolean theSameMemoryAddress = (x == y);
        System.out.println("x == y:");
        System.out.println(theSameMemoryAddress);

        Scanner scn = new Scanner(System.in);
        System.out.println("Say hello!");
        String input = scn.nextLine();
        theSameMemoryAddress = (x == input);
        System.out.println("x == input:");
        System.out.println(theSameMemoryAddress);

        System.out.println("Say hello! (again)");
        input = scn.nextLine();
        boolean theSameContent = x.equals(input);
        System.out.println("x.equals(input):");
        System.out.println(theSameContent);

        scn.close();
    }
}
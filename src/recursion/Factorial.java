package recursion;

import java.util.Scanner;

public class Factorial {
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a number to find its factorial: ");
		int n = sc.nextInt();
		System.out.println("Factorial of " + n + " is: " + fact(n));
		sc.close();
		
	}
	
	public static int fact(int n) {
		
		// base case
		if (n == 0) return 1;
		// recursion case
		return n * fact(n-1);
		
	}

}

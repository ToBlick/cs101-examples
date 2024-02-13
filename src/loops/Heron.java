package loops;

import java.util.Scanner;

/**
 * A method to compute the square root.
 * https://en.wikipedia.org/wiki/Methods_of_computing_square_roots#Heron's_method
 * 
 */
public class Heron {

	public static void main(String[] args) {

		Scanner scn = new Scanner(System.in);
		System.out.println("What number would you like the square root of?");
		double S = Double.parseDouble(scn.nextLine());
		
		if (S == 0.0) {
            System.out.println("The square root of 0 is 0.");
			scn.close();
            return;
        } else if (S < 0.0) {
			System.out.println("Negative number S entered, calculating sqrt(-S).");
			S = -S;
		}

		System.out.println("Computing sqrt(" + S + ").");
		scn.close();
		// a heuristic to determine how many iterations we need
		double tol = 0.001;
		// Note: The default value of 1e-15 is much to small, it leads to over a million iterations!
		// Hence, change it to something reasonable.
		int max_it = (int) Math.sqrt(1/tol);

		// initialize values
		double x = 0.5 * S;
		double sqrtS = Math.sqrt(S);

		for (int i = 1; i <= max_it; i++) {
			x = 0.5 * (x + S/x);
			System.out.println("After " + i + " iteration" + (i > 1 ? "s" : "") + ", the approximate value of sqrt(S) is " + x + ". ");
			System.out.println("The relative error is " + Math.abs(x/sqrtS - 1) + ".");
		}
	}
}

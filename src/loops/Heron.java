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
		double s = Double.parseDouble(scn.nextLine());
		
		if (s == 0.0) {
            System.out.println("The square root of 0 is 0.");
			scn.close();
            return;
        } else if (s < 0.0) {
			System.out.println("Negative number x entered, calculating sqrt(-S).");
			s = -s;
		}

		System.out.println("Computing sqrt(" + s + ").");
		scn.close();
		// a heuristic to determine how many iterations we need
		double tol = 0.01;
		int max_it = (int) Math.sqrt(1/tol);

		// initialize values
		double x = 0.5 * s;
		double sqrt_s = Math.sqrt(s);

		for (int i = 1; i <= max_it; i++) {
			double x_old = x;
			x = 0.5 * (x + s/x);
			System.out.println("After " + i + " iteration" + (i > 1 ? "s" : "") + ", the approximate value of sqrt(S) is " + x + ". ");
			System.out.println("The relative error is " + Math.abs(x/sqrt_s - 1) + ".");
			System.out.println();
			if (Math.abs(x - x_old) < 1e-6)
				break;
		}
	}
}

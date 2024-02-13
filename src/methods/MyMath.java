package methods;

import java.util.Random;

public class MyMath {
    /**
     * Computing the square root with Heron's method.
     * @param x a strictly positive number.
     * @return the square root of x.
     */
    public static double myRoot(double x) {
        if (x == 0.0) {
            return x;
        } else if (x < 0.0) {
            System.out.println("Error: Negative number entered.");
            return 0.0; 
            // this is not ideal but we will discuss exception handling later
        }
        int max_it = 10;
        double s = 0.5 * x;
        for (int i = 1; i <= max_it; i++) {
            s = 0.5 * (s + x/s);
        }
        return s;
    }

    public static void main(String[] args) {
        Random rng = new Random();
        double x = rng.nextDouble();
        System.out.println("The square root of " + x + " is " + myRoot(x) + ".");
    }
}

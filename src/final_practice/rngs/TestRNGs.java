package final_practice.rngs;

import java.util.Arrays;

public class TestRNGs {

    public static int[] testRNG(RandomNumberGenerator rng, int n) {
        int[] counts = new int[10];
        for (int i = 0; i < n; i++) {
            int r = (int) (10 * rng.nextDouble());
            counts[r]++;
        }
        return counts;
    }

    public static void main(String[] args) {
        int seed = 1; // normally would do something like Integer.parseInt(args[0]); here
        try {
            new MLCG(seed);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            System.err.println("Using default seed 123.");
            seed = 123; // default seed
        } // try/catch block is not asked in the problem statement but illustrates the point of the exception
        RandomNumberGenerator r = new MLCG(seed);
        int[] counts = testRNG(r, 10_000);
        System.err.println(Arrays.toString(counts));
    }
}

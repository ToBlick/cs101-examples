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
        int seed = 1234; // normally would do something like Integer.parseInt(args[0]); here
        try {
            new MLCG(seed);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            System.err.println("Using default seed 1234.");
            seed = 1234; // default seed
        } // try/catch block is not asked in the problem statement but illustrates the point of the exception
        MLCG r1 = new MLCG(seed);
        VonNeumannRNG r2 = new VonNeumannRNG(seed);
        RNGWrapper r3 = new RNGWrapper(seed);
        r2.nextInt();
        RandomNumberGenerator[] myRNGs = {r1, r2, r3};
        for (int i = 0; i < 100; i++) {
            System.err.println(r2.nextInt());
        }
        for (RandomNumberGenerator r : myRNGs) {
            int[] counts = testRNG(r, 10_000);
            System.err.println(Arrays.toString(counts));
        }
        FourtyTwo theWorstRNGEver = new FourtyTwo();
        for (int i = 0; i < 3; i++) {
            System.err.println(theWorstRNGEver.nextDouble());
        }
    }
}

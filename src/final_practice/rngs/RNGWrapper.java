package final_practice.rngs;

import java.util.Random;

public class RNGWrapper extends RandomNumberGenerator {
    
    private Random r;

    public RNGWrapper(int seed) {
        super(seed);
        r = new Random(seed);
    }

    public int nextInt() {
        return r.nextInt();
    }

    public boolean nextBoolean() {
        return r.nextBoolean();
    }

    public double nextDouble() {
        return r.nextDouble();
    }
}
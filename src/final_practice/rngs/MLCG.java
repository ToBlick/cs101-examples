package final_practice.rngs;

public class MLCG extends RandomNumberGenerator {

    private static final int M = (int) Math.pow(2, 31) - 1;
    private static final int A = 16_807;

    public MLCG(int seed) throws IllegalArgumentException {
        super(seed);
        if (seed <= 0 || seed >= M) {
            throw new IllegalArgumentException("Seed must be in the range [0, 2^31 - 1]");
        }
    }

    public int nextInt() {
        state = (A * state) % M;
        // hacky way to handle integer overflow (this is not asked in the problem statement)
        if (state < 0) {
           state *= -1;
        }
        return state;
    }

    public boolean nextBoolean() {
        return nextDouble() < 0.5;
    }

    public double nextDouble() {
        return nextInt() * 1.0 / M;
    }

}

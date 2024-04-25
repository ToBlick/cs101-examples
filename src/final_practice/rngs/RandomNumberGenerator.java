package final_practice.rngs;

public abstract class RandomNumberGenerator {
    protected int state;
    public RandomNumberGenerator(int seed) {
        state = seed;
    }
    public abstract int nextInt();
    public abstract boolean nextBoolean();
    public abstract double nextDouble(); // returns a random number in the range [0, 1)
}
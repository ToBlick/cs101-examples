package final_practice.rngs;

public class VonNeumannRNG extends RandomNumberGenerator {

    public VonNeumannRNG(int seed) {
        super(seed);
    }

    private int[] toEightDigitArray(int n) {
        int[] digits = new int[8];
        for (int i = 0; i < 8; i++) {
            digits[7-i] = n / (int) Math.pow(10,i) % 10;
        }
        return digits;
    }

    private int toInt(int[] digits) {
        int n = 0;
        for (int i = 0; i < digits.length; i++) {
            n += digits[3-i] * Math.pow(10, i);
        }
        return n;
    }

    public int nextInt() {
        int[] longStateArray = toEightDigitArray(state * state);
        int[] shortStateArray = {longStateArray[2], longStateArray[3], longStateArray[4], longStateArray[5]};
        state = toInt(shortStateArray);
        return state;
    }

    public double nextDouble() {
        // largest possible number is 9999 = 10^4 - 1
        return nextInt() / Math.pow(10, 4);
    }

    public boolean nextBoolean() {
        return nextDouble() < 0.5;
    }

    

}

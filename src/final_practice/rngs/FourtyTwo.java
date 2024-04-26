package final_practice.rngs;

import java.util.Random;

public class FourtyTwo extends Random {
    
    @Override
    public int next(int bits) {
        return 42;
    }

}

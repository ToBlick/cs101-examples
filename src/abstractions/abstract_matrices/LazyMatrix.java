package abstractions.abstract_matrices;

import java.util.function.BiFunction;

public class LazyMatrix extends AbstractMatrix{

    private int[] v;
    private int[] w;

    private BiFunction<Integer, Integer, Double> function;

    public LazyMatrix(BiFunction<Integer, Integer, Double> function, int[] v, int[] w) {
        this.function = function;
        this.v = v;
        this.w = w;
        this.rows = v.length;
        this.cols = w.length;
    }

    public double getIndex(int i, int j) {
        return function.apply(v[i], w[j]);
    }

    public void setIndex(int i, int j, int value) {
        throw new UnsupportedOperationException("Cannot set values in a LazyMatrix.");
    }

    public AbstractMatrix transpose() {
        return new LazyMatrix(function, w, v);
    }


    
}

package abstract_matrices;


import java.util.function.BiFunction;

public class LazyMatrix extends AbstractMatrix{

    private int[] v;
    private int[] w;

    private BiFunction<Integer, Integer, Integer> function;

    public LazyMatrix(BiFunction<Integer, Integer, Integer> function, int[] v, int[] w) {
        this.function = function;
        this.v = v;
        this.w = w;
        this.rows = v.length;
        this.cols = w.length;
    }

    public int getIndex(int i, int j) {
        return function.apply(v[i], w[j]);
    }

    public void setIndex(int i, int j, int value) {
        throw new UnsupportedOperationException("Cannot set values in a LazyMatrix.");
    }

    public AbstractMatrix transpose() {
        return new LazyMatrix(function, w, v);
    }


    
}

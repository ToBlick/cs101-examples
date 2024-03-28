package abstract_matrices;

import java.util.function.BiFunction;

public class TestMatrix {

    public static void main(String[] args) {

        int n = 5;

        Matrix m = new Matrix(n, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                m.setIndex(i, j, i + j);
            }
        }        
        m.print();
        System.out.println();

        SparseMatrix sm = new SparseMatrix(n, n);
        for (int i = 0; i < n; i++) {
            sm.setIndex(i, i, i);
        }
        sm.print();
        System.out.println();

        int[] v = new int[n];
        for (int i = 0; i < n; i++) {
            v[i] = i;
        }
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = i;
        }
        BiFunction<Integer, Integer, Integer> f = (i, j) -> i + j;
        LazyMatrix lm = new LazyMatrix(f, v, w);
        lm.print();
    }
}

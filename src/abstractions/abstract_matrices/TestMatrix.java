package abstractions.abstract_matrices;

import java.util.function.BiFunction;

public class TestMatrix {

    public static void main(String[] args) {

        int n = 5;

        DenseMatrix m = new DenseMatrix(n, n);
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
        
        IdentityMatrix im = new IdentityMatrix(n);
        im.print();
        System.out.println();
        
        double[] d = {1, 2, 3, 4, 5};
        DiagonalMatrix dm = new DiagonalMatrix(d);
        dm.print();
        System.out.println();
        
        DenseMatrix ddm = new DenseMatrix(dm);
        ddm.print();
        System.out.println();

       int[] v = new int[n];
       for (int i = 0; i < n; i++) {
           v[i] = i;
       }
       int[] w = new int[n];
       for (int i = 0; i < n; i++) {
           w[i] = i;
       }
       BiFunction<Integer, Integer, Double> f = (i, j) -> (double) (i + j);
       LazyMatrix lm = new LazyMatrix(f, v, w);
       lm.print();
    }
}

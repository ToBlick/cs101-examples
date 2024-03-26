package matrices;

public class Timings {

    public static void main(String[] args) {
        int n = 20_000;
        int nonZeroPerRow = 5;

        Matrix x = new Matrix(n, 1);

        SparseMatrix sm = new SparseMatrix(n, n);
        sm.randomFill(nonZeroPerRow);

        long start = System.currentTimeMillis();
        Matrix b1 = sm.multiply(x);
        long end = System.currentTimeMillis();
        System.out.println("Sparse matrix-vector multiplication took " + (end - start) + " ms.");

        Matrix m = sm.toMatrix();

        start = System.currentTimeMillis();
        Matrix b2 = m.multiply(x);
        end = System.currentTimeMillis();
        System.out.println("Matrix-vector multiplication took " + (end - start) + " ms.");
        if (b1.equals(b2)) {
            System.out.println("Results are equal.");
        } else {
            System.out.println("Results are not equal.");
        }
    }
    
}

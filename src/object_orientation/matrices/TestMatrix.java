package object_orientation.matrices;

public class TestMatrix {

    public static void main(String[] args) {

        int n = 5;

        DenseMatrix m1 = new DenseMatrix(n, n);
        DenseMatrix m2 = new DenseMatrix(n, n);

        System.out.println("m1 is a " + m1.toString());
        System.err.println();

        m1.randomFill();
        m2.randomFill();
        
        m1.print();
//        System.err.println();
//        m2.print();
//        System.err.println();
//
//        DenseMatrix m3 = m1.multiply(m2);
//        m3.print();
//        System.err.println();
//
//        SparseMatrix sm1 = new SparseMatrix(n, n);
//        sm1.randomFill(2);
//        sm1.print();
//        System.err.println();
//        sm1.listPrint();
    }
}

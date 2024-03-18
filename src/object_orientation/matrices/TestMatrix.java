package matrices;

public class TestMatrix {

    public static void main(String[] args) {
        Matrix m1 = new Matrix(3, 3);
        Matrix m2 = new Matrix(3, 1);

        System.out.println("m1 is a " + m1.toString());
        System.err.println();

        m1.randomFill();
        m2.fill(1);
        
        m2.print();
        System.err.println();
        m1.print();
        System.err.println();
        m1.transpose().print();
        System.err.println();

        Matrix m3 = m1.multiply(m2);
        m3.print();

    }
    
}

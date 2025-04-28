package testing.tests;

import testing.*;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestMatrix {

    @Test
    public void testDense() throws Exception  {
        double[][] data = new double[][] { {1, 2, 3},
                                           {4, 5, 6},
                                           {7, 8, 9} };
        DenseMatrix m = new DenseMatrix(data);

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                assertEquals(m.getIndex(i, j), data[i][j], 1e-16);
            }
        }
    }

    @Test
    public void testSparse() throws Exception {
        
        double[][] data = new double[][] { {1, 2, 3},
                                           {4, 5, 6},
                                           {7, 8, 9} };
        SparseMatrix m = new SparseMatrix(data);

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                assertEquals(m.getIndex(i, j), data[i][j], 1e-16);
            }
        }
    }

}

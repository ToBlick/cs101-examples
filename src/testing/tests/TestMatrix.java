package testing.tests;

import testing.*;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestMatrix {

    double[][] data = new double[][] { {1.2, 0.0, 0.0},
                                           {0.0, 0.5, 3.1},
                                           {8.8, 0.0, 9.7} };

    DenseMatrix dense = new DenseMatrix(data);
    SparseMatrix sparse = new SparseMatrix(data);

    @Test
    public void testDense() throws Exception  {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                assertEquals(dense.getIndex(i, j), data[i][j], 1e-16);
            }
        }
    }

    @Test
    public void testSparse() throws Exception {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                assertEquals(sparse.getIndex(i, j), data[i][j], 1e-16);
            }
        }
    }

    @Test
    public void testEquals() throws Exception {
        AbstractMatrix[] matrices = new AbstractMatrix[] { dense, sparse };
        for (AbstractMatrix m1 : matrices) {
            for (AbstractMatrix m2 : matrices) {
                assertTrue(m1.equals(m2));
            }
        }
    }

    @Test
    public void testTranspose() throws Exception {
        AbstractMatrix denseT = dense.transpose();
        AbstractMatrix sparseT = sparse.transpose();
        assertTrue(sparseT.equals(denseT));
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                assertEquals(denseT.getIndex(j, i), data[i][j], 1e-16);
                assertEquals(sparseT.getIndex(j, i), data[i][j], 1e-16);
            }
        }
    }

    @Test
    public void testConversions() throws Exception {
        SparseMatrix sparseFromDense = new SparseMatrix(dense);
        DenseMatrix denseFromSparse = new DenseMatrix(sparse);

        AbstractMatrix[] matrices = new AbstractMatrix[] {
            sparse, dense, sparseFromDense, denseFromSparse
        };
        for (AbstractMatrix m1 : matrices) {
            for (AbstractMatrix m2 : matrices) {
                assertTrue(m1.equals(m2));
            }
        }
    }

}

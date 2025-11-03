package abstractions.abstract_matrices;

import java.util.ArrayList;

public class SparseMatrix extends AbstractMatrix{

    private ArrayList<Integer> rowIndices;
    private ArrayList<Integer> colIndices;
    private ArrayList<Double> data;

    public SparseMatrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.rowIndices = new ArrayList<Integer>();
        this.colIndices = new ArrayList<Integer>();
        this.data = new ArrayList<Double>();
    }
    
    public double getIndex(int i, int j) throws IndexOutOfBoundsException {
        if (i < 0 || i >= this.rows || j < 0 || j >= this.cols) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        for (int k = 0; k < this.data.size(); k++) {
            if (this.rowIndices.get(k) == i && this.colIndices.get(k) == j) {
                return this.data.get(k);
            }
        }
        return 0;
    }

    public int getNonZeroElements() {
        return this.data.size();
    }

    public void setIndex(int i, int j, double value) throws IndexOutOfBoundsException {
        if (i < 0 || i >= this.rows || j < 0 || j >= this.cols) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        // TODO: check if this is already nonzero
        if (value == 0) {
            return;
        }
        // sorting the indices would be better for performance
        for (int k = 0; k < this.data.size(); k++) {
            if (this.rowIndices.get(k) == i && this.colIndices.get(k) == j) {
                this.data.set(k, value);
                return;
            }
        }
        this.rowIndices.add(i);
        this.colIndices.add(j);
        this.data.add(value);
    }

//     public boolean equals(SparseMatrix other) {
//         // TODO: Implement equals to other Sparse Matrix that is much faster.
//         // Only needs a loop of size #nonzeroelements
//     }
    
    public SparseMatrix transpose() {
        SparseMatrix result = new SparseMatrix(this.getCols(), this.getRows());
        for (int k = 0; k < this.data.size(); k++) {
            result.setIndex(this.colIndices.get(k), this.rowIndices.get(k), this.data.get(k));
        }
        return result;
    }


    public DenseMatrix toMatrix() {
        DenseMatrix result = new DenseMatrix(this.getRows(), this.getCols());
        for (int k = 0; k < this.data.size(); k++) {
            result.setIndex(this.rowIndices.get(k), this.colIndices.get(k), this.data.get(k));
        }
        return result;
    }

    public void randomFill(int nonZeroPerRow) {
        for (int k = 0; k < nonZeroPerRow*nonZeroPerRow ; k++) {
            int i = (int) (Math.random() * this.rows);
            int j = (int) (Math.random() * this.cols);
            this.setIndex(i, j, (int) (10 * Math.random()));
        }
    }

    public void print() {
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getCols(); j++) {
                if (this.getIndex(i, j) == 0) 
                    System.out.print(" . ");
                else
                    System.out.print(this.getIndex(i, j));
                System.out.print(" ");
            }
            System.out.println();
        }
    }

}
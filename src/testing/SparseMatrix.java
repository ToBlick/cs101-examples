package testing;

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

    public SparseMatrix(double[][] data) {
        this.rows = data.length;
        this.cols = data[0].length;
        this.rowIndices = new ArrayList<Integer>();
        this.colIndices = new ArrayList<Integer>();
        this.data = new ArrayList<Double>();
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.setIndex(i, j, data[i][j]);
            }
        }
    }

    public SparseMatrix(AbstractMatrix m) {
        this.rows = m.getRows();
        this.cols = m.getCols();
        this.rowIndices = new ArrayList<Integer>();
        this.colIndices = new ArrayList<Integer>();
        this.data = new ArrayList<Double>();
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.setIndex(i, j, m.getIndex(i, j));
            }
        }
    }

    public int getNonZeroElements() {
        return this.data.size();
    }

    public void setIndex(int i, int j, double value) throws IndexOutOfBoundsException {
        if (i < 0 || i >= this.rows || j < 0 || j >= this.cols) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
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

    public boolean equals(SparseMatrix other) {
        if ( (other == null) || 
            (this.rows != other.rows || this.cols != other.cols) || 
            (this.data.size() != other.data.size()) ) {
            return false;
        }
        // Check if all non-zero elements are the same
        for (int k = 0; k < this.data.size(); k++) {
            int row = this.rowIndices.get(k);
            int col = this.colIndices.get(k);
            double value = this.data.get(k);
            
            if (other.getIndex(row, col) != value) {
                return false;
            }
        }
        return true;
    }
    
    public SparseMatrix transpose() {
        SparseMatrix result = new SparseMatrix(this.getCols(), this.getRows());
        for (int k = 0; k < this.data.size(); k++) {
            result.setIndex(this.colIndices.get(k), this.rowIndices.get(k), this.data.get(k));
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

}
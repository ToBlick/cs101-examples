package matrices;

import java.util.ArrayList;

public class SparseMatrix {

    private int rows;
    private int cols;
    private ArrayList<Integer> rowIndices;
    private ArrayList<Integer> colIndices;
    private ArrayList<Integer> data;

    public SparseMatrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.rowIndices = new ArrayList<Integer>();
        this.colIndices = new ArrayList<Integer>();
        this.data = new ArrayList<Integer>();
    }

    public SparseMatrix(Matrix matrix) {
        this.rows = matrix.getRows();
        this.cols = matrix.getCols();
        this.rowIndices = new ArrayList<Integer>();
        this.colIndices = new ArrayList<Integer>();
        this.data = new ArrayList<Integer>();
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getCols(); j++) {
                this.setIndex(i, j, matrix.getIndex(i, j));
            }
        }
    }

    public int getRows() {
        return this.rows;
    }

    public int getCols() {
        return this.cols;
    }

    public int getNonZeroElements() {
        return this.data.size();
    }

    public Matrix toMatrix() {
        Matrix result = new Matrix(this.getRows(), this.getCols());
        for (int k = 0; k < this.data.size(); k++) {
            result.setIndex(this.rowIndices.get(k), this.colIndices.get(k), this.data.get(k));
        }
        return result;
    }

    public void setIndex(int i, int j, int value) throws IndexOutOfBoundsException {
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

    public int getIndex(int i, int j) throws IndexOutOfBoundsException {
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

    public void randomFill(int nonZeroPerRow) {
        for (int k = 0; k < nonZeroPerRow*nonZeroPerRow ; k++) {
            int i = (int) (Math.random() * this.rows);
            int j = (int) (Math.random() * this.cols);
            this.setIndex(i, j, (int) (10 * Math.random()));
        }
    }

    public boolean equals(SparseMatrix other) {
        if (this.getRows() != other.getRows() || this.getCols() != other.getCols()) {
            return false;
        }
        if (this.getNonZeroElements() != other.getNonZeroElements()) {
            return false;
        }
        int i;
        int j;
        for (int k = 0; k < this.data.size(); k++) {
            i = this.rowIndices.get(k);
            j = this.colIndices.get(k);
            if (other.getIndex(i, j) != this.data.get(k)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Matrix other) {
        if (this.getRows() != other.getRows() || this.getCols() != other.getCols()) {
            return false;
        }
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getCols(); j++) {
                if (this.getIndex(i, j) != other.getIndex(i, j)) {
                    return false;
                }
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

    public String toString() {
        return "Sparse matrix with " + this.getRows() + " rows, " + this.getCols() + " columns and " + this.data.size() + " non-zero elements.";
    }

    public void listPrint() {
        System.out.println(this.rowIndices.toString());
        System.out.println(this.colIndices.toString());
        System.out.println(this.data.toString());
    }

    public void print() {
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getCols(); j++) {
                if (this.getIndex(i, j) == 0) {
                    System.out.print(". ");
                } else {
                    System.out.print(this.getIndex(i, j));
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public Matrix multiply(Matrix other) throws IllegalArgumentException{
        if (this.getCols() != other.getRows()) {
            throw new IllegalArgumentException("Matrices cannot be multiplied.");
        }
        int r;
        int s;
        Matrix result = new Matrix(this.getRows(), other.getCols());
        for (int k = 0; k < this.data.size(); k++) {
            r = this.rowIndices.get(k);
            for (int l = 0; l < other.getCols(); l++) {
                s = 0;
                for (int m = 0; m < other.getRows(); m++) {
                    s += this.data.get(k) * other.getIndex(m, l);
                }
                result.setIndex(r, l, s);
            }
        }
        return result;
    }

    public double norm() {
        int sum = 0;
        for (int k = 0; k < this.data.size(); k++) {
            sum += this.data.get(k) * this.data.get(k);
        }
        return Math.sqrt(sum);
    }
}
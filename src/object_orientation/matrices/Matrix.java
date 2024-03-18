package matrices;

public class Matrix {

    private int[][] data;
    private int rows;
    private int cols;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new int[rows][cols];
    }

    public void setIndex(int row, int col, int value) {
        this.data[row][col] = value;
    }

    public int getIndex(int row, int col) {
        return this.data[row][col];
    }

    public int getRows() {
        return this.rows;
    }

    public int getCols() {
        return this.cols;
    }

    public void print() {
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getCols(); j++) {
                System.out.print(this.getIndex(i, j) + " ");
            }
            System.out.println();
        }
    }

    public Matrix add(Matrix other) {
        if (this.getRows() != other.getRows() || this.getCols() != other.getCols()) {
            System.out.println("Error: dimensions must agree.");
            return null;
        }
        Matrix result = new Matrix(this.getRows(), this.getCols());
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getCols(); j++) {
                result.setIndex(i, j, this.getIndex(i, j) + other.getIndex(i, j));
            }
        }
        return result;
    }

    public Matrix multiply(Matrix other) {
        if (this.getCols() != other.getRows()) {
            System.out.println("Error: dimensions must agree.");
            return null;
        }
        Matrix result = new Matrix(this.getRows(), other.getCols());
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < other.getCols(); j++) {
                int sum = 0;
                for (int k = 0; k < this.getCols(); k++) {
                    sum += this.data[i][k] * other.getIndex(k, j);
                }
                result.setIndex(i, j, sum);
            }
        }
        return result;
    }

    public String toString() {
        return "Matrix with " + this.getRows() + " getRows() and " + this.getCols() + " columns.";
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

    public void fill(int value) {
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getCols(); j++) {
                this.setIndex(i, j, value);
            }
        }
    }

    public void randomFill() {
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getCols(); j++) {
                this.setIndex(i, j, (int) (10 * Math.random()));
            }
        }
    }

    public Matrix transpose() {
        Matrix result = new Matrix(this.getCols(), this.getRows());
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getCols(); j++) {
                result.setIndex(j, i, this.getIndex(i, j));
            }
        }
        return result;
    }

    public Matrix invert() {
        if (this.getRows() != this.getCols()) {
            System.out.println("Error: matrix must be square to invert.");
            return null;
        }
        Matrix result = new Matrix(this.getRows(), this.getCols());
        // TODO ...

        return result;
    }
}

package object_orientation.matrices;

public class DenseMatrix {

    private int[][] data;
    private int rows;
    private int cols;

    public DenseMatrix(int rows, int cols) {
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
                System.out.print(this.getIndex(i, j));
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public DenseMatrix add(DenseMatrix other) {
        if (this.getRows() != other.getRows() || this.getCols() != other.getCols()) {
            System.out.println("Error: dimensions must agree.");
            return null;
        }
        DenseMatrix result = new DenseMatrix(this.getRows(), this.getCols());
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getCols(); j++) {
                result.setIndex(i, j, this.getIndex(i, j) + other.getIndex(i, j));
            }
        }
        return result;
    }

    public DenseMatrix subtract(DenseMatrix other) {
        if (this.getRows() != other.getRows() || this.getCols() != other.getCols()) {
            System.out.println("Error: dimensions must agree.");
            return null;
        }
        DenseMatrix result = new DenseMatrix(this.getRows(), this.getCols());
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getCols(); j++) {
                result.setIndex(i, j, this.getIndex(i, j) - other.getIndex(i, j));
            }
        }
        return result;
    }

    public DenseMatrix multiply(DenseMatrix other) {
        if (this.getCols() != other.getRows()) {
            System.out.println("Error: dimensions must agree.");
            return null;
        }
        int sum;
        DenseMatrix result = new DenseMatrix(this.getRows(), other.getCols());
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < other.getCols(); j++) {
                sum = 0;
                for (int k = 0; k < this.getCols(); k++) {
                    sum += this.data[i][k] * other.getIndex(k, j);
                }
                result.setIndex(i, j, sum);
            }
        }
        return result;
    }

    public DenseMatrix multiply(int a) {
        DenseMatrix result = new DenseMatrix(this.getRows(), this.getCols());
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getCols(); j++) {
                result.setIndex(i, j, a * this.getIndex(i, j));
            }
        }
        return result;
    }

    public String toString() {
        return "Matrix with " + this.getRows() + " and " + this.getCols() + " columns.";
    }

    public boolean equals(DenseMatrix other) {
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

    public DenseMatrix transpose() {
        DenseMatrix result = new DenseMatrix(this.getCols(), this.getRows());
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getCols(); j++) {
                result.setIndex(j, i, this.getIndex(i, j));
            }
        }
        return result;
    }

    public DenseMatrix invert() {
        if (this.getRows() != this.getCols()) {
            System.out.println("Error: matrix must be square to invert.");
            return null;
        }
        DenseMatrix result = new DenseMatrix(this.getRows(), this.getCols());
        // TODO ...

        return result;
    }

    public double norm() {
        int sum = 0;
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getCols(); j++) {
                sum += this.getIndex(i, j) * this.getIndex(i, j);
            }
        }
        return Math.sqrt(sum);
    }
}

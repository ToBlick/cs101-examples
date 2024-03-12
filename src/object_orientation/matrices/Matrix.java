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
        for (int i=0; i<this.rows; i++) {
            for (int j=0; j<this.cols; j++) {
                System.out.print(this.data[i][j] + " ");
            }
            System.out.println();
        }
    }

    public Matrix add(Matrix other) {
        if (this.rows != other.getRows() || this.cols != other.getCols()) {
            System.out.println("Error: cannot add matrices of different sizes.");
            return null;
        }
        Matrix result = new Matrix(this.rows, this.cols);
        for (int i=0; i<this.rows; i++) {
            for (int j=0; j<this.cols; j++) {
                result.setIndex(i, j, this.data[i][j] + other.getIndex(i, j));
            }
        }
        return result;
    }

    public Matrix multiply(Matrix other) {
        if (this.cols != other.getRows()) {
            System.out.println("Error: dimensions must agree.");
            return null;
        }
        Matrix result = new Matrix(this.rows, other.getCols());
        for (int i=0; i<this.rows; i++) {
            for (int j=0; j<other.getCols(); j++) {
                int sum = 0;
                for (int k=0; k<this.cols; k++) {
                    sum += this.data[i][k] * other.getIndex(k, j);
                }
                result.setIndex(i, j, sum);
            }
        }
        return result;
    }

    public String toString() {
        return "Matrix with " + this.rows + " rows and " + this.cols + " columns.";
    }

    public boolean equals(Matrix other) {
        if (this.rows != other.getRows() || this.cols != other.getCols()) {
            return false;
        }
        for (int i=0; i<this.rows; i++) {
            for (int j=0; j<this.cols; j++) {
                if (this.data[i][j] != other.getIndex(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void fill(int value) {
        for (int i=0; i<this.rows; i++) {
            for (int j=0; j<this.cols; j++) {
                this.data[i][j] = value;
            }
        }
    }

    public void randomFill() {
        for (int i=0; i<this.rows; i++) {
            for (int j=0; j<this.cols; j++) {
                this.data[i][j] = (int) (10 * Math.random());
            }
        }
    }

    public Matrix transpose() {
        Matrix result = new Matrix(this.cols, this.rows);
        for (int i=0; i<this.rows; i++) {
            for (int j=0; j<this.cols; j++) {
                result.setIndex(j, i, this.data[i][j]);
            }
        }
        return result;
    }

}

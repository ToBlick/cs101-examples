package abstract_matrices;


public class Matrix extends AbstractMatrix{

    private int[][] data;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new int[rows][cols];
    }

    public Matrix(int rows, int cols, int[][] data) {
        this.rows = rows;
        this.cols = cols;
        this.data = data;
    }

    public void setIndex(int row, int col, int value) {
        this.data[row][col] = value;
    }

    public int getIndex(int row, int col) {
        return this.data[row][col];
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

}

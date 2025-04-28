package testing;


public class DenseMatrix extends AbstractMatrix{

    private int[][] data;

    public DenseMatrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new int[rows][cols];
    }

    public DenseMatrix(int rows, int cols, int[][] data) {
        this.rows = rows;
        this.cols = cols;
        this.data = data;
    }

    // TODO: Write a constructor that only takes int[][] data.
    // should throw an error if the input array is ragged.

    public DenseMatrix(double[][] data) {
        this.rows = data.length;
        this.cols = data[0].length;
        this.data = new int[this.rows][this.cols];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.data[i][j] = (int) data[i][j] + 1;
            }
        }
    }

    public int getIndex(int i, int j) {
        return this.data[i][j];
    }

    public void setIndex(int row, int col, int value) {
        this.data[row][col] = value;
    }

    // TODO: implement this in-place
    public DenseMatrix transpose() {
        DenseMatrix result = new DenseMatrix(this.getCols(), this.getRows());
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

package abstractions.abstract_matrices;


public class DenseMatrix extends AbstractMatrix {

    private double[][] data;

    public DenseMatrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new double[rows][cols];
    }

    public DenseMatrix(double[][] data) {
        this.data = data;
        this.rows = data.length;
        this.cols = data[0].length;
        // TODO: throw an error when input array is ragged
    }
    
    public DenseMatrix(AbstractMatrix a) {
    	this.rows = a.getRows();
        this.cols = a.getCols();
        this.data = new double[this.rows][this.cols];
        
        for (int i = 0; i < this.rows; i++) {
        	for (int j = 0; j < this.cols; j++) {
        		this.data[i][j] = a.getIndex(i, j);
        	}
        }
    	
    }

    public double getIndex(int i, int j) {
        return this.data[i][j];
    }

    public void setIndex(int row, int col, double value) {
        this.data[row][col] = value;
    }
//
//    // TODO: implement this in-place
//    public DenseMatrix transpose() {
//        DenseMatrix result = new DenseMatrix(this.getCols(), this.getRows());
//        for (int i = 0; i < this.getRows(); i++) {
//            for (int j = 0; j < this.getCols(); j++) {
//                result.setIndex(j, i, this.getIndex(i, j));
//            }
//        }
//        return result;
//    }
//
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
                this.setIndex(i, j, Math.random());
            }
        }
    }

}

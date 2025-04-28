package abstractions.abstract_matrices;

public class DiagonalMatrix extends AbstractMatrix {
	
	private int[] diag;
	
	public DiagonalMatrix(int n) {
		this.diag = new int[n];
		this.rows = n;
		this.cols = n;
	}
	
	public DiagonalMatrix(int[] diag) {
		this.diag = diag;
		this.rows = diag.length;
		this.cols = diag.length;
	}
	
	public DiagonalMatrix(AbstractMatrix a) {
    	this.rows = a.getRows();
        this.cols = a.getCols();
        
        for (int i = 0; i < this.rows; i++) {
        	this.diag[i] = a.getIndex(i, i);
        }
    	
    }
	
	public int getIndex(int i, int j) {
		return i == j ? diag[i] : 0;
	}

}

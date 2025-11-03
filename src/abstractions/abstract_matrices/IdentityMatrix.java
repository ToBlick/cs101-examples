package abstractions.abstract_matrices;

public class IdentityMatrix extends DiagonalMatrix {
	
	public IdentityMatrix(int n) {
		super(n);
		this.rows = n;
		this.cols = n;
	}
	
	public double getIndex(int i, int j) {
		return i == j ? 1.0 : 0.0;
	}

}

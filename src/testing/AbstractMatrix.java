package testing;

public abstract class AbstractMatrix {

    protected int rows;
    protected int cols;
    
    public int getRows() {
        return this.rows;
    }
    public int getCols() {
        return this.cols;
    }

    public abstract int getIndex(int i, int j);

    public boolean equals(AbstractMatrix other) {
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

    public abstract AbstractMatrix transpose();

    public void print() {
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getCols(); j++) {
                System.out.print(this.getIndex(i, j));
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}

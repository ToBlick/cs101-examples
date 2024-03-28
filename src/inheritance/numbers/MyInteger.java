package inheritance.numbers;

/**
 * A class to represent a Integer number.
 */
public class MyInteger extends MyRational {
    
    // Constructor
    public MyInteger(int value) {
        super(value, 1);
    }

    // Basic methods
    public String toString() {
        return "" + this.getValue();
    }

    public boolean equals(MyInteger n) {
        return this.getValue() == n.getValue();
    }

    // Getters and Setters

    public int getValue() {
        return this.getNumerator();
    }

    // Conversion methods
    public MyRational toRational() {
        return super.copy();
    }

    // Operations with Integers
    public MyInteger add(MyInteger n) {
        return new MyInteger(this.getValue() + n.getValue());
    }

    public MyInteger subtract(MyInteger n) {
        return new MyInteger(this.getValue() - n.getValue());
    }

    public MyInteger multiply(MyInteger n) {
        return new MyInteger(this.getValue() * n.getValue());
    }

    public MyRational divide(MyInteger n) {
        return new MyRational(this.getValue(), n.getValue());
    }

    // Integer-specific operations

    public boolean isPrime() {
        if (this.getValue() < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(this.getValue()); i++) {
            if (this.getValue() % i == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isEven() {
        return this.getValue() % 2 == 0;
    }

}

package inheritance.numbers;

/**
 * A class to represent a MyRational number, which consists of two integers (numerator and denominator).
 */
public class MyRational {

    // Attributes
    private int numerator;
    private int denominator;

    // Constructor

    public MyRational(int numerator, int denominator) throws IllegalArgumentException {
        if (denominator == 0) {
            throw new IllegalArgumentException("Denominator cannot be zero.");
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    // Getters and Setters

    public int getNumerator() {
        return this.numerator;
    }

    public int getDenominator() {
        return this.denominator;
    }

    protected void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    protected void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    // Basic methods

    public String toString() {
        MyRational s = this.simplify();
        return "" + s.getNumerator() + "/" + s.getDenominator();
    }

    public boolean equals(MyRational r) {
        MyRational s = r.simplify();
        MyRational t = this.simplify();
        return t.getNumerator() == s.getNumerator() && t.getDenominator() == s.getDenominator();
    }

    public MyRational copy() {
        return new MyRational(this.getNumerator(), this.getDenominator());
    }

    public double toDouble() {
        return ((double) this.getNumerator()) / this.getDenominator();
    }

    // Operations with Rationals

    public MyRational add(MyRational r) {
        int newNumerator = this.getNumerator() * r.getDenominator() + r.getNumerator() * this.denominator;
        int newDenominator = this.getDenominator() * r.getDenominator();
        return new MyRational(newNumerator, newDenominator);
    }

    public MyRational subtract(MyRational r) {
        int newNumerator = this.getNumerator() * r.getDenominator() - r.getNumerator() * this.denominator;
        int newDenominator = this.getDenominator() * r.getDenominator();
        return new MyRational(newNumerator, newDenominator);
    }

    public MyRational multiply(MyRational r) {
        int newNumerator = this.getNumerator() * r.getNumerator();
        int newDenominator = this.getDenominator() * r.getDenominator();
        return new MyRational(newNumerator, newDenominator);
    }

    public MyRational divide(MyRational r) {
        int newNumerator = this.getNumerator() * r.getDenominator();
        int newDenominator = this.getDenominator() * r.getNumerator();
        return new MyRational(newNumerator, newDenominator);
    }

    // Convenience methods

    public MyRational simplify() {
        int num = this.getNumerator();
        int denom = this.denominator;
        int gcd = gcd(num, denom);
        num /= gcd;
        denom /= gcd;
        if (denom < 0) {
            denom *= -1;
            num *= -1;
        }
        return new MyRational(num, denom);
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
    
}

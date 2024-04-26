package final_practice.shapes;

public class RightTriangle extends Triangle {

    public RightTriangle(double base, double height) {
        super(base, height, Math.sqrt(base * base + height * height));
    }

    public double getArea() {
        return 0.5 * super.side1 * super.side2;
    }

}

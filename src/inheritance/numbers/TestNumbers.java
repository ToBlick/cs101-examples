package inheritance.numbers;

public class TestNumbers {

    public static void main(String[] args) {
        MyInteger n1 = new MyInteger(3);
        MyInteger n2 = new MyInteger(7);
        MyRational r1 = new MyRational(-5, 3);
        MyRational r2 = new MyRational(7, 2);

        System.out.println("n1: " + n1);
        System.out.println("n2: " + n2);
        System.out.println("r1: " + r1);
        System.out.println("r2: " + r2);

        System.out.println("n1 + n2: " + n1.add(n2));
        System.out.println("n1 - n2: " + n1.subtract(n2));
        System.out.println("n1 * n2: " + n1.multiply(n2));
        System.out.println("n1 / n2: " + n1.divide(n2));

        System.out.println("r1 + r2: " + r1.add(r2));
        System.out.println("r1 - r2: " + r1.subtract(r2));
        System.out.println("r1 * r2: " + r1.multiply(r2));

        System.out.println("n1 + r1: " + n1.add(r1));
        System.out.println("n1 - r1: " + n1.subtract(r1));
        System.out.println("n1 * r1: " + n1.multiply(r1));
        System.out.println("n1 / r1: " + n1.divide(r1));

        System.out.println("r1 + n1: " + r1.add(n1));
        System.out.println("r1 - n1: " + r1.subtract(n1));
        System.out.println("r1 * n1: " + r1.multiply(n1));
        System.out.println("r1 / n1: " + r1.divide(n1));

        System.out.println("8/4 == 2/1: " + new MyRational(8, 4).equals(new MyRational(2, 1)));
        System.out.println("8/4 == 2 " + new MyRational(8, 4).equals(new MyInteger(2)));
        System.out.println("2 == 2: " + new MyInteger(2).equals(new MyInteger(2)));
        System.out.println("MyInteger(2) == MyInteger(2): " + (new MyInteger(2) == new MyInteger(2)));

        System.out.println("n1 to rational number: " + n1.toRational());

        MyRational[] myArray = {n1, n2, r1, r2};
        for (MyRational r : myArray) {
            System.out.print(r + " is in myArray.");
            if (r instanceof MyInteger) {
                System.out.println("\tIt is an integer.");
            } else {
                System.out.println("\tIt is a rational number.");
            }
        }
    }
}

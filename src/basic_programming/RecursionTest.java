package basic_programming;

public class RecursionTest {

    public static void f(int x) {
        System.out.println("f called with x = " + x);
        if (x < 3) {
            f(x + 1);
            f(x + 1);
        }
    }

    public static void main(String[] args) {
        f(-100);
    }
    
}


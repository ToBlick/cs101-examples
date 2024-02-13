package methods;

public class PassingVariables {

    public static void addOne(int x) {
        x++; // increment x
    }

    public static void addOne(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i]++;
        }
    }

    public static void printarray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {
        int i = 1;
        addOne(i);
        System.out.println(i);  // prints 1

        int[] array = {1,2,3};
        printarray(array);   // prints 123
        addOne(array);
        printarray(array);  // prints 234
    }
}

package arrays;
import java.util.Arrays;

public class Arrayprint {
    public static void main(String[] args) {
        int[] myArr = {1, 2, 3};
        int[] myArr2 = {1, 2, 3};
        System.out.println(myArr);
        System.out.println(myArr2);
        System.out.println(Arrays.toString(myArr));
        System.out.println(Arrays.toString(myArr2));
        System.out.println(Arrays.equals(myArr, myArr2));
        System.out.println(myArr == myArr2);
    }
    
}

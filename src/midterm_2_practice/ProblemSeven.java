package midterm_2_practice;

public class ProblemSeven {

    public static void main(String[] args) {
        int[][] array = { {1,2}, {3,4}, {5,6} };
        System.out.println(array.length + ", " + array[0].length + "\n");

        for(int i = 0; i < array.length; i++) {
            System.out.print(array[i][0] + ", ");
        }
    }
}

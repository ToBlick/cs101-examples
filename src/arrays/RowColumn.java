package arrays;

import java.util.Locale;

public class RowColumn {

    static void printTime(String name, double time) {
        System.out.printf(Locale.US, "Time spent for " + name + ": \t %.2fms \n", time/1e6);
    }

    public static void main(String[] args) {
        

        int n = 10_000;
        int m = 10_000;
        int[][] myArray = new int[n][m];

        long startTime = System.nanoTime();
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                myArray[i][j] = 0;
                
            }
        }
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        printTime("i then j", totalTime);


        startTime = System.nanoTime();
        for (int j=0; j<m; j++) {
            for (int i=0; i<n; i++) {  
                myArray[i][j] = 0;
            }
        }
        endTime   = System.nanoTime();
        totalTime = endTime - startTime;
        printTime("j then i", totalTime);
    }
}
package midterm_2_practice.dijkstra_algorithm;

import java.util.ArrayList;
import java.util.Locale;


public class Test {

    public static void main(String[] args) {
        int n = 25;
        Distances da = new Distances(n);
        da.printDistances();
        System.out.println();
        long startTime = System.nanoTime();
        ArrayList<Integer> randomPath = da.randomPath(0, n-1);
        long endTime = System.nanoTime();
        System.out.print("Random path from city 0 to " + (n-1) + ": " + randomPath + " of length ");
        System.out.printf(Locale.US, "%.1f", da.lengthOfPath(randomPath));
        System.out.print(" took ");
        System.out.printf(Locale.US, "%.1f", (endTime - startTime) / 1e6);
        System.out.println("ms to find.");

        startTime = System.nanoTime();
        ArrayList<Integer> dijkstraPath = da.dijkstraPath(0, n-1);
        endTime = System.nanoTime();
        System.out.print("Smart path from city 0 to " + (n-1) + ": " + dijkstraPath + " of length ");
        System.out.printf(Locale.US, "%.1f", da.lengthOfPath(dijkstraPath));
        System.out.print(" took ");
        System.out.printf(Locale.US, "%.1f", (endTime - startTime) / 1e6);
        System.out.println("ms to find.");
    }
    
}

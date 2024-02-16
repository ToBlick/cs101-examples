package arrays;

import java.util.Arrays;
import java.util.Random;

public class Sorting {

    public static void main(String[] args) {
        int n = 100;
        int[] numbers = getRandomNumbers(n, 10*n);

        // The Java method
        int[] sortednumbers = Arrays.copyOf(numbers, n);
        long startTime = System.nanoTime();
        Arrays.sort(sortednumbers);
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("Time spent for optimized Quicksort (per element): " + totalTime/((double) n) + " ns");

        // Insertionsort
        startTime = System.nanoTime();
        int[] insertionsortednumbers = bubbleSort(numbers);
        endTime   = System.nanoTime();
        totalTime = endTime - startTime;
        boolean correct = Arrays.equals(sortednumbers, insertionsortednumbers);
        System.out.println("Insertionsort gives the " + (correct ? "correct" : "wrong") + " result.");
        System.out.println("Time spent for insertionsort (per element): " + totalTime/((double) n) + " ns");

        // Bubblesort
        startTime = System.nanoTime();
        int[] bubblesortednumbers = bubbleSort(numbers);
        endTime   = System.nanoTime();
        totalTime = endTime - startTime;
        correct = Arrays.equals(sortednumbers, bubblesortednumbers);
        System.out.println("Bubblesort gives the " + (correct ? "correct" : "wrong") + " result.");
        System.out.println("Time spent for bubblesort (per element): " + totalTime/((double) n) + " ns");
    }

    static int[] insertionSort(int[] array) {
        int n = array.length;
        int[] sortedarray = Arrays.copyOf(array, n);
        for (int j = 0; j < n; j++) {
            for (int i = j; i < n; i++) {
                if (sortedarray[i] < sortedarray[j]) {
                    int newmin = sortedarray[i];
                    sortedarray[i] = sortedarray[j];
                    sortedarray[j] = newmin;
                }
            }
        }
        return sortedarray;
    }

    static int[] bubbleSort(int[] array) {
        int n = array.length;
        int[] sortedarray = Arrays.copyOf(array, n);
        boolean swaps_done = true;
        while (swaps_done) {
            swaps_done = false;
            for (int i = 0; i < n-1; i++) {
                if (sortedarray[i] > sortedarray[i+1]) {
                    int temp = sortedarray[i];
                    sortedarray[i] = sortedarray[i+1];
                    sortedarray[i+1] = temp;
                    swaps_done = true;
                }
            }
        }
        return sortedarray;
    }

    static int[] getRandomNumbers(int n, int bound) {
        int[] numbers = new int[n];
        Random rng = new Random();
        for (int i = 0; i < n; i++) {
            numbers[i] = rng.nextInt(bound);
        }
        return numbers;
    }
    
}

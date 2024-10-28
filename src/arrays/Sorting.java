package arrays;

import java.util.Arrays;
import java.util.Random;
import java.util.Locale;

public class Sorting {

    public static void main(String[] args) {

        int a = 0/0;

        int N = (int) 1e4; // 10000
        int[] numbers = getRandomNumbers(N, N);

        // The Java method
        int[] sortednumbers = Arrays.copyOf(numbers, N);
        long startTime = System.nanoTime();
        Arrays.sort(sortednumbers);
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        printTime("Array's sort", totalTime, N);

        // Insertionsort
        int[] insertionsortednumbers = Arrays.copyOf(numbers, N);
        startTime = System.nanoTime();
        insertionsort(insertionsortednumbers);
        endTime   = System.nanoTime();
        totalTime = endTime - startTime;
        if (!Arrays.equals(sortednumbers, insertionsortednumbers)) { 
            System.out.println("Incorrect Insertionsort!");
        }
        printTime("Insertionsort", totalTime, N);

        // Bubblesort
        int[] bubblesortednumbers = Arrays.copyOf(numbers, N);
        startTime = System.nanoTime();
        bubblesort(bubblesortednumbers);
        endTime   = System.nanoTime();
        totalTime = endTime - startTime; 
        if (!Arrays.equals(sortednumbers, bubblesortednumbers)) {
            System.out.println("Incorrect Bubblesort!");
        }
        printTime("Bubblesort", totalTime, N);
        
        // Quicksort
        int[] quicksortednumbers = Arrays.copyOf(numbers, N);
        startTime = System.nanoTime();
        quicksort(quicksortednumbers);
        endTime   = System.nanoTime();
        totalTime = endTime - startTime;
        if (!Arrays.equals(sortednumbers, quicksortednumbers)) {
            System.out.println("Incorrect Quicksort!");
        }
        printTime("Quicksort", totalTime, N);
    }

    /**
     * In-place insertion sort https://en.wikipedia.org/wiki/Insertion_sort
     * @param array to be sorted
     */
    static void insertionsort(int[] array) {
        int n = array.length;
        for (int j = 0; j < n-1; j++) {
            for (int i = j+1; i < n; i++) {
                if (array[i] < array[j]) {
                    swap(i, j, array);
                }
            }
        }
    }

    /**
     * In-place bubblesort https://en.wikipedia.org/wiki/Bubble_sort
     * @param array to be sorted
     */
    static void bubblesort(int[] array) {
        int n = array.length;
        boolean swaps_done = true;
        while (swaps_done) {
            swaps_done = false;
            for (int i = 0; i < n-1; i++) {
                if (array[i] > array[i+1]) {
                    swap(i, i+1, array);
                    swaps_done = true;
                }
            }
        }
    }
    
    /**
     * In-place quicksort https://en.wikipedia.org/wiki/Quicksort
     * @param array to be sorted
     */
    static void quicksort(int[] array) {
        quicksort(array, 0, array.length-1);
    }
    /**
     * In-place quicksort https://en.wikipedia.org/wiki/Quicksort
     * @param array array to be sorted
     * @param start smallest considered index
     * @param end largest considered index
     */
    static void quicksort(int[] array, int start, int end) {
        if (start < end) {
           int partitionindex = partition(array, start, end);
           quicksort(array, start, partitionindex - 1);
           quicksort(array, partitionindex + 1, end);
        }
    }

    /**
     * determine the partition index for quicksort
     * @param array
     * @param start
     * @param end
     * @return partition index
     */
    static int partition(int array[], int start, int end) {
        int mid = (start+end)/2;
        if (array[mid] < array[start]) {
            swap(mid, start, array);
        }
        if (array[end] < array[start]) {
            swap(end, start, array);
        }
        if (array[mid] < array[end]) {
            swap(mid, end, array);
        }
        int pivot = array[end]; 
        int i = start-1;
        for (int j = start; j < end; j++) {
            if (array[j] < pivot) {
                i++;
                swap(i, j, array);
            }
        }
        swap(i+1, end, array);
        return i+1;
    }

    /**
     * Generate an array of n random integers between 0 and bound
     * @param n
     * @param bound
     * @return generated array
     */
    static int[] getRandomNumbers(int n, int bound) {
        int[] numbers = new int[n];
        Random rng = new Random();
        for (int i = 0; i < n; i++) {
            numbers[i] = rng.nextInt(bound);
        }
        return numbers;
    }

    /**
     * Swap the elements of array at index i and j
     * @param i
     * @param j
     * @param array
     */
    static void swap(int i, int j, int[] array) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    static void printTime(String name, double time, int n) {
        System.out.printf(Locale.US, "Time spent for " + name + ": \t %.2fms \t (%.2fμs per element) \n", time/1e6, time/(1e3 * n));
    }
    
}

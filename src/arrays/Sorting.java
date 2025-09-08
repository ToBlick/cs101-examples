package arrays;

import java.util.Arrays;
import java.util.Random;

import java.util.Locale;

public class Sorting {

    public static void main(String[] args) {

        int N = (int) 4e4; // 40000
        int[] numbers = getRandomNumbers(N, N);

        // Print the first 10 numbers
        System.out.print("First 10 numbers: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println();

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
        int[] mergesortednumbers = Arrays.copyOf(numbers, N);
        startTime = System.nanoTime();
        mergesort(mergesortednumbers);
        endTime   = System.nanoTime();
        totalTime = endTime - startTime;
        if (!Arrays.equals(sortednumbers, mergesortednumbers)) {
            System.out.println("Incorrect Mergesort!");
        }
        printTime("Mergesort", totalTime, N);
        
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

        // Print the first 10 numbers
        System.out.print("First 10 numbers: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(sortednumbers[i] + " ");
        }
        System.out.println();
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
     * In-place mergesort https://en.wikipedia.org/wiki/Merge_sort
     * @param array to be sorted
     */
    static void mergesort(int[] array) {
        int n = array.length;
        if (n < 2) {
            return;
        }
        int mid = n/2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, n);
        mergesort(left);
        mergesort(right);
        merge(left, right, array);
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
        // --- Part 1: Pivot Selection (Median-of-Three) ---
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
        // after this, array[start] <= array[end] <= array[mid]

        // --- Part 2: Partitioning ---
        int pivot = array[end];                 // Choose the median value (now at index 'end') as the pivot.
        int i = start-1;                        // Initialize index 'i' (pointer for the "less than pivot" boundary).
                                                // 'i' will track the index of the last element found to be smaller than the pivot
        for (int j = start; j < end; j++) {     // Iterate through the array segment from 'start' up to (but not including) 'end',
                                                // because the pivot is currently at 'end'.    
            if (array[j] < pivot) {             // If the current element is smaller than the pivot,
                i++;                            // ...move the boundary for smaller elements one step right.
                swap(i, j, array);              // Swap the current element with the element at index 'i'.
                                                // This moves the smaller element to the left side (index <= i) and moves a 
                                                // potentially larger element (originally at index i) to index j to be processed later.
            }
        }
        // --- Part 3: Place the Pivot in its Final Position ---
        swap(i+1, end, array);                  // After the loop, all elements from 'start' to 'i' are less than the pivot.
                                                // All elements from 'i+1' to 'end-1' are greater than or equal to the pivot.
                                                // The pivot itself is still at 'end'.
                                                // Swap the pivot (at array[end]) with the element at array[i+1] (which is the
                                                // first element >= pivot).
        return i+1;                             // The pivot is now correctly placed at index 'i+1'.
                                                // All elements to its left (indices < i+1) are smaller.
                                                // All elements to its right (indices > i+1) are greater or equal.
                                                // Return the index where the pivot ended up.
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

    /**
     * Merge two sorted arrays into one sorted array
     * @param left
     * @param right
     * @param array
     */
    static void merge(int[] left, int[] right, int[] array) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }
        while (i < left.length) {
            array[k++] = left[i++];
        }
        while (j < right.length) {
            array[k++] = right[j++];
        }
    }
    
}

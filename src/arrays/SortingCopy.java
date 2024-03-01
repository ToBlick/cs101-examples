package arrays;

import java.util.Arrays;
import java.util.Random;

public class SortingCopy {
    
        public static void main(String[] args) {
            int n = 10;
            // get array with n random numbers between 0 and n
            int[] numbers = getRandomNumbers(n, n);
            // make a copy of the array and sort it
            int[] sortedNumbers = Arrays.copyOf(numbers, n);
            Arrays.sort(sortedNumbers);
            // make another copy and sort it with my method
            int[] mySortedNumbers = Arrays.copyOf(numbers, n);
            mySort(mySortedNumbers);
            // check if my method is sorting correctly
            if (!Arrays.equals(sortedNumbers,mySortedNumbers)) {
            // if (!Arrays.equals(sortedNumbers,mySortedNumbers)) {
                System.out.println("mySort does not work");
            }
            System.out.println(Arrays.toString(mySortedNumbers));
            System.out.println(Arrays.toString(sortedNumbers));
        }

        static void mySort(int[] array) {
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

        static void swap(int i, int j, int[] array) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
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
package com.company;
import java.util.*;

public class QuickSort {
    public static void main(String[] args) {
        QuickSort s = new QuickSort();
        int[] array = new int[] {4, 2, -3, 6, 1};
        System.out.println(Arrays.toString(s.quickSort(array)));
        // [-3, 1, 2, 4, 6]

        array = new int[] {3, 2, 1};
        System.out.println(Arrays.toString(s.quickSort(array)));
        // [1, 2, 3]
    }

    // Corner Cases:
    // 1. What if the given array is null? In this case, we do not need to do anything.
    // 2. What if the given array is of length zero? In this case, we do not need to do anything.
    // Time O(nlogn)  worst case O(n^2)
    // Space O(logn) worst case O(n)
    public int[] quickSort(int[] array) {
        if (array == null || array.length == 0) {
            return array;
        }
        quickSort(array, 0, array.length - 1);
        return array;
    }

    private Random rand = new Random();

    private void quickSort(int[] array, int left, int right) {
        // base case;
        if (left >= right) {
            return;
        }
        // partition
        // q1: choose randomly a number in [left, right]
        // given we have random.nextInt(x) → random value in [0, x)
        int pivotIdx = left + rand.nextInt(right - left + 1);// pick pivot
        swap(array, pivotIdx, right); // swap to rightmost
        int i = left, j = right - 1;
        while (i <= j) {
            if (array[i] < array[right]) {
                // case 1:
                i++;
            } else {
                // case 2:
                swap(array, i, j);
                j--;
            }
        }
        swap(array, i, right);	// swap pivot back
    // recursion rule
        quickSort(array, left, i - 1);
        quickSort(array, i + 1, right);
    }

    private void swap(int[] array, int i, int j){
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}

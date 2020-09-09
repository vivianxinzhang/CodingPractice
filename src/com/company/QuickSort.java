package com.company;
import java.util.*;

public class QuickSort {
    public int[] quickSort(int[] array) {
        // Write your solution here
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

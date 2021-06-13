package com.company;

import java.util.Arrays;

public class SelectionSort {
    public static void main(String[] args) {
        SelectionSort s = new SelectionSort();

        int[] array = null;
        System.out.println(Arrays.toString(s.solve(array)));     // null

        array = new int[0];
        System.out.println(Arrays.toString(s.solve(array)));     // []

        array = new int[] { 4, 3, 2, 1};
        System.out.println(Arrays.toString(s.solve(array)));     // [1, 2, 3, 4]

        array = new int[] {3, 5, 1, 2, 4, 8};
        System.out.println(Arrays.toString(s.solve(array)));     // [1, 2, 3, 4, 5, 8]
    }

    // Corner Cases:
    // 1. What if the given array is null? In this case, we do not need to do anything.
    // 2. What if the given array is of length zero? In this case, we do not need to do anything.
    // Time O(n^2)
    // Space O(1)
    public int[] solve(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        for (int i = 0; i < array.length - 1; i++) {
            int midIdx = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[midIdx]) {
                    midIdx = j;
                }
            }
            swap(array, i, midIdx);
        }
        return array;
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}

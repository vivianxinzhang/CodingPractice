package com.company;

import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {
        InsertionSort s = new InsertionSort();
        int[] array = new int[] {1, 2, 3};
        s.sort(array);
        System.out.println(Arrays.toString(array));
        // [1, 2, 3]

        array = new int[] {4, 2, -3, 6, 1};
        s.sort(array);
        System.out.println(Arrays.toString(array));
        // [-3, 1, 2, 4, 6]
    }

    // Corner Cases:
    // 1. What if the given array is null? In this case, we do not need to do anything.
    // 2. What if the given array is of length zero? In this case, we do not need to do anything.
    // Time O(n^2)
    // Space O(1)
    public int[] sort(int[] array) {
        if (array == null || array.length == 0) {
            return array;
        }
        for (int i = 1; i < array.length; i++) {
            int tmp = array[i];
            while (i >= 1 && array[i - 1] > array[i]) {
                swap(array, i - 1, i);
                i--;
            }
        }
        return array;
    }

    public int[] sortI(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        int index = 1;
        while (index < array.length) {
            if (array[index] <= array[index - 1]) {
                int position = index;
                while (position > 0 && array[position] < array[position - 1]) {
                    swap(array, position, position - 1);
                    position--;
                }
            }
            index++;
        }
        return array;
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}

package com.company;

import java.util.Arrays;

public class SortInPair {
    public static void main(String[] args) {
        SortInPair s = new SortInPair();
        System.out.println();
    }

    // Assumptions:
    // 1. A is guaranteed to be not null.
    // 2. There are no duplicate elements in A.
    // Time O(nlogn)
    // Space O(logn)
    public void sortInPair(int[] array) {
        if (array == null || array.length <= 2) {
            return;
        }
        Arrays.sort(array);
        int i = 2;
        while (i < array.length) {
            swap(array, i - 1, i);
            i += 2;
        }
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}

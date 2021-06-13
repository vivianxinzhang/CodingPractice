package com.company;

import java.util.Arrays;

public class NumberOfValidTriangles {
    public static void main(String[] args) {
        NumberOfValidTriangles s = new NumberOfValidTriangles();
        int[] array = new int[] {4, 6, 3, 7};
        System.out.println(s.numOfTriangles(array));    // 3
    }

    // Assumptions:
    // The given array is not null and has length of at least 3.
    // Time O(n^2)
    // Space O(1)
    public int numOfTriangles(int[] array) {
        if (array == null || array.length < 3) {
            return 0;
        }
        Arrays.sort(array);
        int count = 0;
        // i, j, k
        for (int k = 2; k < array.length; k++) {
            int i = 0;
            int j = k - 1;
            while (i < j) {
                if (array[i] + array[j] > array[k]) {
                    count += (j - i);
                    j--;
                } else {
                    i++;
                }
            }
        }
        return count;
    }

    // Method 1: brute force
    // Time O(n^3)
    // Space O(1)
    public int numOfTrianglesI(int[] array) {
        if (array == null || array.length < 3) {
            return 0;
        }
        Arrays.sort(array);
        int count = 0;
        for (int i = 0; i < array.length - 2; i++) {
            for (int j = i + 1; j < array.length - 1; j++) {
                for (int k = j + 1; k < array.length; k++) {
                    if (array[i] + array[j] > array[k] && array[i] + array[k] > array[j] && array[j] + array[k] > array[i]) {
                        // 两边之和大于第三边
                        count++;
                    }
                }
            }
        }
        return count;
    }
}

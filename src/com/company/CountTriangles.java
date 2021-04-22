package com.company;

import java.util.Arrays;

public class CountTriangles {
    public static void main(String[] args) {
        CountTriangles s = new CountTriangles();
        int[] array = new int[] {3, 4, 5, 5};
        System.out.println(s.countTriangles(array));   // 4
        System.out.println(s.countTrianglesI(array));   // 4
    }

    // Assumptions:
    // 1. The given array is unsorted.
    // 2. The given array only contains positive numbers.
    // Method 2: greedy
    // a + b > c
    // a <= b <= c
    // Time O(n^2)
    // Space O(logn)
    public int countTriangles(int[] array) {
        if (array == null || array.length <= 2) {
            return 0;
        }
        Arrays.sort(array);
        int res = 0;
        for (int k = 2; k < array.length; k++) {
            int i = 0;
            int j = k - 1;
            while (i < j) {
                if (array[i] + array[j] > array[k]) {
                    // i, j, k is valid
                    // [i,j-1], j, k are all valid
                    res += j - i;
                    j--;
                } else {
                    i++;
                }
            }
        }
        return res;
    }

    // Method 1: brute force
    // a + b > c
    // a <= b <= c
    // Time O(n^3)
    // Space O(1)
    public int countTrianglesI(int[] array) {
        if (array == null || array.length <= 2) {
            return 0;
        }
        int res = 0;
        for (int k = 0; k < array.length; k++) {
            for (int j = 0; j < k; j++) {
                for (int i = 0; i < j; i++) {
                    if (array[i] + array[j] > array[k]) {
                        res++;
                    }
                }
            }
        }
        return res;
    }
}

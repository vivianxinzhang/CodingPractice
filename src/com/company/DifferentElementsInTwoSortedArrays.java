package com.company;

import java.util.Arrays;

public class DifferentElementsInTwoSortedArrays {
    public static void main(String[] args) {
        DifferentElementsInTwoSortedArrays s = new DifferentElementsInTwoSortedArrays();

        int[] a = new int[] {1, 2, 2, 3, 4, 5};
        int[] b = new int[] {2, 2, 2, 4, 4, 6};
        Printer.printMatrix(s.diff(a, b));
        // [1, 3, 5]
        // [2, 4, 6]
    }

    // Assumptions:
    // The two given arrays are not null.
    // Time O(m + n)
    // Space O(1)
    public int[][] diff(int[] a, int[] b) {
        int slowA = 0;
        int fastA = 0;
        int slowB = 0;
        int fastB = 0;
        while (fastA < a.length && fastB < b.length) {
            if (a[fastA] == b[fastB]) {
                fastA++;
                fastB++;
            } else if (a[fastA] < b[fastB]) {
                a[slowA++] = a[fastA++];
            } else {
                b[slowB++] = b[fastB++];
            }
        }
        while (fastA < a.length) {
            a[slowA++] = a[fastA++];
        }
        while (fastB < b.length) {
            b[slowB++] = b[fastB++];
        }
        int[][] res = new int[2][];
        res[0] = Arrays.copyOf(a, slowA);
        res[1] = Arrays.copyOf(b, slowB);
        return res;
    }
}

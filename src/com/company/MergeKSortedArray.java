package com.company;

import java.util.Arrays;

public class MergeKSortedArray {
//    public int[] merge(int[][] matrix) {
//        Method 0:
//        Time O(mn * log(mn))
//        Space O(kn)
//        if (matrix == null || matrix.length == 0) {
//            return new int[0];
//        }
//        int length = 0;
//        for (int[] array : matrix) {
//            length += array.length;
//        }
//        int[] result = new int[length];
//        int k = 0;
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[i].length; j++) {
//                result[k] = matrix[i][j];
//                k++;
//            }
//        }
//        Arrays.sort(result);
//        return result;
//    }

//     Method 1:
//     Time O(k^2 * n)
//     Space O(kn)
//    public int[] merge(int[][] matrix) {
//        // Write your solution here
//        if (matrix == null || matrix.length == 0) {
//            return new int[0];
//        }
//        int[] tmp = matrix[0];
//        for (int i = 1; i < matrix.length; i++) {
//            tmp = merge(tmp, matrix[i]);
//        }
//        return tmp;
//    }

//    Method 2:
//    Time O(kn * logk)
//    Space O(kn)
    public int[] merge(int[][] matrix) {
        // Write your solution here
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }
        int[] tmp = matrix[0];
        return merge(matrix, 0, matrix.length - 1);
    }

    private int[] merge(int[][] matrix, int i, int j) {
        if (i == j) {
            return matrix[i];
        }
        if (i == j - 1) {
            return merge(matrix[i], matrix[j]);
        }
        int mid = i + (j - i) / 2;
        int[] left = merge(matrix, i, mid);
        int[] right = merge(matrix, mid + 1, j);
        return merge(left, right);
    }

    private int[] merge(int[] A, int[] B) {
        int m = A.length, n = B.length;
        int[] result = new int[m + n];
        int i = 0, j = 0, k = 0;
        while (i < m && j < n) {
            if (A[i] < B[j]) {
                result[k] = A[i];
                i++;
            } else {
                result[k] = B[j];
                j++;
            }
            k++;
        }
        while (i < m) {
            result[k] = A[i];
            i++;
            k++;
        }
        while (j < n) {
            result[k] = B[j];
            j++;
            k++;
        }
        return result;
    }
}



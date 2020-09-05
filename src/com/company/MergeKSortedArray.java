package com.company;
import java.util.Arrays;

public class MergeKSortedArray {
    public int[] merge(int[][] matrix) {
        // Write your solution here
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }
        int length = 0;
        for (int[] array : matrix) {
            length += array.length;
        }
        int[] result = new int[length];
        int k = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result[k] = matrix[i][j];
                k++;
            }
        }
        Arrays.sort(result);
        return result;
    }

    // Method 1:
    // Time O(k^2 * n)
    // Space O(kn)
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


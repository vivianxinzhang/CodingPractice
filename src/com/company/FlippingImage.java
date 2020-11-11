package com.company;

public class FlippingImage {
    // Time O(n^2)
    // Space O(1)
    public int[][] flipAndInvertImage(int[][] A) {
        if (A == null || A.length == 0 || A[0].length == 0) {
            return A;
        }
        for (int[] row : A) {
            flip(row);
            invert(row);
        }
        return A;
    }

    private void flip(int[] array) {
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            swap(array, left, right);
            left++;
            right--;
        }
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private void invert(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] ^= 1;
        }
    }
}

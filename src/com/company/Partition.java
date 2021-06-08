package com.company;

import java.util.Arrays;

public class Partition {
    public static void main(String[] args) {
        Partition s = new Partition();
        int[] array = new int[] {9, 4, 6, 2, 1, 0, 7};
        s.partition(array, 6);
        System.out.println(Arrays.toString(array));

    }

    // Assumption:
    // The array is not null or empty. pivotIndex is within the boundary of the array.
    // Time O(n)
    // Space O(1)
    public void partition(int[] array, int pivotIndex) {
        int n = array.length;
        swap(array, pivotIndex, array.length - 1);
        int pivot = array[n - 1];
        int i = 0;
        int j = n - 2;
        while (i <= j) {
            if (array[i] < pivot) {
                i++;
            } else {
                swap(array, i, j);
                j--;
            }
        }
        swap(array, i, n - 1);
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}

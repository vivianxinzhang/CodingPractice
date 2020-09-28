package com.company;
import java.util.*;

// Time O(n) better answer O(h)
// Space O(h) worst case O(n)
public class Solution {
    public int[] quickSort(int[] array) {
        // Write your solution here
        if (array == null || array.length == 0) {
            return array;
        }
        quickSort(array, 0, array.length - 1);
        return array;
    }

    private void quickSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivotIdx = partition(array, left, right);
        quickSort(array, left, pivotIdx - 1);
        quickSort(array, pivotIdx + 1, right);
    }

    private int partition(int[] array, int left, int right) {
        int pivotIdx = (int)(Math.random() * (right - left + 1)) + left;
        swap(array, pivotIdx, right);
        int i = left, j = right - 1;
        while (left <= j) {
            if (array[i] < array[right]) {
                i++;
            } else {
                swap(array, i, j);
                j--
            }
        }
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array
    }
}

package com.company;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] array = {3, 2, 1, 5, 6, 4};
        System.out.println(s.findKthLargest(array, 2));
    }

    // Time O(n) worst case O(n^2)
    // Space O(1) worst case O(n)
    public int findKthLargest(int[] array, int k) {
        // Write your solution here
        if (array == null || array.length == 0 || k == 0) {
            return -1;
        }
        quickSelect(array, 0, array.length - 1, array.length - k);
        return array[array.length - k];
    }

    private void quickSelect(int[] array, int left, int right, int target) {
        int pivotIdx = partition(array, left, right);
        if (target == pivotIdx) {
            return;
        } else if (target < pivotIdx) {
            quickSelect(array, left, pivotIdx - 1, target);
        } else {
            quickSelect(array, pivotIdx + 1, right, target);
        }
    }

    private int partition(int[] array, int left, int right) {
        int pivot = array[right];
        int i = left;
        int j = right - 1;
        while (i <= j) {
            if (array[i] < pivot) {
                i++;
            } else {
                swap(array, i, j);
                j--;
            }
        }
        swap(array, i, right);
        return i;
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}

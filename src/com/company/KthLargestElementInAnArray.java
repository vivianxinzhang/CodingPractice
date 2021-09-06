package com.company;

import java.util.Arrays;

public class KthLargestElementInAnArray {
    public static void main(String[] args) {
        KthLargestElementInAnArray s = new KthLargestElementInAnArray();

        int[] array = new int[] {3, 2, 1, 5, 6, 4};
        System.out.println(s.findKthLargest(array, 2));     // 5

        array = new int[] {3, 2, 1, 5, 6, 4};
        System.out.println(s.findKthLargest(array, 5));     // 2
    }

    // Note:
    // You may assume k is always valid, 1 ≤ k ≤ array's length.
    // Time O(n) worst case O(n^2)
    // Space O(1) worst case O(n)
    public int findKthLargest(int[] array, int k) {
        Arrays.sort(array);
        return kthLargestHelper(array, 0, array.length - 1, array.length - k);
    }

    private int kthLargestHelper(int[] array, int left, int right, int targetIdx) {
        int pivotIdx = partition(array, left, right);
        if (pivotIdx == targetIdx) {
            return array[pivotIdx];
        } else if (targetIdx < pivotIdx) {
            return kthLargestHelper(array, left, pivotIdx - 1, targetIdx);
        } else {
            return kthLargestHelper(array, pivotIdx + 1, right, targetIdx);
        }
    }

    private int partition(int[] array, int left, int right) {
        int pivotValue = array[right];
        int i = left;
        int j = right - 1;
        while (i <= j) {
            if (array[i] <= pivotValue) {
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

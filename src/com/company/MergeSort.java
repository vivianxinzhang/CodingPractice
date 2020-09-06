package com.company;

public class MergeSort {
    // Method 1
    // Time O(nlogn)
    // Space O(n)
    public int[] mergeSort1(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        return mergeSort(array, 0, array.length - 1);
    }

    private int[] mergeSort(int[] array, int left, int right) {
        // base case
        if (left == right) {
            return new int[] {array[left]};
        }
        // recursion rule
        int mid = left + (right - left) / 2;
        int[] leftRes = mergeSort(array, left, mid);
        int[] rightRes = mergeSort(array, mid + 1, right);
        return merge(leftRes, rightRes);
    }

    private int[] merge(int[] leftR, int[] rightR) {
        int[] result = new int[leftR.length + rightR.length];
        int i = 0, j = 0, k = 0;
        while (i < leftR.length && j < rightR.length) {
            if (leftR[i] < rightR[j]) {
                result[k] = leftR[i];
                i++;
            } else {
                result[k] = rightR[j];
                j++;
            }
            k++;
        }
        while (i < leftR.length) {
            result[k] = leftR[i];
            i++;
            k++;
        }
        while (j < rightR.length) {
            result[k] = rightR[j];
            j++;
            k++;
        }
        return result;
    }
}

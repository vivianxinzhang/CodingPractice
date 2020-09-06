package com.company;

public class MergeSort {
    // Method 2
    // Time O(nlogn)
    // Space O(n)
    public int[] mergeSort2(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        int[] helper = new int[array.length];
        mergeSort2(array, 0, array.length - 1, helper);
        return array;
    }

    private void mergeSort2(int[] array, int left, int right, int[] helper) {
        // base case
        if (left == right) {
            return;
        }
        // recursion rule
        int mid = left + (right - left) / 2;
        mergeSort2(array, left, mid, helper);
        mergeSort2(array, mid + 1, right, helper);
        merge2(array, left, mid, right, helper);
    }

    private void merge2(int[] array, int left, int mid, int right, int[] helper) {
        for (int i = left; i <= right; i++) {
            helper[i] = array[i];
        }
        int leftIdx = left, rightIdx = mid + 1, idx = left;
        while (leftIdx <= mid && rightIdx <= right) {
            if (helper[leftIdx] < helper[rightIdx]) {
                array[idx] = helper[leftIdx];
                leftIdx++;
            } else {
                array[idx] = helper[rightIdx];
                rightIdx++;
            }
            idx++;
        }
        while (leftIdx <= mid) {
            array[idx] = helper[leftIdx];
            leftIdx++;
            idx++;
        }
    }

    // Method 1
    // Time O(nlogn)
    // Space O(n)
    public int[] mergeSort1(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        return mergeSort1(array, 0, array.length - 1);
    }

    private int[] mergeSort1(int[] array, int left, int right) {
        // base case
        if (left == right) {
            return new int[] {array[left]};
        }
        // recursion rule
        int mid = left + (right - left) / 2;
        int[] leftRes = mergeSort1(array, left, mid);
        int[] rightRes = mergeSort1(array, mid + 1, right);
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

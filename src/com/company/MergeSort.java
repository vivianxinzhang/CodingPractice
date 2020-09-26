package com.company;

import java.util.Arrays;

public class MergeSort {
    // Method 2
    // Time O(nlogn)
    // Space O(n)
    public int[] mergeSort2(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        // allocate helper array to help merge step, so that we guarantee no more than O(n) space is used
        // The space complexity is O(n) in this case
        int[] helper = new int[array.length];
        mergeSort2(array, 0, array.length - 1, helper);
        return array;
    }

    private void mergeSort2(int[] array, int left, int right, int[] helper) {
        // base case
        // left >= right is also ok, left > right is a corner case
        // but only use left > right as base case is wrong, will stack overflow
        // (when there is only one element, left = right = mid, the subproblem will not become smaller)
        // left == right must be a base case
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
        // copy the content to helper array and we will merge from the helper array
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
        // if there are some elements left on the left side, need to copy them
        while (leftIdx <= mid) {
            array[idx] = helper[leftIdx];
            leftIdx++;
            idx++;
        }
        // if there are some elements at right side, we do not need to copy them,
        // because they are already in their position
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

    public static void main(String[] args) {
        MergeSort s = new MergeSort();
        // test cases to cover possible situations
        int[] array = null;
        array = s.mergeSort2(array);
        System.out.println(Arrays.toString(array));

        array = new int[0];
        array = s.mergeSort2(array);
        System.out.println(Arrays.toString(array));

        array = new int[] { 1, 2, 3, 4};
        array = s.mergeSort2(array);
        System.out.println(Arrays.toString(array));

        array = new int[] { 4, 3, 2, 1};
        array = s.mergeSort2(array);
        System.out.println(Arrays.toString(array));

        array = new int[] { 2, 4, 1, 5, 3 };
        array = s.mergeSort2(array);
        System.out.println(Arrays.toString(array));
    }
}

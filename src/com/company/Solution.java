package com.company;
import java.util.*;


// Time O(nlogn)
// Space O(n)
public class Solution {
    public int[] mergeSort(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        int[] helper = new int[array.length];
        mergeSort(array, 0, array.length - 1, helper);
        return array;
    }

    private void mergeSort(int[] array, int left, int right, int[] helper) {
        if (left == right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(array, left, mid, helper);
        mergeSort(array, mid + 1, right, helper);
        merge(array, left, mid, right, helper);
    }

    private void merge(int[] array, int left, int mid, int right, int[] helper) {
        for (int i = left; i <= right; i++) {
            helper[i] = array[i];
        }
        int leftIdx = left, rightIdx = mid + 1, j = left;
        while (leftIdx <= mid && rightIdx <= right) {
            if (helper[leftIdx] < helper[rightIdx]) {
                array[j] = helper[leftIdx];
                leftIdx++;
            } else {
                array[j] = helper[rightIdx];
                rightIdx++;
            }
            j++;
        }
        while (leftIdx <= mid) {
            array[j] = helper[leftIdx];
            leftIdx++;
            j++;
        }
    }
}
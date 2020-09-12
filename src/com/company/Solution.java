package com.company;

import java.util.*;


// Time O(nlogn)
// Space O(n)
public class Solution {
    public int[] countArray(int[] array) {
        if (array == null || array.length == 0) {
            return new int[0];
        }
        int[] indexArray = initialIndexArray(array);
        int[] countArray = new int[array.length];
        int[] helper = new int[array.length];
        mergeSort(array, indexArray, helper, countArray, 0, array.length - 1);
        return countArray;
    }

    private int[] initialIndexArray(int[] array) {
        int[] indices = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            indices[i] = i;
        }
        return indices;
    }

    private void mergeSort(int[] array, int[] indexArray, int[] helper, int[] countArray, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(array, indexArray, helper, countArray, left, mid);
        mergeSort(array, indexArray, helper, countArray, mid + 1, right);
        merge(array, indexArray, helper, countArray, left, mid, right);
    }

    private void merge(int[] array, int[] indexArray, int[] helper, int[] countArray, int left, int mid, int right) {
        copyIndex(indexArray, helper, left, right);
        int l = left, r = mid + 1, cur = left;
        while (l <= mid && r <= right) {
            // here must use <= to keep their original order for same element
            // no need to move element with same value to its left
            if (array[helper[l]] <= array[helper[r]]) {
                indexArray[cur] = helper[l];
                countArray[indexArray[cur]] += r - (mid + 1);
                l++;
            } else {
                indexArray[cur] = helper[r];
                r++;
            }
            cur++;
        }
        while (l <= mid) {
            indexArray[cur] = helper[l];
            countArray[indexArray[cur]] += r - (mid + 1);
            l++;
            cur++;
        }
        // combine above two while loop: another implementation
//        while (l <= mid) {
//            // when sorting the indexArray, we use the corresponding value in the original array
//            if (r > right || array[helper[l]] <= array[helper[r]]) {
//                countArray[helper[l]] += (r - mid - 1);
//                indexArray[cur++] = helper[l++];
//            } else {
//                indexArray[cur++] = helper[r++];
//            }
//        }
    }

    private void copyIndex(int[] indexArray, int[] helper, int left, int right) {
        for (int i = left; i <= right; i++) {
            helper[i] = indexArray[i];
        }
    }
}
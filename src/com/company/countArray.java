package com.company;

import java.util.Arrays;

public class countArray {
    public int[] countArray(int[] array) {
        if (array == null || array.length == 0) {
            return new int[0];
        }
        // The indexArray contains the indices in the original array
        // and it will be sorted by the corresponding number in the original array
        // The countArray is the actual return array
        // The helper array is to helper the merge sort
        int[] indexArray = initialIndexArray(array);
        int[] countArray = new int[array.length];
        int[] helper = new int[array.length];
        mergeSort(array, indexArray, countArray, helper, 0, array.length - 1);
        return countArray;
    }

    private int[] initialIndexArray(int[] array) {
        int[] indices = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            indices[i] = i;
        }
        return indices;
    }

    private void mergeSort(int[] array, int[] indexArray, int[] countArray, int[] helper, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(array, indexArray, countArray, helper, left, mid);
        mergeSort(array, indexArray, countArray, helper, mid + 1, right);
        merge(array, indexArray, countArray, helper, left, mid, right);
    }

    private void merge(int[] array, int[] indexArray, int[] countArray, int[] helper,
                       int left, int mid, int right) {
        copyIndex(indexArray, helper, left, right);
        int l = left;
        int r = mid + 1;
        int cur = left;
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

//        combine above two while loop: another implementation
//        while (l <= mid) {
//            when sorting the indexArray, we use the corresponding value in the original array
//            here must use <= to keep their original order for same element
//            no need to move element with same value to its left
//            if (r > right || array[helper[l]] <= array[helper[r]]) {
//                countArray[helper[l]] += (r - mid - 1);
//                indexArray[cur++] = helper[l++];
//            } else {
//                indexArray[cur++] = helper[r++];
//            }
//        }

        while (l <= mid) {
            if (r > right || array[helper[l]] <= array[helper[r]]) {
                countArray[helper[l]] += (r - mid - 1);
                indexArray[cur++] = helper[l++];
            } else {
                indexArray[cur++] = helper[r++];
            }
        }
    }

    private void copyIndex(int[] indexArray, int[] helper, int left, int right) {
        for (int i = left; i <= right; i++) {
            helper[i] = indexArray[i];
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] array = null;
        System.out.println(Arrays.toString(s.countArray(array)));

        array = new int[] {1};
        System.out.println(Arrays.toString(s.countArray(array)));

        array = new int[] {1, 2, 3};
        System.out.println(Arrays.toString(s.countArray(array)));

        array = new int[] {3, 2, 1};
        System.out.println(Arrays.toString(s.countArray(array)));

        array = new int[] {3, 3, 3};
        System.out.println(Arrays.toString(s.countArray(array)));

        array = new int[] {4, 1, 6, 6, 2};
        System.out.println(Arrays.toString(s.countArray(array)));
    }
}

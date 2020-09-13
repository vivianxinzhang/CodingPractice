package com.company;
import java.util.Arrays;

public class ReturnToNest {
    // Time O(nlogn)
    // Space O(n)
    public int returnToNest(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        // The helper array is to helper the merge sort
        int[] helper = new int[array.length];
        int[] result = new int[1];
        mergeSort(array, helper, 0, array.length - 1, result);
        return result[0];
    }

    private void mergeSort(int[] array, int[] helper, int left, int right, int[] result) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(array, helper, left, mid, result);
        mergeSort(array, helper, mid + 1, right, result);
        merge(array, helper, left, mid, right, result);
    }

    private void merge(int[] array, int[] helper, int left, int mid, int right, int[] result) {
        for (int i = left; i <= right; i++) {
            helper[i] = array[i];
        }
        int l = left;
        int r = mid + 1;
        int cur = left;
        while (l <= mid && r <= right) {
            // here must use <= to keep their original order for same element
            // no need to move element with same value to its left
            if (helper[l] < helper[r]) {
                array[cur] = helper[l];
                result[0] += r - (mid + 1);
                l++;
            } else {
                array[cur] = helper[r];
                r++;
            }
            cur++;
        }
        while (l <= mid) {
            array[cur] = helper[l];
            result[0] += r - (mid + 1);
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
    }

    public static void main(String[] args) {
        ReturnToNest s = new ReturnToNest();
        int[] array = null;
        System.out.println(s.returnToNest(array));

        array = new int[]{1};
        System.out.println(s.returnToNest(array));

        array = new int[]{1, 2, 3};
        System.out.println(s.returnToNest(array));

        array = new int[]{3, 2, 1};
        System.out.println(s.returnToNest(array));
    }
}

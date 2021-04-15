package com.company;

import java.util.Arrays;

public class KClosestInSortedArray {
    public static void main(String[] args) {
        KClosestInSortedArray s = new KClosestInSortedArray();
        int[] array = new int[] {1, 2, 3};
        System.out.println(Arrays.toString(s.kClosest(array, 2, 3)));
        // [2, 1, 3]

        array = new int[] {1, 4, 6, 8};
        System.out.println(Arrays.toString(s.kClosest(array, 5, 3)));
        // [4, 6, 8]
    }

    // Assumptions:
    // 1. k >= 0 && k <= array.length
    // 2. if there is a tie, the element with smaller idx is preferred
    // Time O(logn + k)
    // Space O(1)
    public int[] kClosest(int[] array, int target, int k) {
        int left = largestSmallerOrEqual(array, target);
        int right = left + 1;
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            if (right >= array.length || (left >= 0 && (Math.abs(array[left] - target)) <= Math.abs(array[right] - target))) {
                res[i] = array[left];
                left--;
            } else {
                res[i] = array[right];
                right++;
            }
        }
        return res;
    }

    private int largestSmallerOrEqual(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid] <= target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (array[right] <= target) {
            return right;
        }
        if (array[left] <= target) {
            return left;
        }
        return -1;
    }
}

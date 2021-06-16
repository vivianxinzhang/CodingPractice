package com.company;

import java.util.Arrays;

public class SearchForARange {
    public static void main(String[] args) {
        SearchForARange s = new SearchForARange();
        int[] array = new int[] {1, 3, 3, 3, 5, 5, 7};
        System.out.println(Arrays.toString(s.range(array, 3)));     // [1, 3]
    }

    // Time O(logn)
    // Space O(1)
    public int[] range(int[] array, int target) {
        if (array == null || array.length == 0) {
            return new int[] {-1, -1};
        }
        int firstIdx = firstOccur(array, target);
        int lastIdx = lastOccur(array, target);
        return new int[] {firstIdx, lastIdx};
    }

    private int firstOccur(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (array[left] == target) {
            return left;
        }
        if (array[right] == target) {
            return right;
        }
        return -1;
    }

    private int lastOccur(int[] array, int target) {
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
        if (array[right] == target) {
            return right;
        }
        if (array[left] == target) {
            return left;
        }
        return -1;
    }
}

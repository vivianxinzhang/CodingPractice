package com.company;

public class MissingNumberII {
    public static void main(String[] args) {
        MissingNumberII s = new MissingNumberII();
        int[] array = new int[]{1, 2, 3};
        System.out.println(s.missing(array));   // 4

        array = new int[]{1, 2, 4};
        System.out.println(s.missing(array));   // 3

        array = new int[0];
        System.out.println(s.missing(array));   // 1
    }

    // Assumptions:
    // The given array is not null, and N >= 1
    // find last occur index i of array[i] - i == 1
    // Time O(logn)
    // Space O(1)
    public int missing(int[] array) {
        int lastOccurIdx = binarySearch(array);
        return lastOccurIdx + 2;
    }

    private int binarySearch(int[] array) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid] - mid == 1) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (array[right] - right == 1) {
            return right;
        }
        if (array[left] - left == 1) {
            return left;
        }
        return -1;
    }
}

package com.company;

public class ShiftPosition {
    public static void main(String[] args) {
        ShiftPosition s = new ShiftPosition();
        int[] array = new int[] {3, 4, 5, 1, 2};
        System.out.println(s.shiftPosition(array)); // 3

        array = new int[] {1, 2, 3, 4, 5};
        System.out.println(s.shiftPosition(array)); // 0
    }

    // Assumptions:
    // There are no duplicate elements in the array
    // Time O(logn)
    // Space O(1)
    public int shiftPosition(int[] array) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid] > array[right]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return array[left] < array[right] ? left : right;
    }
}

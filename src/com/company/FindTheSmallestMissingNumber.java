package com.company;

public class FindTheSmallestMissingNumber {
    public static void main(String[] args) {
        FindTheSmallestMissingNumber s = new FindTheSmallestMissingNumber();
        int[] array = new int[] {0, 1, 2, 6, 9};
        System.out.println(s.findFirstMissingElement(array));   // 3

        array = new int[] {4, 5, 10, 11};
        System.out.println(s.findFirstMissingElement(array));   // 0
    }

    // Write a binary search solution to find the smallest number that is missing in the array,
    // i.e. 2 in the above example. Return 0 if the array is null or empty.
    // Time O(logn)
    // Space O(1)
    public int findFirstMissingElement(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int lastOccurIdx = lastOccur(array);
        return lastOccurIdx + 1;
    }

    // find last occur of array[i] - index == 0
    private int lastOccur(int[] array) {
        int left = 0;
        int right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid] - mid == 0) {
                left = mid;
            } else if (array[mid] - mid > 0) {
                right = mid;
            }
        }
        if (array[right] - right == 0) {
            return right;
        }
        if (array[left] - left == 0) {
            return left;
        }
        return -1;
    }
}

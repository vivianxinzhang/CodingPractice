package com.company;

public class SearchInsertPosition {
    public static void main(String[] args) {
        SearchInsertPosition s = new SearchInsertPosition();
        int[] array = new int[] {2, 2, 2, 4, 7, 14};
        System.out.println(s.searchInsert(array, 2));
    }

    // Time O(logn)
    // Space O(1)
    // find largest smaller
    public int searchInsert(int[] input, int target) {
        // Write your solution here
        if (input == null || input.length == 0) {
            return 0;
        }
        int left = 0;
        int right = input.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (input[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (input[right] < target) {
            return right;
        }
        if (input[left] < target) {
            return left;
        }
        return 0;
    }
}

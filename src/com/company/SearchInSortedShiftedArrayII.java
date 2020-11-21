package com.company;

public class SearchInSortedShiftedArrayII {
    public int search(int[] input, int target) {
        if (input == null || input.length == 0) {
            return -1;
        }
        int left = 0, right = input.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (input[mid] == target) return mid;
            if (input[left] == target) return left;
            if (input[right] == target) return right;
            //
            if (input[left] < input[mid] && within(input, target, left, mid)
                    || input[mid] < input[right] && !within(input, target, mid, right)) {
                // [left, mid] is increasing and target is in [left, mid]
                // [mid, right] is increasing and target is in [mid, right]
                // 一定在左边
                right = mid - 1;
            } else if (input[left] < input[mid] && !within(input, target, left, mid)
                    || input[mid] < input[right] && within(input, target, mid, right)){
                // 一定在右边
                left = mid + 1;
            } else {
                left = left + 1;
                right = right - 1;
            }
        }
        if (input[left] == target) return left;
        if (input[right] == target) return right;
        return -1;
    }

    private boolean within(int[] array, int target, int left, int right) {
        return target >= array[left] && target <= array[right];
    }

    // Time O(logn)
    // Space O(1)
    public boolean searchI(int[] input, int target) {
        if (input == null || input.length == 0) {
            return false;
        }
        int left = 0, right = input.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (input[mid] == target || input[left] == target || input[right] == target) {
                return true;
            }
            if (input[left] < input[mid] && within(input, target, left, mid)
                    || input[mid] < input[right] && !within(input, target, mid, right)) {
                // [left, mid] is increasing and target is in [left, mid]
                // [mid, right] is increasing and target is in [mid, right]
                // 一定在左边
                right = mid - 1;
            } else if (input[left] < input[mid] && !within(input, target, left, mid)
                    || input[mid] < input[right] && within(input, target, mid, right)){
                // 一定在右边
                left = mid + 1;
            } else {
                left = left + 1;
                right = right - 1;
            }
        }
        return input[left] == target || input[right] == target;
    }
}

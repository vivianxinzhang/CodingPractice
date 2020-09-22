package com.company;

public class KClosest {
    public int[] kClosest(int[] array, int target, int k) {
        // Write your solution here
        if (array == null || array.length == 0 || k <= 0) {
            return new int[0];
        }
        int left = largestSmallerOrEqual(array, target);
        int right = left + 1;
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            if (right >= array.length || (left >= 0 && Math.abs(array[left] - target) <= Math.abs(array[right] - target))) {
                result[i] = array[left];
                left--;
            } else {
                result[i] = array[right];
                right++;
            }
        }
        return result;
    }

    private int largestSmallerOrEqual(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0, right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] > target) {
                right = mid;
            } else {
                left = mid;
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

    // Time O(logn + k)
    // Space O(1)
    public int[] kClosestI(int[] array, int target, int k) {
        // Write your solution here
        int[] result = new int[k];
        if (array == null || array.length == 0) {
            return result;
        }
        if (k > array.length) {
            return array;
        }
        int left = 0, right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid] <= target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        for (int i = 0; i < k; i++) {
            if (left < 0 || right < array.length && Math.abs(array[right] - target) < Math.abs(array[left] - target)) {
                result[i] = array[right];
                right++;
            } else {
                result[i] = array[left];
                left--;
            }
        }
        return result;
    }
}

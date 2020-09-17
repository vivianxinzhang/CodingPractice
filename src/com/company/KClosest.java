package com.company;

public class KClosest {
    // Time O(2^n)
    // Space O(n)
    public int[] kClosest(int[] array, int target, int k) {
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

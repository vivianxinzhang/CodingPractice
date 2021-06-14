package com.company;

public class SearchInBitonicArray {
    public static void main(String[] args) {
        SearchInBitonicArray s = new SearchInBitonicArray();

        int[] array = new int[] {1, 4, 7, 11, 6, 2, -3, -8};
        System.out.println(s.search(array, 2));     // 5
    }

    // Assumptions: The array is not null.
    // Time O(logn)
    // Space O(1)
    public int search(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int maxIdx = findLocalMaximum(array);
        int leftRes = increasingBinarySearch(array, target, 0, maxIdx);
        return leftRes != -1 ? leftRes : decreasingBinarySearch(array, target, maxIdx, array.length -1);
    }

    private int decreasingBinarySearch(int[] array, int target, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    private int increasingBinarySearch(int[] array, int target, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public int findLocalMaximum(int[] array) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid] > array[mid - 1] && array[mid] > array[mid + 1]) {
                return mid;
            } else if (array[mid] > array[mid - 1]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return array[left] > array[right] ? left : right;
    }
}

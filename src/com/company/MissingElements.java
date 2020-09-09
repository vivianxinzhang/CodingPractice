package com.company;

public class MissingElements {
    // Time O(logn)
    // Space O(n)
    // Assumption: The given array is not null, and N >= 1
    public int oneMissing1(int[] array) {
        if (array.length == 0) {
            return 1;
        }
        int left = 0, right =  array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid] - mid == 2) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (array[left] - left == 2) {
            return left + 1;
        }
        if (array[right] - right == 2) {
            return right + 1;
        }
        return right + 2;
    }

    public int oneMissing2(int[] array) {
        if (array.length == 0) {
            return 1;
        }
        int left = 0, right =  array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid] - mid == 2) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (array[left] - left == 2) {
            return left + 1;
        }
        if (array[right] - right == 2) {
            return right + 1;
        }
        return right + 2;
    }



    //   []		            return [0, 1]
    //    0  1  2
    //   [1, 3, 5]
    //    1  2  3  diff	    return [2, 4]
    //   [1, 3, 4]
    //    1  2  2  diff	    return [2, 5]
    //   [1, 2, 3]
    //    1  1  1  diff	    return [4, 5]
    // Time O(logn)
    // Space O(n)
    // Assumption: input is not null
    public int[] twoMissingElements(int[] array) {
        int[] result = new int[2];
        int firstIdx = firstOccur(array, 2);
        result[0] = firstIdx == -1 ? array.length + 1 : firstIdx + 1;
        int secondIdx = firstOccur(array, 3);
        result[1] = secondIdx == -1 ? array.length + 2 : secondIdx + 2;
    }

    // returns the idx of the first occur of array[i] - i == target
    private int firstOccur(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0, right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid] - mid >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (array[left] - left == target) {
            return left;
        }
        if (array[right] - right == target) {
            return right;
        }
        return -1;
    }
}

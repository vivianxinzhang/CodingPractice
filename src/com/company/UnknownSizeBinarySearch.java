package com.company;

public class UnknownSizeBinarySearch {
    public static void main(String[] args) {
        UnknownSizeBinarySearch s = new UnknownSizeBinarySearch();

        Dictionary dict = new DictImpl(new int[0]);
        System.out.println(s.search(dict, 0));  // -1

        dict = new DictImpl(new int[] {2, 3, 5});
        System.out.println(s.search(dict, 0));  // -1
        System.out.println(s.search(dict, 3));  // 1
        System.out.println(s.search(dict, 8));  // -1
    }

    /**
     * Binary search implementation on an dictionary with unknown size
     * Assumption:
     * 1). The dictionary is an unknown sized sorted array, it only provides
     *     get(int index) functionality, if the index asked for is out of right
     *     bound, it will return null.
     * 2). The elements in the dictionary are all Integers.
     */
    // Time O(logn)
    // Space O(1)
    public int search(Dictionary dict, int target) {
        // Write your solution here
        if (dict == null) {
            return -1;
        }
        int left = 0, right = 1;
        // find the right boundary for binary search
        // extends until we are sure the target is within the [left, right] range
        while (dict.get(right) != null && dict.get(right) < target) {
            // 1. move left to right
            // 2. double right index
            left = right;
            right *= 2;
        }
        return binarySearch(dict, left, right, target);
    }

    private int binarySearch(Dictionary dict, int left, int right, int target) {
        // classical binary search
        if (dict == null) {
            return -1;
        }
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (dict.get(mid) == null || dict.get(mid) > target) {
                right = mid - 1;
            } else if (dict.get(mid) < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}

interface Dictionary {
    public Integer get(int index);
}


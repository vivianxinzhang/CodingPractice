package com.company;

public class UnknownSizeBinarySearch {
    // Time O(logn)
    // Space O(1)
    public int search(Dictionary dict, int target) {
        // Write your solution here
        if (dict == null) {
            return -1;
        }
        int left = 0, right = 1;
        while (dict.get(right) != null && dict.get(right) < target) {
            left = right;
            right *= 2;
        }
        return binarySearch(dict, left, right, target);
    }

    private int binarySearch(Dictionary dict, int left, int right, int target) {
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

    public static void main(String[] args) {
        UnknownSizeBinarySearch s = new UnknownSizeBinarySearch();

        Dictionary dict = new DictImpl(new int[0]);
        System.out.println(s.search(dict, 0));

        dict = new DictImpl(new int[] {2, 3, 5});
        System.out.println(s.search(dict, 0));
        System.out.println(s.search(dict, 3));
        System.out.println(s.search(dict, 8));
    }
}

interface Dictionary {
    public Integer get(int index);
}


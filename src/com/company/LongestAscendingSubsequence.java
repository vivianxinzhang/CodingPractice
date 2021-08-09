package com.company;

public class LongestAscendingSubsequence {
    public static void main(String[] args) {
        LongestAscendingSubsequence s = new LongestAscendingSubsequence();

        int[] array = new int[] {3, 1, 2, 0, 3, 4, 5};
        System.out.println(s.longest(array));  // 5

        array = new int[] {5, 2, 6, 3, 4, 7, 5};
        System.out.println(s.longest(array));  // 4

        array = new int[] {123, 236, 227, 258, 138, 441, 496, 479, 124, 389, 84, 104, 143, 24, 363, 379, 304, 317, 10, 121, 179, 179, 267};
        System.out.println(s.longest(array));  // 5
    }

    // Method 2:
    // Time: O(nlogn)
    // Space: O(n)
    public int longest(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        // tbl[i]: the smallest ending value of all the ascending subsequences with length i.
        int[] M = new int[array.length + 1];
        // at the very beginning, the longest ascending subsequence we have has length 1 (array[0] itself).
        int result = 1;
        // initialization: at the very beginning, we have a length 1 ascending subsequence,
        // ending at array[0].
        // while we traversing the array, we will update existing tbl[i] and find new longer
        // ascending subsequence.
        M[1] = array[0];
        for (int i = 1; i < array.length; i++) {
            // tbl is guaranteed to be in ascending order - the key point.
            // from tbl, find the best(longest) ascending subsequence, which can
            // concatenate array[i] to form a new one.
            // this is actually a binary search of the "largest smaller value".
            int index = binarySearch(M, 1, result, array[i]);
            // two cases:
            // 1. we can possibly form a longer ascending subsequence than whatever we
            // have before, if array[i] is larger than all the values in tbl.
            // 2. we may update tbl[index + 1] because we find a better ascending subsequence
            // with length index + 1 (the ending value is smaller or equal).
            if (index == result) {
                M[++result] = array[i];
            } else {
                M[index + 1] = array[i];
            }
        }
        return result;
    }

    // find the index of the "largest smaller value" to target in M,
    // M is sorted in ascending order.
    private int binarySearch(int[] array, int left, int right, int target) {
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (array[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        if (array[right] <= target) {
            return right;
        }
        if (array[left] < target) {
            return left;
        }
        return 0;
    }

    // Method 1:
    // Time: O(n^2)
    // Space: O(n)
    public int longestI(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        // dp[i] = the length of the longest ascending subsequence ending at index i.
        int[] longest = new int[array.length];
        // record the length of longest ascending subsequence so far.
        int result = 1;
        for (int i = 0; i < array.length; i++) {
            // initialize dp[i] as 1, since the shortest one has length 1,
            // (just array[i] itself).
            longest[i] = 1;
            for (int j = 0; j < i; j++) {
                // only when array[j] < array[i], it is possible to use the
                // longest ascending subsequence ending at index j and array[i]
                // to form a new ascending subsequence.
                if (array[j] < array[i]) {
                    longest[i] = Math.max(longest[j] + 1, longest[i]);
                }
            }
            // possibly update the global longest one.
            result = Math.max(result, longest[i]);
        }
        return result;
    }
}

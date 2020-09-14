package com.company;

public class kSmallestTwoSortedArray {
    // Time = O(k)
    // Space O(1)
    public int kthI(int[] a, int[] b, int k) {
        // Write your solution here
        if (k < 0 || k > a.length + b.length) {
            return -1;
        }
        int kSmallest = Integer.MAX_VALUE;
        int i = 0, j = 0;
        for (int t = 0; t < k; t++) {
            if (i < a.length && j < b.length) {
                kSmallest = a[i] < b[j] ? a[i++] :b[j++];
            } else if (i < a.length) {
                kSmallest = a[i++];
            } else {
                kSmallest = b[j++];
            }
        }
        return kSmallest;
    }

    // Recursion
    // Time O(logk)
    // Space O(logk)
    public int kthII(int[] a, int[] b, int k) {
        // Write your solution here
        // Assumptions: a, b is not null, at least one of them is not empty,
        // k <= a.length + b.length, k >= 1
        return kth(a, 0, b, 0, k);
    }

    // in the subarray of a starting from index aleft, and subarray b starting from
    // index bleft, find the kth smallest element among these two subarrays
    private int kth(int[] a, int aleft, int[] b, int bleft, int k) {
        // three base cases:
        // 1. we already eliminate all the elements in a
        // 2. we already eliminate all the elements in b
        // 3. when k is reduced to 1, don't miss this base case
        // The reason why we have this as base case is in the following logic
        // we need k >= 2 to make it work
        if (aleft >= a.length) {
            return b[bleft + k - 1];
        }
        if (bleft >= b.length) {
            return a[aleft + k - 1];
        }
        if (k == 1) {
            return Math.min(a[aleft], b[bleft]);
        }
        // we compare the k/2 th element in a's subarray
        // and the k/2 the element in b's subarray
        // to determine which k/2 partition can be surely included in the smallest k elements
        int amid = aleft + k / 2 - 1;
        int bmid = bleft + k / 2 - 1;
        int aval = amid >= a.length ? Integer.MAX_VALUE : a[amid];
        int bval = bmid >= b.length ? Integer.MAX_VALUE : b[bmid];
        if (aval <= bval) {
            return kth(a, amid + 1, b, bleft, k - k / 2);
        } else {
            return kth(a, aleft, b, bmid + 1, k - k / 2);
        }
    }


}

package com.company;

public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] A = new int[] {1, 2, 4};
        int[] B = new int[] {3, 5};
        System.out.println(s.median(A, B)); // 3.0

        A = new int[] {};
        B = new int[] {1};
        System.out.println(s.median(A, B));  // 1.0


        A = new int[] {1, 4};
        B = new int[] {2, 3};
        System.out.println(s.median(A, B));  // 2.5

        A = new int[] {1};
        B = new int[] {2, 3};
        System.out.println(s.median(A, B));   // 2.0
    }

    // Time O(k)
    // Space O(1)
    public double median(int[] a, int[] b) {
        // 0, 1, 2, 3
        // (1th + 2th) / 2
        // 0, 1, 2
        // 1th
        int total = a.length + b.length;
        if (total == 1) {
            return a.length == 0 ? b[0] : a[0];
        }
        int halfIdx = (total - 1) / 2;
        int kSmallest = Integer.MAX_VALUE;
        int i = 0, j = 0;
        for (int t = 0; t <= halfIdx; t++) {
            if (i < a.length && j < b.length) {
                kSmallest = a[i] < b[j] ? a[i++] :b[j++];
            } else if (i < a.length) {
                kSmallest = a[i++];
            } else {
                kSmallest = b[j++];
            }
        }
        if (total % 2 != 0) {
            return kSmallest;
        }
        int nextSmallest;
        if (i < a.length && j < b.length) {
            nextSmallest = a[i] < b[j] ? a[i] : b[j];
        } else if (i < a.length) {
            nextSmallest = a[i];
        } else {
            nextSmallest = b[j];
        }
        return ((double)kSmallest + nextSmallest) / 2;
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

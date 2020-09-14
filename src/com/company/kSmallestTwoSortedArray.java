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
}

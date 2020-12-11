package com.company;

public class KthSmallestInTwoSortedArrays {
    public static void main(String[] args) {
        KthSmallestInTwoSortedArrays s = new KthSmallestInTwoSortedArrays();
        int[] A = new int[] {1, 4, 6};
        int[] B = new int[] {2, 3};
        System.out.println(s.kth(A, B, 3));  // 3
        System.out.println(s.kthI(A, B, 3));  // 3
        System.out.println(s.kthII(A, B, 3));  // 3

        A = new int[] {1, 2, 3, 4};
        B = new int[] {};
        System.out.println(s.kth(A, B, 2));  // 2
        System.out.println(s.kthI(A, B, 2));  // 2
        System.out.println(s.kthII(A, B, 2));  // 2
    }

    // Assumptions: a, b is not null, at least one of them is not empty,
    // k <= a.length + b.length, k >= 1
    // Method 2: Binary Search
    // 核心：1. search space decreases over time after each iteration
    //      2. target cannot be ruled out
    // Time O(mn)
    // Space O(mn)
    public int kth(int[] a, int[] b, int k) {
        return kth(a, 0, b, 0, k);
    }

    // in the subarray of a starting from index aleft, and subarray b starting from
    // index bleft, find the kth smallest element among these two subarrays
    public int kth(int[] a, int aLeft, int[] b, int bLeft, int k) {
        // three base cases:
        // 1. we already eliminate all the elements in a
        // 2. we already eliminate all the elements in b
        // 3. when k is reduced to 1, don't miss this base case
        // The reason why we have this as base case is in the following logic
        // we need k >= 2 to make it work
        if (aLeft >= a.length) {
            return b[bLeft + k - 1];
        }
        if (bLeft >= b.length) {
            return a[aLeft + k - 1];
        }
        if (k == 1) {
            return Math.min(a[aLeft], b[bLeft]);
        }
        // we compare the k/2 th element in a's subarray
        // and the k/2 the element in b's subarray
        // to determine which k/2 partition can be surely
        // included in the smallest k elements
        int amid = aLeft + k/2 - 1;
        int bmid = bLeft + k/2 - 1;
        int aval = amid >= a.length ? Integer.MAX_VALUE : a[amid];
        int bval = bmid >= b.length ? Integer.MAX_VALUE : b[bmid];
        if (aval <= bval) {
            return kth(a, amid + 1, b, bLeft, k - k/2);
        } else {
            return kth(a, aLeft, b, bmid + 1, k - k/2);
        }
    }

    // Method 1: two pointers
    // use two pointers i and j to traverse A and B loop k rounds to find the kth smallest
    // value by moving forward the pointer pointing to smaller value in each round
    // Time O(k)
    // Space O(1)
    public int kthI(int[] a, int[] b, int k) {
        if (k < 0 || k > a.length + b.length) {
            return Integer.MIN_VALUE;
        }
        int kthSmallest = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;
        for (int count = 0; count < k; count++) {
            if (i < a.length && j < b.length) {
                kthSmallest = a[i] < b[j] ? a[i++] : b[j++];
            } else if (i < a.length) {
                kthSmallest = a[i++];
            } else {
                kthSmallest = b[j++];
            }
        }
        return kthSmallest;
    }

    // Space： O(logk) → O(1)
    // Time O(logk)
    // Space O(1)
    public int kthII(int[] a, int[] b, int k) {
        // Write your solution here
        if (k < 0 || k > a.length + b.length) {
            return -1;
        }
        int aleft = 0, bleft = 0;
        while (k >= 2) { // 大家思考一下这里为什么要k >= 2
            if (aleft >= a.length) {
                return b[bleft + k - 1];
            }
            if (bleft >= b.length) {
                return a[aleft + k - 1];
            }
            int amid = aleft + k/2 - 1;
            int bmid = bleft + k/2 - 1;
            int aval = amid >= a.length ? Integer.MAX_VALUE : a[amid];
            int bval = bmid >= b.length ? Integer.MAX_VALUE : b[bmid];
            if (aval <= bval) {
                aleft = amid + 1;
            } else {
                bleft = bmid + 1;
            }
            k -= k/2;
        }
        if (aleft >= a.length) {
            return b[bleft];
        }
        if (bleft >= b.length) {
            return a[aleft];
        }
        return a[aleft] < b[bleft] ? a[aleft] : b[bleft];
    }
/* 每次k减小 K/2 元素
   Example：
      K = 4   第一次去掉了2个元素
      K = 4 - 2 = 2   这时候还没有找到K小 还需要再次进循环
      K = 1 这个时候的result应该是 两个array valid index里谁的第一个元素比较小 答案就是谁
*/
}

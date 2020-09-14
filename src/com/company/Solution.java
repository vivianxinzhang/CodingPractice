package com.company;

import java.util.*;


// Iteration
// Time O(logk)
// Space O(1)
public class Solution {
    public int kth(int[] a, int[] b, int k) {
        // Write your solution here
        if (k < 0 || k > a.length + b.length) {
            return -1;
        }
        int aleft = 0, bleft = 0;
        while (k >= 2) {
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
            // smallest element in b
            return b[bleft];
        }
        if (bleft >= b.length) {
            // smallest element in a
            return a[aleft];
        }
        // return smaller between a and b
        return a[aleft] < b[bleft] ? a[aleft] : b[bleft];
    }
}



//public class Solution {
//    String s1 = 5 + "10";
//    String s2 = (char)(60 + 1) + "s";
//    long num1 = (long)10 + 5.0;
//    int num2 = (int)(1.5 + (double)2);
//    char c = (char)"a";
//    public void recurse(int x) {
//        System.out.print(x % 10);
//        if ((x / 10) != 0) {
//            recurse(x / 10);
//        }
//        System.out.print(x % 10);
//    }
//}

// Clarification:
// Assumption: sorted, integers, increasing, k compare to size of A + B?
// The two given arrays are not null and at least one of them is not empty
// k>=1, k <= total length of the two sorted arrays
// input: in[] A, int[] B, k
// output: int kSmallest
// High level :  这道题本质是什么,有多少种方法可以做, 为什么.
// find the kth smallest in two sorted array
// Solution 1: 谁小移谁
// use two pointers i and j to traverse A and B
// loop k rounds to find the kth smallest value by moving forward the pointer pointing to smaller value in each round
// Time = O(k)
// Space O(1)



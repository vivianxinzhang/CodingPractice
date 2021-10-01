package com.company;

import java.util.Arrays;

public class TwoSumTwoArrays {
    public static void main(String[] args) {
        TwoSumTwoArrays s = new TwoSumTwoArrays();
        int[] A = new int[] {3, 1, 5};
        int[] B = new int[] {2, 8};
        System.out.println(s.existSum(A, B, 7));    // true

        A = new int[] {-1, 0, 1};
        B = new int[] {7, 3};
        System.out.println(s.existSum(A, B, 8));    // true
    }

    // Assumptions:
    // The two given arrays are not null and have length of at least 1
    // Time (mlogm + nlogn)
    // Space O(1)
    public boolean existSum(int[] a, int[] b, int target) {
        Arrays.sort(a);
        Arrays.sort(b);
        int i = 0;
        int j = b.length - 1;
        while (i < a.length && j >= 0) {
            int twoSum = a[i] + b[j];
            if (twoSum == target) {
                return true;
            } else if (twoSum < target) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }

    // Method 1: brute force
    // Time O(n^2)
    // Space O(1)
    public boolean existSumI(int[] a, int[] b, int target) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                if (a[i] + b[j] == target) {
                    return true;
                }
            }
        }
        return false;
    }
}

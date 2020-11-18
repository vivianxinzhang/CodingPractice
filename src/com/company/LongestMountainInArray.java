package com.company;

public class LongestMountainInArray {
    public static void main(String[] args) {
        LongestMountainInArray s = new LongestMountainInArray();
        int[] array = new int[] {2,1,4,7,3,2,5};
        System.out.println(s.longestMountain(array));

        array = new int[] {2,2,2};
        System.out.println(s.longestMountain(array));
    }


    // Method 1:
    // Time O(n)
    // Space O(1)
    public int longestMountainI(int[] A) {
        int inc = 0;
        int dec = 0;
        int ans = 0;
        for (int i = 1; i < A.length; ++i) {
            if (dec > 0 && A[i] > A[i - 1] || A[i] == A[i - 1])
                dec = inc = 0;
            if (A[i] > A[i - 1]) {
                inc++;
            }
            if (A[i] < A[i - 1]) {
                dec++;
            }
            // inc += A[i] > A[i - 1] ? 1 : 0;
            // dec += A[i] < A[i - 1] ? 1 : 0;
            if (inc > 0 && dec > 0)
                ans = Math.max(ans, inc + dec + 1);
        }
        return ans >= 3 ? ans : 0;
    }

    // Method 1:
    // Time O(n)
    // Space O(1)
    public int longestMountain(int[] A) {
        int ans = 0;
        int[] inc = new int[A.length];
        int[] dec = new int[A.length];
        for (int i = 1; i < A.length; i++) {
            if (A[i] > A[i - 1]) {
                inc[i] = inc[i - 1] + 1;
            }
        }
        for (int i = A.length - 2; i >= 0; i--) {
            if (A[i] > A[i + 1]) {
                dec[i] = dec[i + 1] + 1;
            }
        }
        for (int i = 0; i < A.length; i++)
            if (inc[i] > 0 && dec[i] > 0)
                ans = Math.max(ans, inc[i] + dec[i] + 1);
        return ans >= 3 ? ans : 0;
    }
}

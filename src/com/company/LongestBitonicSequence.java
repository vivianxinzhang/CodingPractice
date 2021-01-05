package com.company;

public class LongestBitonicSequence {
    public static void main(String[] args) {
        LongestBitonicSequence s = new LongestBitonicSequence();
        int[] array = new int[] {1, 2};
        System.out.println(s.longestBitonic(array));    // 2

        array = new int[] {1, 3, 2, 1, 4, 6, 1};
        System.out.println(s.longestBitonic(array));    // 5
    }
    // Assumptions:
    // The given array is not null.
    // Corner Cases:
    // A subsequence, sorted in increasing order is considered Bitonic with the decreasing part as empty. Similarly,
    // decreasing order sequence is considered Bitonic with the increasing part as empty.
    // Step 1: get length of longest increasing subsequence on left of i and on right of i for each index
    // Step 2: update length of Longest Bitonic Sequence with i as the peak
    public int longestBitonic(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int[] M = new int[array.length];
        int[] incLeftSubsequence = getIncSubsequenceLeftToRight(array);
        int[] intRightSubsequence = getIncSubsequenceRightToLeft(array);
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            int curr = incLeftSubsequence[i] + intRightSubsequence[i] - 1;
            max = Math.max(max, curr);
        }
        return max;
    }

    // only consider Bitonic Sequence with both increasing and decreasing part
    // Time O(n^2)
    // Space O(n)
    public int longestBitonicI(int[] array) {
        if (array == null || array.length < 3) {
            return 0;
        }
        int[] M = new int[array.length];
        int[] incLeftSubsequence = getIncSubsequenceLeftToRight(array);
        int[] intRightSubsequence = getIncSubsequenceRightToLeft(array);
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            int curr = 0;
            if (incLeftSubsequence[i] > 1 && intRightSubsequence[i] > 1) {
                curr = incLeftSubsequence[i] + intRightSubsequence[i] - 1;
            }
            max = Math.max(max, curr);
        }
        return max;
    }

    private int[] getIncSubsequenceLeftToRight(int[] array) {
        int[] M = new int[array.length];
        M[0] = 1;
        for (int i = 1; i < array.length; i++) {
            M[i] = 1;
            for (int j = 0; j < i; j++) {
                if (array[i] > array[j]) {
                    M[i] = Math.max(M[i], M[j] + 1);
                }
            }
        }
        return M;
    }

    private int[] getIncSubsequenceRightToLeft(int[] array) {
        int[] M = new int[array.length];
        M[array.length - 1] = 1;
        for (int i = array.length - 2; i >= 0; i--) {
            M[i] = 1;
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    M[i] = Math.max(M[i], M[j] + 1);
                }
            }
        }
        return M;
    }
}

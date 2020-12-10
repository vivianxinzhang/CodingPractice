package com.company;
import java.util.Arrays;

public class LongestAscendingSubsequenceII {
    public static void main(String[] args) {
        LongestAscendingSubsequenceII s = new LongestAscendingSubsequenceII();
        int[] array = new int[] {5, 2, 6, 3, 4, 7, 5};
        System.out.println(Arrays.toString(s.longest(array)));  // 2, 3, 4, 7
    }

    // Assumptions: A is not null
    // Method 1:
    // Time: O(n^2)
    // Space: O(n)
    public int[] longest(int[] array) {
        if (array.length == 0) {
            return new int[0];
        }
        // dp[i] = the length of the longest ascending subsequence ending at index i.
        int[] longest = new int[array.length];
        int[] pred = new int[array.length];
        int iMax = 0;
        // record the length of longest ascending subsequence so far.
        // int result = 1;
        for (int i = 0; i < array.length; i++) {
            // initialize dp[i] as 1, since the shortest one has length 1,
            // (just array[i] itself).
            longest[i] = 1;
            pred[i] = -1;
            for (int j = 0; j < i; j++) {
                // only when array[j] < array[i], it is possible to use the
                // longest ascending subsequence ending at index j and array[i]
                // to form a new ascending subsequence.
                if (array[j] < array[i] && longest[j] + 1 > longest[i]) {
                    longest[i] = longest[j] + 1;
                    pred[i] = j;
                }
            }
            // possibly update the global longest one.
            // result = Math.max(result, longest[i]);
            if (longest[i] > longest[iMax]) {
                iMax = i;
            }
        }
        int[] result = new int[longest[iMax]];
        return traceBack(iMax, result, array, pred);
    }

    // iMax is the index to the largest M[i]
    private int[] traceBack(int iMax, int[] result, int[] array, int[] pred) {
        int j = result.length - 1;
        while (iMax != -1) {
            result[j] = array[iMax];
            iMax = pred[iMax];
            j--;
        }
        return result;
    }
}

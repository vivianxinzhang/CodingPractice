package com.company;
import java.util.Arrays;

public class CountAscendingSubsequence {
    public static void main(String[] args) {
        CountAscendingSubsequence s = new CountAscendingSubsequence();
        int[] array = new int[] {};
        System.out.println(s.numIncreasingSubsequences(array));  // 0

        array = new int[] {1, 2, 3};
        System.out.println(s.numIncreasingSubsequences(array));  // 7
                array = new int[] {1, 3, 2, 2};
        System.out.println(s.numIncreasingSubsequences(array));  // 7

        array = new int[] {1, 2, 3};
        System.out.println(s.numIncreasingSubsequences(array));  // 7
    }

    // Assumptions: A is not null
    // Base case:
    // M[0] = 1
    // Induction rule:
    // M[i] represents the number of ascending subsequences ending at input[i], must include the i-th element.
    // M[i] = 1 + SUM(M[j])		for all j that if 0 <= j < i and a[j] < a[i]
    // Time: O(n^2)
    // Space: O(n)
    public int numIncreasingSubsequences(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int[] M = new int[array.length];
        M[0] = 1;
        int sum = 1;
        for (int i = 1; i < array.length; i++) {
            M[i] = 1;
            for (int j = 0; j < array.length; j++) {
                if (array[j] < array[i]) {
                    M[i] += M[j];
                }
            }
            sum += M[i];
        }
        return sum;
    }
}
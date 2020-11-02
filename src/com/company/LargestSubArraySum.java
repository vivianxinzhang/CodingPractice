package com.company;
import java.util.Arrays;

public class LargestSubArraySum {
    public static void main(String[] args) {
        LargestSubArraySum s = new LargestSubArraySum();
        int[] array = new int[] {2, -1, 4, -2, 1};
        System.out.println(Arrays.toString(s.largestSum(array)));

        array = new int[] {-2, -1, -3};
        System.out.println(Arrays.toString(s.largestSum(array)));
    }

    // Time O(n)
    // Space O(n)
    public int[] largestSum(int[] array) {
        int[] M = new int[array.length];
        M[0] = array[0];
        int maxSum = array[0];
        int globalLeft = 0;
        int globalRight = 0;
        int currLeft = 0;
        for (int i = 1; i < array.length; i++) {
            if (M[i - 1] > 0) {
                M[i] = M[i - 1] + array[i];
            } else {
                M[i] = array[i];
                currLeft = i;
            }
            if (M[i] > maxSum) {
                globalLeft = currLeft;
                globalRight = i;
                maxSum = M[i];
            }
        }
        int[] result = new int[3];
        result[0] = maxSum;
        result[1] = globalLeft;
        result[2] = globalRight;
        return result;
    }
}

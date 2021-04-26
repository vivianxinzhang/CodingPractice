package com.company;
import java.util.Arrays;

public class LargestSubArraySum {
    public static void main(String[] args) {
        LargestSubArraySum s = new LargestSubArraySum();
        int[] array = new int[] {2, -1, 4, -2, 1};
        System.out.println(Arrays.toString(s.largestSum(array)));
        // [5, 0, 2]

        array = new int[] {-2, -1, -3};
        System.out.println(Arrays.toString(s.largestSum(array)));
        // [-1, 1, 1]

        array = new int[] {1, -2, 3};
        System.out.println(Arrays.toString(s.largestSum(array)));
        // [3, 2, 2]
    }

    // Assumptions：
    // The given array is not null and has length of at least 1.
    // M[i] represents largest SubArray Sum ending at index i
    // M[i] =  if M[i-1] > 0  M[i-1] + array[i]
    //         else           M[i] = array[i]
    // base case: M[0] = array[0]
    // Time O(n)
    // Space O(n)
    public int largestSumI(int[] array) {
        // Write your solution here
        if (array == null || array.length == 0) {
            return 0;
        }
        int[] M = new int[array.length];
        M[0] = array[0];
        int maxSum = array[0];
        for (int i = 1; i < array.length; i++) {
            if (M[i - 1] > 0) {
                M[i] = M[i - 1] + array[i];
            } else {
                M[i] = array[i];
            }
            maxSum = Math.max(maxSum, M[i]);
        }
        return maxSum;
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

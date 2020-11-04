package com.company;
import java.util.*;

public class MaxSubarraySumDifference {
    public static void main(String[] args) {
        MaxSubarraySumDifference s = new MaxSubarraySumDifference();
        int[] array = new int[] {2, -2, 2, -2, 2, -2};
        System.out.println(s.maxDiff(array));   // 4

        array = new int[] {4, -2, 0, 3, 1, 5, -4};
        System.out.println(s.maxDiff(array));   // 15

        array = new int[] {1, -3, 1, -4, 3, 4 };
        System.out.println(s.maxDiff(array));   // 13
    }

    // Time O(n)
    // Space O(n)
    public int maxDiff(int[] array) {
        // int[] {maxSum, left, right}
        int[] maxResult = maxSubArraySum(array);
        if (maxResult[2] - maxResult[1] + 1== array.length) {
            int smallestSubArraySum = Math.min(array[0], array[array.length - 1]);
            return Math.abs(maxResult[0] - smallestSubArraySum - smallestSubArraySum);
        }
        int minSubArraySum = Integer.MAX_VALUE;
        if (maxResult[1] > 0) {
            int leftMin = minSubArraySum(array, 0, maxResult[1] - 1);
            minSubArraySum = Math.min(minSubArraySum, leftMin);
        }
        if (maxResult[2] < array.length - 1) {
            int rightMin = minSubArraySum(array, maxResult[2] + 1, array.length - 1);
            minSubArraySum = Math.min(minSubArraySum, rightMin);
        }
        return Math.abs(maxResult[0] - minSubArraySum);
    }

    private int minSubArraySum(int[] array, int left, int right) {
        if (left == right) {
            return array[left];
        }
        int minSum = array[left];
        int minCur = array[left];
        for (int i = left + 1; i <= right; i++) {
            if (minCur < 0) {
                minCur = minCur + array[i];
            } else {
                minCur = array[i];
            }
            minSum = Math.min(minSum, minCur);
        }
        return minSum;
    }

    public int[] maxSubArraySum(int[] array) {
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

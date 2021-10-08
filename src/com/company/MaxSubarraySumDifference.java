package com.company;
import java.util.*;

public class MaxSubarraySumDifference {
    public static void main(String[] args) {
        MaxSubarraySumDifference s = new MaxSubarraySumDifference();

        int[] array = new int[] {1, 1};
        System.out.println(s.maxDiff(array));     // 0
        array = new int[] {2, -2, 2, -2, 2, -2};
        System.out.println(s.maxDiff(array));     // 4
        array = new int[] {1, -3, 1, -4, 3, 4 };
        System.out.println(s.maxDiff(array));     // 13
        array = new int[] {4, -2, 0, 3, 1, 5, -4};
        System.out.println(s.maxDiff(array));     // 15
    }

    // Assumptions:
    // The given array is not null and has length of at least 2.
    // Method 2: dp
    // Time O(n)
    // Space O(n)
    public int maxDiff(int[] array) {
        // int[] {maxSum, left, right}
        int[] maxResult = maxSubArraySum(array);
        if (maxResult[2] - maxResult[1] + 1== array.length) {
            int smallestSubArraySum = Math.min(array[0], array[array.length - 1]);
            int maxSubarraySum = maxResult[0] - smallestSubArraySum;
            return Math.abs(maxSubarraySum - smallestSubArraySum);
        }
        int maxLeft = maxResult[0];
        int maxRight = maxResult[1];
        int minSubArraySum = Integer.MAX_VALUE;
        if (maxLeft > 0) {
            int leftMin = minSubArraySum(array, 0, maxLeft - 1);
            minSubArraySum = Math.min(minSubArraySum, leftMin);
        }
        if (maxRight < array.length - 1) {
            int rightMin = minSubArraySum(array, maxRight + 1, array.length - 1);
            minSubArraySum = Math.min(minSubArraySum, rightMin);
        }
        return Math.abs(maxResult[0] - minSubArraySum);
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

    // Method 1: optimize calculate range sum
    // pre-processing: prefix sum
    // Time O(n^3)
    // Space O(n)
    public int maxDiffII(int[] array) {
        int[] prefixSum = getPrefixSum(array);
        int maxDiff = Integer.MIN_VALUE;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                // k is the divider of two sub-arrays, first int of the right part
                for (int k = i + 1; k <= j; k++) {
                    int leftSum = rangeSum(prefixSum, i, k - 1, array);
                    int rightSum = rangeSum(prefixSum, k, j, array);
                    int currDiff = Math.abs(leftSum - rightSum);
                    maxDiff = Math.max(maxDiff, currDiff);
                }
            }
        }
        return maxDiff;
    }

    private int rangeSum(int[] prefixSum, int left, int right, int[] array) {
        return prefixSum[right] - prefixSum[left] + array[left];
    }

    private int[] getPrefixSum(int[] nums) {
        int[] prefixSum = new int[nums.length];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            prefixSum[i] = sum;
        }
        return prefixSum;
    }

    // Method 1: brute force
    // Step 1: enumerate all contiguous sub-arrays of length >= 2
    // Step 2:  enumerate all divider positions
    // Step 3: compute leftSum and rightSum, and curr diff, update globalMaxDiff
    // Time O(n^4)
    // Space O(1)
    public int maxDiffI(int[] array) {
        int maxDiff = Integer.MIN_VALUE;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                // k is the divider of two subarrays, first idx of the right part
                for (int k = i + 1; k <= j; k++) {
                    int leftSum = sum(array, i, k - 1);
                    int rightSum = sum(array, k, j);
                    int currDiff = Math.abs(leftSum - rightSum);
                    maxDiff = Math.max(maxDiff, currDiff);
                }
            }
        }
        return maxDiff;
    }

    private int sum(int[] array, int left, int right) {
        int sum = 0;
        for (int i = left; i <= right; i++) {
            sum += array[i];
        }
        return sum;
    }
}

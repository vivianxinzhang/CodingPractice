package com.company;

public class MinimumSizeOfSubarraySum {
    public static void main(String[] args) {
        MinimumSizeOfSubarraySum s = new MinimumSizeOfSubarraySum();
        int[] array = new int[] {1, 2};
        int sum = 4;
        System.out.println(s.minSubArrayLen(sum, array));   // 0

        array = new int[] {19};
        sum = 18;
        System.out.println(s.minSubArrayLen(sum, array));   // 1

        array = new int[] {2,3,1,2,4,3};
        sum = 7;
        System.out.println(s.minSubArrayLen(sum, array));   // 2
    }

    // Time O(n)
    // Space O(1)
    // sliding window [slow, fast] sum of  sum ≥ s
    public int minSubArrayLen(int s, int[] array) {
        if (array == null) {
            return 0;
        }
        int slow = 0;
        int fast = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;
        while (fast < array.length) {
            sum += array[fast];
            fast++;
            while (sum >= s) {
                minLength = Math.min(minLength, fast - slow);
                sum -= array[slow];
                slow++;
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    // Method 2: optimize range sum query
    // Time O(n^2)
    // Space O(n)
    public int minSubArrayLenII(int num, int[] array) {
        int minLength = Integer.MAX_VALUE;
        int[] prefixSum = getPrefixSum(array);
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array.length; j++) {
                int currSum = getRangeSum(prefixSum, i, j, array);
                if (currSum >= num) {
                    minLength = Math.min(minLength, j - i + 1);
                }
            }
        }
        return minLength;
    }

    private int getRangeSum(int[] prefixSum, int i, int j, int[] array) {
        return prefixSum[j] - prefixSum[i] + array[i];
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
    // Time O(n^3)
    // Space O(1)
    public int minSubArrayLenI(int num, int[] array) {
        int minLength = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array.length; j++) {
                int currSum = sumUp(array, i, j);
                if (currSum >= num) {
                    minLength = Math.min(minLength, j - i + 1);
                }
            }
        }
        return minLength;
    }

    private int sumUp(int[] array, int left, int right) {
        int sum = 0;
        for (int i = left; i <= right; i++) {
            sum += array[i];
        }
        return sum;
    }
}

package com.company;

public class CanIWinEatingPizza {
    public static void main(String[] args) {
        CanIWinEatingPizza s = new CanIWinEatingPizza();
        int[] array = new int[] {10, 10};
        System.out.println(s.canWin(array));    // true

        array = new int[] {1, 100, 1};
        System.out.println(s.canWin(array));    // false

        array = new int[] {2, 1, 100, 3};
        System.out.println(s.canWin(array));    // true

        array = new int[] {60,77,46,53,84,79,19};
        System.out.println(s.canWin(array));    // true
    }

    // Method 1: optimized
    // M[i][j] represents [from the ith pizza to the jth pizza] the largest total sum of
    // total pizza I can get assuming you start first.
    // Time O(n^2)
    // Space O(n^2)
    public boolean canWin(int[] nums) {
        int[] prefixSum = getPrefixSum(nums);
        int[][] M = new int[nums.length][nums.length];
        // Base case:
        // 1 piece of pizza: M[i][i] = input[i]
        // 2 piece of pizza: M[i][i + 1] = max{input[i], input[i+1]}
        for (int i = 0; i < nums.length; i++) {
            M[i][i] = nums[i];
            if (i < nums.length - 1) {
                M[i][i + 1] = Math.max(nums[i], nums[i + 1]);
            }
        }
        // 枚举所有的 subproblem 问题规模, 从小到大
        for (int length = 3; length <= nums.length; length++) {
            // 枚举所有可能的起点
            for (int i = 0; i <= nums.length - length; i++) {
                int j = i + length - 1;
                // Case 1: if I eat i, my friend's problem become M[i+1][j]
                // I will get rangeSum - M[i+1][j]
                // Case 2: if I eat j, my friend's problem become M[i][j-1]
                // I will get rangeSum - M[i][j-1]
                int rangeSum = getRangeSum(prefixSum, i, j, nums);
                // I will choose i or j which give me a larger total sum in the current round
                M[i][j] = Math.max(rangeSum - M[i + 1][j], rangeSum - M[i][j - 1]);
            }
        }
        int mySum = M[0][nums.length -1];
        int totalSum = getRangeSum(prefixSum, 0, nums.length - 1, nums);
        return mySum >= totalSum - mySum;
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

    // Method 1:
    // M[i][j] represents [from the ith pizza to the jth pizza] the largest total sum of
    // total pizza I can get assuming I start first.
    // Time O(n^2 * n)  --> can optimize query subArray sum
    // Space O(n^2)
    public boolean canWinI(int[] nums) {
        int[][] M = new int[nums.length][nums.length];
        // Base case:
        // 1 piece of pizza: M[i][i] = input[i]
        // 2 piece of pizza: M[i][i + 1] = max{input[i], input[i+1]}
        for (int i = 0; i < nums.length; i++) {
            M[i][i] = nums[i];
            if (i < nums.length - 1) {
                M[i][i + 1] = Math.max(nums[i], nums[i + 1]);
            }
        }
        // 枚举所有的 subproblem 问题规模, 从小到大
        for (int length = 3; length <= nums.length; length++) {
            // 枚举所有可能的起点
            for (int i = 0; i <= nums.length - length; i++) {
                int j = i + length - 1;
                // Case 1: if I eat i, my friend's problem become M[i+1][j]
                // Case 2: if I eat j, my friend's problem become M[i+1][j - 1]
                // I will choose i or j which give me a larger total sum in the current round
                int sum = getSum(nums, i, j);
                M[i][j] = Math.max(sum - M[i + 1][j], sum - M[i][j - 1]);
            }
        }
        int mySum = M[0][nums.length -1];
        int friendSum = getSum(nums, 0, nums.length - 1) - mySum;
        return mySum >= friendSum;
    }

    private int getSum(int[] nums, int start, int end) {
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += nums[i];
        }
        return sum;
    }
}

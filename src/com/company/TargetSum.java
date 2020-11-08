package com.company;
import java.util.*;

public class TargetSum {
    public static void main(String[] args) {
        TargetSum s = new TargetSum();
        int[] array = new int[] {1, 0};
        System.out.println(s.waysToTargetSum(array, 1));
        System.out.println(s.waysToTargetSumI(array, 1));
    }

    // Method 3: 2D Dynamic Programming
    // Time O(n) <- O(l*n) l is range of sum
    // Space O(n) <- O(l*n) l is range of sum
    public int waysToTargetSumI(int[] nums, int target) {
        int sum = 0;
        for (int num : nums)
            sum += num;
        if (sum < target) return 0;
        int kOffset = sum;
        int kMaxN = sum * 2 + 1;
        int[] ways = new int[kMaxN];
        ways[kOffset] = 1;
        for (int num : nums) {
            int[] tmp = new int[kMaxN];
            for (int i = num; i < kMaxN - num; ++i) {
                tmp[i + num] += ways[i];
                tmp[i - num] += ways[i];
            }
            ways = tmp;
        }
        return ways[target + kOffset];
    }

    // Method 2: recursion + memo
    // Assumption:
    // The length of the given array is positive and will not exceed 20.
    // The sum of elements in the given array will not exceed 1000.
    // Your output answer is guaranteed to be fitted in a 32-bit integer.
    // Time O(n) <- O(l*n) l is range of sum which is 2000
    // Space O(n) <- O(l*n) l is range of sum which is 2000
    public int waysToTargetSumII(int[] nums, int target) {
        // Write your solution here
        int[] num = new  int[] {0};
        int sum = 0;
        // for every call to calculate(nums, i, sum, S),
        // we store the result obtained in memo[i][sum + 1000]memo[i][sum+1000].
        // The factor of 1000 has been added as an offset to the sum value to
        // map all the sums possible to positive integer range
        int[][] memo = new int[nums.length][2001];
        for (int[] row: memo) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }
        return calculate(nums, 0, 0, target, memo);
    }

    public int calculate(int[] nums, int i, int sum, int S, int[][] memo) {
        if (i == nums.length) {
            if (sum == S)
                return 1;
            else
                return 0;
        } else {
            if (memo[i][sum + 1000] != Integer.MIN_VALUE) {
                return memo[i][sum + 1000];
            }
            int add = calculate(nums, i + 1, sum + nums[i], S, memo);
            int subtract = calculate(nums, i + 1, sum - nums[i], S, memo);
            memo[i][sum + 1000] = add + subtract;
            return memo[i][sum + 1000];
        }
    }

    // Method 1: dfs Brute Force
    // Time O(2^n)
    // Space O(n)
    public int waysToTargetSum(int[] array, int target) {
        int[] num = new  int[] {0};
        int sum = 0;
        dfs(array, 0, sum, num, target);
        return num[0];
    }

    private void dfs(int[] array, int i, int sum, int[] num, int target) {
        if (i == array.length) {
            if (sum == target) {
                num[0]++;
            }
            return;
        }
        // +
        target += array[i];
        dfs(array, i + 1, sum, num, target);
        target -= array[i];
        // -
        target -= array[i];
        dfs(array, i + 1, sum, num, target);
        target += array[i];
    }
}

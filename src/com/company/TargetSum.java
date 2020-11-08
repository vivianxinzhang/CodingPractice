package com.company;
import java.util.*;

public class TargetSum {
    public static void main(String[] args) {
        TargetSum s = new TargetSum();
        int[] array = new int[] {1, 0};
        System.out.println(s.waysToTargetSum(array, 1));
        System.out.println(s.waysToTargetSumI(array, 1));
        System.out.println(s.waysToTargetSumIII(array, 1));
    }

    // Method 3: 2D Dynamic Programming
    // Time O(n) <- O(l*n) l is range of sum
    // Space O(l) <- O(n * l) l is range of sum
    public int waysToTargetSumI(int[] nums, int target) {
        int sum = 0;
        for (int num : nums)
            sum += num;
        if (sum < target) return 0;
        int kOffset = sum;
        int kMaxN = sum * 2 + 1;   // 所有sum 取值的可能性
        int[] ways = new int[kMaxN];
        ways[kOffset] = 1;   // 使用前0个元素能够构成0的可能性是1种
        for (int num : nums) {
            int[] tmp = new int[kMaxN];
            for (int j = num; j < kMaxN - num; j++) {
                // 使用前i-1个元素 构成j的可能性是ways[j]种
                // 使用前i个元素 构成j+num[i]的可能性是ways[j]种
                tmp[j + num] += ways[j];
                // 使用前i个元素 构成j-num[i]的可能性是ways[j]种
                tmp[j - num] += ways[j];
            }
            ways = tmp;
        }
        return ways[target + kOffset];
    }

    // Method 3: 2D Dynamic Programming
    // Time O(n) <- O(l*n) l is range of sum
    // Space O(l) <- O(n * l) l is range of sum
    public int waysToTargetSumIII(int[] nums, int target) {
        int sum = 0;
        for (int num : nums)
            sum += num;
        if (sum < target) return 0;
        int kOffset = sum;
        int kMaxN = sum * 2 + 1;   // 所有sum 取值的可能性
        int[][] M = new int[nums.length][kMaxN];
        M[0][nums[0] + kOffset] = 1;
        for (int i = 1; i < nums.length; i++) {
            int[] tmp = new int[kMaxN];
            // j的取值范围
            // 防止 j-nums[i] 和 j+nums[i] 越界
            for (int j = nums[i]; j < kMaxN - nums[i]; j++) {
                // 使用前i-1个元素 构成j的可能性是ways[j]种
                // 使用前i个元素 构成j+num[i]的可能性是ways[j]种
                M[i][j + nums[i]] += M[i - 1][j];
                // 使用前i个元素 构成j-num[i]的可能性是ways[j]种
                M[i][j - nums[i]] += M[i - 1][j];
            }
        }
        return M[nums.length - 1][target + kOffset];
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

package com.company;

public class BurstBalloons {
    public static void main(String[] args) {
        BurstBalloons s = new BurstBalloons();

        int[] array = new int[] {4, 1, 3, 0};
        System.out.println(s.maxCoins(array));    // 28

        array = new int[] {50, 57, 64, 81, 56, 43, 73, 62, 89, 60, 85, 47};
        System.out.println(s.maxCoins(array));    // 3697768
    }

    // Note:
    // 1. You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
    // 2. 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
    // M[i][j] = maxCoins(nums[i]~nums[j])
    // return M[1][n]
    // M[i][j] = max(M[i][k-1] + nums[i-1]*nums[k]*nums[j+1] + M[k+1][j])   i<=k<=j
    //                            M[i][j]
    //        _________________________________________________
    //       /                                                 \
    // i-1   i   i+1     ...    k-1   k   k+1     ...     j-1   j   j+1
    //       \___________________/         \____________________/
    //               M[i][k-1]                  M[k+1][j]
    // Time O(n^3)
    // Space O(n)
    public int maxCoins(int[] array) {
        // padding array with 1s on both ends
        int[] nums = new int[array.length + 2];
        nums[0] = 1;
        nums[nums.length - 1] = 1;
        for (int i = 0; i < array.length; i++) {
            nums[i + 1] = array[i];
        }
        int[][] M = new int[nums.length][nums.length];
        for (int window = 1; window <= array.length; window++) {
            for (int left = 1; left + window < nums.length; left++) {
                int right = left + window - 1;
                for (int k = left; k <= right; k++) {   // k is the last balloon to burst between [i,j]
                    M[left][right] = Math.max(M[left][right],
                            M[left][k - 1] + nums[left - 1] * nums[k] * nums[right + 1] + M[k + 1][right]);
                }
            }
        }
        return M[1][array.length];
    }

    // another implementation of dp
    // Time O(n^3)
    // Space O(n)
    public int maxCoinsII(int[] nums) {
        int[] array = new int[nums.length + 2];
        array[0] = 1;
        array[array.length - 1] = 1;
        for (int i = 0; i < nums.length; i++) {
            array[i + 1] = nums[i];
        }
        int[][] M = new int[array.length][array.length];
        for (int right = 1; right < array.length - 1; right++) {
            for (int left = right; left > 0; left--) {
                for (int k = left; k <= right; k++) {
                    M[left][right] = Math.max(M[left][right], M[left][k - 1] + M[k + 1][right] + array[left - 1] * array[k] * array[right + 1]);
                }
            }
        }
        return M[1][array.length - 2];
    }

    public int maxCoinsI(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[][] f = new int[n][n];
        for (int j = 0; j < n; j++) {
            for (int i = j; i >= 0; i--) {
                f[i][j] = Integer.MIN_VALUE;
                for (int k = i; k <= j; k++) { // k 是最后一个扎破的气球
                    int left = i == 0 ? 1 : nums[i - 1];
                    int right = j == n - 1 ? 1 : nums[j + 1];
                    int left_sum = k == i ? 0 : f[i][k - 1];
                    int right_sum = k == j ? 0 : f[k + 1][j];
                    f[i][j] = Math.max(f[i][j], left_sum + left * nums[k] * right + right_sum);
                }
            }
        }
        return f[0][n-1];
    }
}

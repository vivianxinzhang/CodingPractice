package com.company;

public class CanIWinEatingPizzaII {
    public static void main(String[] args) {
        CanIWinEatingPizzaII s = new CanIWinEatingPizzaII();
        int[] array = new int[]{2, 1, 100, 3};
        System.out.println(s.canWin(array));    // 102
    }

    // Assumption:
    // If during your friend's turn, the leftmost pizza has the same size as the rightmost pizza, he will pick the rightmost one.
    // M[i][j] represents [from the ith pizza to the jth pizza] the largest total sum of all pizza you can pick assuming you start first.
    // Time O(n^2)
    // Space O(n^2)
    public int canWin(int[] nums) {
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
        // M[i][j] = max { case 1: if we take the left pizza
        //	                        input[i] + M[i+2][j]		if input[i+1] > input[j]
        //			                input[i] + M[i+1][j-1]		if input[i+1] < input[j]
        //	               case 2: if we take the right pizza
        //	                        input[j] + M[i+1][j-1]		if input[i] > input[j-1]
        //			                input[j] + M[i][j-2]		if input[i] < input[j-1]}
        // M[i][j] = max{case1, case2}
        // 枚举所有的 subproblem 问题规模, 从小到大
        for (int length = 3; length <= nums.length; length++) {
            // 枚举所有可能的起点
            for (int i = 0; i <= nums.length - length; i++) {
                int j = i + length - 1;
                // Case 1: if I eat i, my friend will eat either i+1 or j, whichever is bigger
                int eatLeftRes = nums[i + 1] > nums[j] ? nums[i] + M[i + 2][j] : nums[i] + M[i + 1][j - 1];
                // Case 2: if I eat j, my friend will eat either i or j-1, whichever is bigger
                int eaRightRes = nums[i] > nums[j - 1] ? nums[j] + M[i + 1][j - 1] : nums[j] + M[i][j - 2];
                M[i][j] = Math.max(eatLeftRes, eaRightRes);
            }
        }
        return M[0][nums.length -1];
    }
}

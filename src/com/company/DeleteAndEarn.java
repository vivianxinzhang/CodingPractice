package com.company;

public class DeleteAndEarn {
    public static void main(String[] args) {
        DeleteAndEarn s = new DeleteAndEarn();
        int[] array = new int[] {1, 2, 3};
        System.out.println(s.deleteAndEarn(array));     // 4

        array = new int[] {2, 3, 4, 4};
        System.out.println(s.deleteAndEarn(array));     // 10
    }

    // Assumptions:
    // 1. Originally your total score is 0.
    // 2. The length of given array is no more than 20000.
    // 3. Each number in the given integer array is in range of [1, 10000].
    // Key Observations: if we take nums[i]
    // 1. we can safely take all of its copies
    // 2. we cannot take any of copies of nums[i - 1] and nums[i + 1]
    // This problem is reduced to House Robber
    // House[i] has all the copies of num whose value is i
    // [3, 4, 2]  -> rob[0, 2, 3, 4] = 6
    // [2, 2, 3, 3, 3, 4]  -> rob[0, 2*2, 3*3, 4] = rob[0, 4, 9, 4] = 9
    // Time O(n+r)   reduction O(n) + solving rob = O(r)   r = range of nums
    // Space O(r)
    public int deleteAndEarn(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = findMax(nums);
        int[] array = new int[max + 1];
        for (int num : nums) {
            array[num] += num;
        }
        return rob(array);
    }

    private int findMax(int[] array) {
        int max = array[0];
        for (int num : array) {
            max = Math.max(max, num);
        }
        return max;
    }

    public int rob(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int n = array.length;
        int rob = 0;
        int notRob = 0;
        for (int i = 0; i < n; i++) {
            int robTmp = rob;
            int notRobTmp = notRob;
            rob = array[i] + notRobTmp;
            notRob = Math.max(robTmp, notRobTmp);
        }
        return Math.max(rob, notRob);
    }
}

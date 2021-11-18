package com.company;

public class HouseRobberII {
    public static void main(String[] args) {
        HouseRobberII s = new HouseRobberII();

        int[]  array = new int[] {5};
        System.out.println(s.rob(array));   // 5
        array = new int[] {1, 5, 6, 7};
        System.out.println(s.rob(array));   // 12
        array = new int[] {11, 6, 5, 4, 3, 10, 5};
        System.out.println(s.rob(array));   // 26
        array = new int[] {11, 12, 5, 8, 3, 10, 5, 7, 13, 8};
        System.out.println(s.rob(array));   // 45
    }

    // Method 2: dp
    // avoid rob both 0 and n-1 at the same time
    // Case 1: not rob house 0, dp between [1, n-1]
    //         0  [1       ...     n-1]
    //         N  [        ...     n-1]
    // Case 2: not rob house n-1, dp between [0, n-2]
    //         [0      ...    n-2]  n-1
    //         [N      ...    n-2]  n-1
    // Time O(n)
    // Space O(1)
    public int rob(int[] array) {
        // base case:
        if (array == null || array.length == 0) {
            return 0;
        }
        int n = array.length;
        if (n == 1) {
            return array[0];
        }
        // Case 1: not rob house 0, dp between [1, n-1]
        //         0  [1       ...     n-1]
        //         N  [        ...     n-1]
        int notRobStartRes = robHelper(array, 1, n - 1);
        // Case 2: not rob house n-1, dp between [0, n-2]
        //         [0      ...    n-2]  n-1
        //         [N      ...    n-2]  n-1
        int notRobEndRes = robHelper(array, 0, n - 2);
        return Math.max(notRobStartRes, notRobEndRes);
    }

    private int robHelper(int[] array, int start, int end) {
        // base case:
        int rob = array[start];
        int noRob = 0;
        // induction rule:
        for (int i = start + 1; i <= end; i++) {
            int robTmp = rob;
            int noRobTmp = noRob;
            rob = noRobTmp + array[i];
            noRob = Math.max(robTmp, noRobTmp);
        }
        return Math.max(rob, noRob);
    }

    // Method 1: dfs
    // dfs has n levels, for each house there are two options
    // not rob - all houses has the option to not rob
    // rob - case 1: index 0,
    //       case 2: index [1, n - 2] and not rob previous
    //       case 3: index n - 1, not rob previous and not not rob 0
    // Time O(2^n)
    // Space O(n)
    public int robI(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        boolean[] status = new boolean[nums.length];
        int[] max = new int[1];
        dfs(nums, 0, 0, status, max);
        return max[0];
    }

    private void dfs(int[] num, int index, int curMax, boolean[] status, int[] max) {
        if (index == num.length) {
            max[0] = Math.max(max[0], curMax);
            return;
        }
        // not rob at index
        status[index] = false;
        dfs(num, index + 1, curMax, status, max);
        // rob at index
        // case 1: not the last house
        if ((index == 0) || (index < num.length - 1 &&status[index - 1] == false)) {
            status[index] = true;
            dfs(num, index + 1, curMax + num[index], status, max);
        }
        // case 2: last house, need to consider adjacent index 0
        if (index == num.length - 1 && (status[index - 1] == false && status[0] == false)) {
            dfs(num, index + 1, curMax + num[index], status, max);
        }
    }
}

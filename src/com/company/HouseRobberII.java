package com.company;

public class HouseRobberII {
    public static void main(String[] args) {
        HouseRobberII s = new HouseRobberII();
        int[]  array = new int[] {1, 5, 6};
        System.out.println(s.rob(array));   // 6

        array = new int[] {1, 5, 6, 7};
        System.out.println(s.rob(array));   // 12

        array = new int[] {11, 6, 5, 4, 3, 10, 5};
        System.out.println(s.rob(array));   // 26

        array = new int[] {11, 12, 5, 8, 3, 10, 5, 7, 13, 8};
        System.out.println(s.rob(array));   // 45
    }

    // Method 1:
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
        // base case:
        int rob = array[1];
        int noRob = 0;
        // induction rule:
        for (int i = 2; i < n; i++) {
            int robTmp = rob;
            int noRobTmp = noRob;
            rob = noRobTmp + array[i];
            noRob = Math.max(robTmp, noRobTmp);
        }
        int notRobStartRes = Math.max(rob, noRob);
        // Case 2: not rob house n-1, dp between [0, n-2]
        //         [0      ...    n-2]  n-1
        //         [N      ...    n-2]  n-1
        rob = array[0];
        noRob = 0;
        // induction rule:
        for (int i = 1; i < array.length - 1; i++) {
            int robTmp = rob;
            int noRobTmp = noRob;
            rob = noRobTmp + array[i];
            noRob = Math.max(robTmp, noRobTmp);
        }
        int notRobEndRes = Math.max(rob, noRob);
        return Math.max(notRobStartRes, notRobEndRes);
    }
}

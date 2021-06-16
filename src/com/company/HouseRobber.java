package com.company;

public class HouseRobber {
    public static void main(String[] args) {
        HouseRobber s = new HouseRobber();
        int[] array = new int[] {1, 5, 6, 7};
        System.out.println(s.rob(array));   // 12

        array = new int[] {11, 12, 5, 8, 3, 10, 5, 7, 13, 8};
        System.out.println(s.rob(array));   // 45
    }

    // Method 3: optimize space
    // Time O(n)
    // Space O(1)
    public int rob(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int n = array.length;
        int rob = 0;
        int noRob = 0;
        for (int i = 0; i < n; i++) {
            int robTmp = rob;
            int notRobTmp = noRob;
            rob = array[i] + notRobTmp;
            noRob = Math.max(robTmp, notRobTmp);
        }
        return Math.max(rob, noRob);
    }

    // Method 2:
    // M[i] represents maximum amount of gold you can rob without being caught between [0, i]
    // Time O(n)
    // Space O(n)
    public int robII(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        if (array.length == 1) {
            return array[0];
        }
        if (array.length == 2) {
            return Math.max(array[0], array[1]);
        }
        int[] M = new int[array.length];
        M[0] = array[0];
        M[1] = Math.max(array[0], array[1]);
        for (int i = 2; i < array.length; i++) {
            M[i] = Math.max(M[i - 1], M[i - 2] + array[i]);
        }
        return M[array.length - 1];
    }

    // Method 1:
    // rob[i] represents maximum amount of gold you can rob if rob house i
    // rob[i] = array[i] + notRob[i - 1];
    // noRob[i] represents maximum amount of gold you can rob if not rob house i
    // notRob[i] = Math.max(rob[i - 1], notRob[i - 1]);
    // Time O(n)
    // Space O(n)
    public int robI(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int n = array.length;
        int[] rob = new int[n];
        int[] notRob = new int[n];
        rob[0] = array[0];
        notRob[0] = 0;
        for (int i = 1; i < n; i++) {
            rob[i] = array[i] + notRob[i - 1];
            notRob[i] = Math.max(rob[i - 1], notRob[i - 1]);
        }
        return Math.max(rob[n - 1], notRob[n - 1]);
    }
}

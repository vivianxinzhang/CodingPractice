package com.company;

public class HouseRobberII {
    public static void main(String[] args) {
        HouseRobberII s = new HouseRobberII();
        int[] array = new int[] {1, 5, 6, 7};
        System.out.println(s.rob(array));   // 12

        array = new int[] {11, 12, 5, 8, 3, 10, 5, 7, 13, 8};
        System.out.println(s.rob(array));   // 45
    }

    // M[i] represents maximum amount of gold you can rob without being caught between [0, i]
    // Time O(n)
    // Space O(n)
    public int rob(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        if (array.length == 1) {
            return array[0];
        }
        if (array.length == 2) {
            return Math.max(array[0], array[1]);
        }
        return 0;
    }
}

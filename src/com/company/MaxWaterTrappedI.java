package com.company;

public class MaxWaterTrappedI {
    public static void main(String[] args) {
        MaxWaterTrappedI s = new MaxWaterTrappedI();
        int[] array = new int[] {2, 1, 3, 2, 4};
        System.out.println(s.maxTrapped(array));

        array = new int[] {2, 1, 3, 4, 5, 2, 6};
        System.out.println(s.maxTrapped(array));
    }

    // Method 3: Optimized solution
    // Time O(n)
    // Space O(1)
    // Assumptions: The given array is not null
    public int maxTrapped(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        int left = 0;
        int right = array.length - 1;
        int result = 0;
        int lmax = array[left];
        int rmax = array[right];
        while (left < right) {
            if (array[left] <= array[right]) {
                result += Math.max(0, lmax - array[left]);
                lmax = Math.max(lmax, array[left]);
                left++;
            } else {
                result += Math.max(0, rmax - array[right]);
                rmax = Math.max(rmax, array[right]);
                right--;
            }
        }
        return result;
    }

    // Method 2: DP


    // Method 1: 中心开花

}

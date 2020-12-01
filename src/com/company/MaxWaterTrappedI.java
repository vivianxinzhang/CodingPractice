package com.company;

public class MaxWaterTrappedI {
    public static void main(String[] args) {
        MaxWaterTrappedI s = new MaxWaterTrappedI();
        int[] array = new int[]{2, 1, 3, 4, 5, 2, 6};   // 4
        System.out.println(s.maxTrapped(array));
        System.out.println(s.maxTrappedI(array));
        System.out.println(s.maxTrappedII(array));

        array = new int[]{2, 1, 3, 2, 4};   // 2
        System.out.println(s.maxTrapped(array));
        System.out.println(s.maxTrappedI(array));
        System.out.println(s.maxTrappedII(array));
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
    public int maxTrappedII(int[] array) {
        // Step 1: pre-processing
        int[] leftMax = fillLeftMax(array);
        int[] rightMax = fillRightMax(array);
        // Step 2: find water level for each index, and calculate total
        int maxWaterTrapped = 0;
        for (int i = 0; i < array.length; i++) {
            maxWaterTrapped += (Math.min(leftMax[i], rightMax[i]) - array[i]);
        }
        return maxWaterTrapped;
    }

    private int[] fillRightMax(int[] array) {
        int[] M = new int[array.length];
        M[array.length - 1] = array[array.length - 1];
        for (int i = M.length - 2; i >= 0; i--) {
            M[i] = array[i] > M[i + 1] ? array[i] : M[i + 1];
        }
        return M;
    }

    private int[] fillLeftMax(int[] array) {
        int[] M = new int[array.length];
        M[0] = array[0];
        for (int i = 1; i < M.length; i++) {
            M[i] = array[i] > M[i - 1] ? array[i] : M[i - 1];
        }
        return M;
    }

    // Method 1: 中心开花
    // for each index i {
    //    try using myself as the top bound
    //    中心开花，go left find max height, go right find max height,
    //    min(leftMaxHeight, rightMaxHeight) is the maximum water level for current index
    // Time = O(n^2)
    // Extra space = O(1)
    public int maxTrappedI(int[] array) {
        int maxWaterTrapped = 0;
        for (int i = 0; i < array.length; i++) {
            int leftMaxHeight = findLeftMaxHeight(array, i);
            int rightMaxHeight = findRightMaxHeight(array, i);
            maxWaterTrapped += (Math.min(leftMaxHeight, rightMaxHeight) - array[i]);
        }
        return maxWaterTrapped;
    }

    private int findLeftMaxHeight(int[] array, int i) {
        int maxHeight = array[i];
        for (int k = i; k >= 0; k--) {
            maxHeight = array[k] > maxHeight ? array[k] : maxHeight;
        }
        return maxHeight;
    }

    private int findRightMaxHeight(int[] array, int i) {
        int maxHeight = array[i];
        for (int k = i; k < array.length; k++) {
            maxHeight = array[k] > maxHeight ? array[k] : maxHeight;
        }
        return maxHeight;
    }
}

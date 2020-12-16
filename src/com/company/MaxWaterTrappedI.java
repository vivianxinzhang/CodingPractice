package com.company;

public class MaxWaterTrappedI {
    public static void main(String[] args) {
        MaxWaterTrappedI s = new MaxWaterTrappedI();
        int[] array = new int[]{3, 1, 3};   // 2
        System.out.println(s.maxTrapped(array));
        System.out.println(s.maxTrappedI(array));
        System.out.println(s.maxTrappedII(array));
        System.out.println(s.maxTrappedIII(array));

        array = new int[]{2, 1, 3, 4, 5, 2, 6};   // 4
        System.out.println(s.maxTrapped(array));
        System.out.println(s.maxTrappedI(array));
        System.out.println(s.maxTrappedII(array));
        System.out.println(s.maxTrappedIII(array));

        array = new int[]{2, 1, 3, 2, 4};   // 2
        System.out.println(s.maxTrapped(array));
        System.out.println(s.maxTrappedI(array));
        System.out.println(s.maxTrappedII(array));
        System.out.println(s.maxTrappedIII(array));
    }

    // Assumptions: The given array is not null
    // Method 3: Optimized solution
    // initialize:
    // left = 0;
    // right = n - 1;
    // leftMax = 0;
    // rightMax = 0;
    // sum = 0;
    // each round:
    // update leftMax and rightMax
    // if leftMax < rightMax, calculate water trapped on leftIdx, then left++;
    // else                 , calculate water trapped on rightIdx, then righ--;
    // Time O(n)
    // Space O(1)
    public int maxTrapped(int[] A) {
        if (A.length == 0) {
            return 0;
        }
        int left = 0;
        int right = A.length - 1;
        int max = 0;
        int leftMax = 0;
        int rightMax = 0;
        // [left, right] unexplored area
        while (left <= right) {     // left < right is also ok, the last index in the range must be the hightest, cannot trap any water
            leftMax = Math.max(leftMax, A[left]);      // M[1] 从左往右
            rightMax = Math.max(rightMax, A[right]);    // M[2] 从右往左
            if (leftMax < rightMax) {
                max += (leftMax - A[left]);
                left++;
            } else {
                max += (rightMax - A[right]);
                right--;
            }
        }
        return max;
    }

    // Case 1: if leftMax < rightMax, we already know that the water stored in left can be calculated safely. so i++;
    //      Case 1.1: A[i+1] <= left_max, then left_max is not updated, left border is still valid and does not change, so water at i + 1 == left_max - A[i+1];
    //      Case 1.2: A[i+1] > left_max, then left max is updated to A[i+1], water at i + 1 == new_left_max - A[i+1] = A[i+1]  - A[i+1]  = 0;
    // Case 2: else, j--;
    public int maxTrappedIII(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        int left = 0;
        int right = array.length - 1;
        int result = 0;
        int lmax = array[left];
        int rmax = array[right];
        while (left <= right) {
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
    // Time O(n)
    // Space O(n)
    public int maxTrappedII(int[] array) {
        // Step 1: pre-processing - O(n)
        int[] leftMax = findLeftMax(array);
        int[] rightMax = findRightMax(array);
        // Step 2: find water level for each index, and calculate total - O(n)
        int maxWaterTrapped = 0;
        for (int i = 0; i < array.length; i++) {
            maxWaterTrapped += (Math.min(leftMax[i], rightMax[i]) - array[i]);
        }
        return maxWaterTrapped;
    }

    private int[] findRightMax(int[] array) {
        int[] M = new int[array.length];
        // M[i] represents the maxHeight in [i, n - 1]
        M[array.length - 1] = array[array.length - 1];
        // induction rule:
        // M[i] = array[i]    if array[i] > M[i+1]
        //      = M[i+1]      otherwise
        for (int i = M.length - 2; i >= 0; i--) {
            M[i] = array[i] > M[i + 1] ? array[i] : M[i + 1];
        }
        return M;
    }

    private int[] findLeftMax(int[] array) {
        int[] M = new int[array.length];
        // M[i] represents the maxHeight in [0, i]
        // base case:
        M[0] = array[0];
        // induction rule:
        // M[i] = array[i]    if array[i] > M[i-1]
        //      = M[i-1]      otherwise
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
    // Space = O(1)
    public int maxTrappedI(int[] array) {
        int maxWaterTrapped = 0;
        for (int i = 0; i < array.length; i++) {
            // find left border, highest on the left
            int leftMaxHeight = findMaxHeight(array, 0, i);
            // find right border, highest on the right
            int rightMaxHeight = findMaxHeight(array, i, array.length - 1);
            int currHeight = array[i];
            // Math.min(leftMaxHeight, rightMaxHeight) is water level on current index
            int waterTrappedOnCurrIndex = Math.min(leftMaxHeight, rightMaxHeight) - currHeight;
            maxWaterTrapped += waterTrappedOnCurrIndex;
        }
        return maxWaterTrapped;
    }

    private int findMaxHeight(int[] array, int left, int right) {
        int max = array[left];
        for (int i = left + 1; i <= right; i++) {
            max = Math.max(max, array[i]);
        }
        return max;
    }
}

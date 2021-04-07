package com.company;

public class BuyStockIII {
    public static void main(String[] args) {
        BuyStockIII s = new BuyStockIII();
        int[] array = new int[] {3, 4, 1, 2, 6, 2, 3, 5, 1, 7, 3, 8};
        System.out.println(s.maxProfit(array));
        // (4 - 3) + (7 - 1) = 12

        array = new int[] {3, 4, 1, 2, 7, 6};
        System.out.println(s.maxProfit(array));
        // (4 - 3) + (7 - 1) = 7

        array = new int[] {2, 3, 2, 1, 4, 5, 2, 11};
        System.out.println(s.maxProfit(array));
        // (5 - 1) + (11 - 2) = 13
    }

    // Assumptions:
    // The give array is not null and is length of at least 2
    // Method 1: enumerate all divider positions, and sum up profit of at at most 1 transaction
    // on left of divider and on right of divider
    // Step 1:
    // preprocessing: calculate max profit of at most 1 transaction on left of each index i
    // and on right of each index i
    // Step 2:
    // enumerate all the starting index of right part [0, prices.length - 1] as divider
    // sum left[i-1] with right[i]
    // Time O(n)
    // Space O(n)
    public int maxProfit(int[] array) {
        if (array == null || array.length < 2) {
            return 0;
        }
        int[] leftMaxProfit = getLeftMax(array);
        int[] rightMaxProfit = getRightMax(array);
        int maxProfit = 0;
        for (int i = 0; i < array.length; i++) {
            int currMax = getNumber(leftMaxProfit, i - 1) + getNumber(rightMaxProfit, i);
            maxProfit = Math.max(maxProfit, currMax);
        }
        return maxProfit;
    }

    // from left to right, maintain lowest price
    // M[i] represents maximum profit from [0, i]
    private int[] getLeftMax(int[] array) {
        int lowest = array[0];
        int[] M = new int[array.length];
        for (int i = 1; i < array.length; i++) {
            M[i] = Math.max(M[i - 1], array[i] - lowest);
            lowest = Math.min(lowest, array[i]);
        }
        return M;
    }

    // from right to left, maintain highest price
    // M[i] represents maximum profit from [i, n-1]
    private int[] getRightMax(int[] array) {
        int highest = array[array.length - 1];
        int[] M = new int[array.length];
        for (int i = array.length - 2; i >= 0; i--) {
            M[i] = Math.max(M[i + 1], highest - array[i]);
            highest = Math.max(highest, array[i]);
        }
        return M;
    }

    private int getNumber(int[] M, int i) {
        if (i < 0 || i >= M.length) {
            return 0;
        }
        return M[i];
    }
}

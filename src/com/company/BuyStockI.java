package com.company;

public class BuyStockI {
    public static void main(String[] args) {
        BuyStockI s = new BuyStockI();
        int[] array = new int[] {2, 3, 2, 1, 4, 5};
        System.out.println(s.maxProfit(array)); // 4
        System.out.println(s.maxProfitI(array)); // 4
        System.out.println(s.maxProfitII(array)); // 4
        System.out.println(s.maxProfitIII(array)); // 4
    }

    // Method 3: dp
    // compute the gain of each day
    // [7, 1, 5,  3, 6, 4]
    //   [-6, 4, -2, 3, -2]
    // Step 1: pre-processing to compute gain of one unit of stock if hold for 1 day
    // Step 2: maximum subarray sum
    // M[i] represents maximum subarray sum up to i
    // M[i] = Math.max(gain[i], P[i-1] + gain[i])
    // Time O(n)
    // Space O(n)
    public int maxProfit(int[] array) {
        if (array == null || array.length < 2) {
            return 0;
        }
        // dailyProfits[i] represents profit can get to buy stock on day i - 1 and sell on day i
        int[] dailyProfits = new int[array.length];
        for (int i = 1; i < array.length; i++) {
            dailyProfits[i] = array[i] - array[i - 1];
        }
        return maxSubarraySum(dailyProfits);
    }

    private int maxSubarraySum(int[] array) {
        int[] M = new int[array.length];
        M[0] = array[0];    // array[0] = 0: represents不操作 profit为0
        int max = M[0];
        for (int i = 1; i < array.length; i++) {
            if (M[i - 1] > 0) {
                M[i] = M[i - 1] + array[i];
            } else {
                M[i] = array[i];
            }
            max = Math.max(max, M[i]);
        }
        return max;
    }

    // Method 2: dp
    // Observation:
    // Buy: prices[i]: min{prices[k]}, k <= i
    // Sell: prices[j]: max{prices[k]}, k >= j
    // keep tracking the min price so far
    // L[i]: lowest price up to the i-th day
    // P[i]; max profit up to the i-th day
    // P[i] = max(P[i-1], price[i] - L[i])
    // buy at the lowest price and sell at i-th day
    // Time O(n)
    // Space O(n) -> O(1)
    public int maxProfitII(int[] array) {
        // lowest is the minimum price between [0, i-1]
        int[] lowest = new int[array.length];
        lowest[0] = array[0];
        // M[i] represents maximum profit between [0, i]
        int[] M = new int[array.length];
        for (int i = 1; i < array.length; i++) {
            lowest[i] = Math.min(lowest[i - 1], array[i]);
            M[i] = Math.max(M[i - 1], array[i] - lowest[i - 1]);
            // array[i] - lowest[i - 1] means buy at the lowest between [0, i-1]
            // and sell at day i
        }
        return M[array.length - 1];
    }

    // optimize space
    public int maxProfitIII(int[] array) {
        // lowest is the minimum price between [0, i-1]
        int lowest = array[0];
        int maxProfit = 0;
        for (int i = 1; i < array.length; i++) {
            maxProfit = Math.max(maxProfit, array[i] - lowest);
            lowest = Math.min(lowest, array[i]);
        }
        return maxProfit;
    }

        // Method 1: brute force
    // enumerate all buy and sell time, and compute gain
    // Time O(n^2)
    // Space O(1)
    public int maxProfitI(int[] array) {
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                int currProfit = array[j] - array[i];
                max = Math.max(max, currProfit);
            }
        }
        return max;
    }
}

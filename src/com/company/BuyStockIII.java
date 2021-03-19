package com.company;

public class BuyStockIII {
    public static void main(String[] args) {
        BuyStockIII s = new BuyStockIII();
        int[] array = new int[] {2, 3, 2, 1, 4, 5, 2, 11};
        System.out.println(s.maxProfit(array)); // (5 - 1) + (11 - 2) = 13
    }

    // DP
    // Step 1: preprocessing: calculate max profit of at most 1 transaction on left of each index i
    //         and on right of each index i
    // Step 2: enumerate all the starting index of right part [1, prices.length - 1] as divider
    //         sum left[i-1] with right[i]
    // Time O(n)
    // Space O(n)
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int[] left = getLeftMaxProfit(prices);
        int[] right = getRightMaxProfit(prices);
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
           int curr = left[i - 1] + right[i];
           max = Math.max(curr, max);
        }
        return max;
    }

    private int[] getRightMaxProfit(int[] array) {
        int highest = array[array.length - 1];
        int[] M = new int[array.length];
        for (int i = array.length - 2; i >= 0; i--) {
            M[i] = Math.max(M[i + 1], highest - array[i]);
            highest = Math.max(highest, array[i]);
        }
        return M;
    }

    private int[] getLeftMaxProfit(int[] array) {
        int lowest = array[0];
        int[] M = new int[array.length];
        for (int i = 1; i < array.length; i++) {
            M[i] = Math.max(M[i - 1], array[i] - lowest);
            lowest = Math.min(lowest, array[i]);
        }
        return M;
    }

    // insights: keep track of the minimum price of the stock till previous day
    // and record the maximum profit can be obtained in the current day
    // dp[i][j] means:in the ith transaction, the maximum profit from day 1 to day j
    // (including day 1 and day j)
    // base case:
    // dp[0][j] = 0;
    // dp[i][0] = 0;
    // want to know: dp[transactions][prices.length - 1]
    // Time O(n)
    // Space O(n)
    public int maxProfitII(int[] prices) {
        if(prices.length == 0) {
            return 0;
        }
        int trans = 2;
        int dp[][] = new int[trans + 1][prices.length];

        for(int i = 1; i <= trans; i++) {
            int min = Integer.MAX_VALUE;
            for(int j = 1; j < prices.length; j++) {
                min = Math.min(min, prices[j - 1] - dp[i - 1][j - 1]);
                dp[i][j] = Math.max(dp[i][j - 1], prices[j] - min);
            }
        }
        return dp[trans][prices.length - 1];
    }

    // Recursion with Memoization
    // Time O(2^n)
    // Space O(n)
    public int maxProfitI(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int[][][] memo = new int[prices.length][3][2];
        int res = helper(prices, 0, 2, 0, memo);
        return res;
    }

    private int helper(int[] prices, int pos, int transaction, int bought, int[][][] memo) {
        // base case: out of bound or no more transaction to make
        if (pos >= prices.length || transaction == 0) {
            return 0;
        }
        // return if already calculated
        if (memo[pos][transaction][bought] != 0) {
            return memo[pos][transaction][bought];
        }
        // 3 choices for a position: buy/ sell/ skip
        // skip
        int result = helper(prices, pos + 1, transaction, bought, memo);
        // sell
        if (bought == 1) {
            result = Math.max(result, helper(prices, pos + 1, transaction - 1, 0, memo) + prices[pos]);
        } else {
            // buy
            result = Math.max(result, helper(prices, pos + 1,transaction, 1, memo) - prices[pos]);
        }
        memo[pos][transaction][bought] = result;
        return result;
    }
}

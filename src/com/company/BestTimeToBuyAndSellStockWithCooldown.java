package com.company;

public class BestTimeToBuyAndSellStockWithCooldown {
    public static void main(String[] args) {
        BestTimeToBuyAndSellStockWithCooldown s = new BestTimeToBuyAndSellStockWithCooldown();

        int[] array = new int[]{};
        System.out.println(s.maxProfit(array));  // 0

        array = new int[]{1, 2, 3, 0, 2};
        System.out.println(s.maxProfit(array));  // 3
    }

    // Assumption:
    // 1. You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
    // 2. After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
    // hold[i]: maximum profit if hold a stock on day i
    // unhold[i]: maximum profit if unhold a stock on day i
    // Base case:
    // hold[0] = -prices[0]
    // hold[1] = max(-prices[1], -prices[0]) on day one hold a stock, purchased either on day 0 or day 1
    // Induction rule:
    // hold[i] = max(case1, case2)
    // case 1: 第i天买入  unhold[i-2] - prices[i]
    // case 2: 第i天没有买入  unhold[i-2]
    // unhold[i] = max(case1, case2)
    // case 1: 第i天卖出 hold[i-1] + prices[i]
    // case 2: 第i天没有卖出 unhold[i-1]
    // return: unhold[n-1]
    // Time O(n)
    // Space O(n) -> O(1)
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int n = prices.length;
        int[] hold = new int[n];
        int[] unhold = new int[n];
        // base case
        hold[0] = -prices[0];
        for (int i = 1; i < n; i++) {
            if (i == 1) {
                hold[i] = Math.max(-prices[i - 1], -prices[i]);
            } else {
                hold[i] = Math.max(hold[i - 1], unhold[i - 2] - prices[i]);
            }
            unhold[i] = Math.max(unhold[i - 1], hold[i - 1] + prices[i]);
        }
        return unhold[n - 1];
    }

    // Method 3: DP - optimize space
    // Time O(n)
    // Space O(1)
    public int maxProfitIV(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int sold = 0;
        int noStock = 0;
        int inHand = Integer.MIN_VALUE;
        for (int price : prices) {
            int prevSold = sold;
            sold = inHand + price;
            inHand = Math.max(inHand, noStock - price);
            noStock = Math.max(noStock, prevSold);
        }
        return Math.max(noStock, sold);
    }

    // Method 2: DP
    // noStock[i] = max(noStock[i-1], sold[i-1])
    // inHand[i] = max(inHand[i-1], noStock[i-1]-prices[i])
    // sold[i] = inHand[i-1] + prices[i]
    // init: rest[0] = sold[0] = 0, hold[0] = -∞
    // ans: max(res[i], sold[i])
    // Time O(n)
    // Space O(n)
    public int maxProfitIII(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int n = prices.length;
        int[] sold = new int[n];
        int[] noStock = new int[n];
        int[] inHand = new int[n];
        // base case:
        noStock[0] = 0;
        inHand[0] = -prices[0];
        sold[0] = 0;
        // induction rule:
        for (int i = 1; i < prices.length; i++) {
            noStock[i] = Math.max(noStock[i - 1], sold[i - 1]);
            inHand[i] = Math.max(inHand[i - 1], noStock[i - 1] - prices[i]);
            sold[i] = inHand[i - 1] + prices[i];
        }
        return Math.max(noStock[n - 1], sold[n - 1]);
    }

    // Method 1: Recursion + memo
    // recursion has n levels, each level has three options buy / sell / rest
    // for each position can rest / buy / sell
    // what are valid sequences? buy sell rest buy sell
    // 1. can only buy after rest
    // 2. can only sell after buy or after buy and rest
    // 3. can rest after rest
    // Time O(3^n)
    // Space O(n)
    public int maxProfitII(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int[] maxProfit  = new int[prices.length];
        return findMax(prices, 0, maxProfit);
    }

    private int findMax(int[] prices, int index, int[] memo) {
        if (index >= prices.length) {
            return 0;
        }
        if (memo[index] != 0) {
            return memo[index];
        }
        // we have 2 options:
        // 1.We can buy the stock at ith day and findMax from [i+2, n-1] th day
        // now find all the positions where we can sell the stock
        int maxProfit = 0;
        // we can try to sell on the ith day
        for (int i = index + 1; i < prices.length; i++) {
            if (prices[i] > prices[index]) {
                maxProfit = Math.max(maxProfit, prices[i] - prices[index] + findMax(prices, i + 2, memo));
            }
        }
        // 2.We don't sell the stock on ith day
        maxProfit = Math.max(maxProfit, findMax(prices, index + 1, memo));
        memo[index] = maxProfit;
        return maxProfit;
    }

    // Method 1: brute force dfs
    // Time O(3^n)
    // Space O(1)
    public int maxProfitI(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        char[] action = new char[prices.length];
        int[] maxProfit = new int[] {0};
        dfs( action, 0, prices, 0, 0, maxProfit);
        return maxProfit[0];
    }

    private void dfs(char[] action, int index, int[] prices, int sumProfit, int stocks, int[] maxProfit) {
        if (index == action.length) {
            maxProfit[0] = Math.max(maxProfit[0], sumProfit);
            return;
        }
        // buy - 'b'
        if (index == 0 || action[index - 1] == 'r') {
            action[index] = 'b';
            dfs(action, index + 1, prices, sumProfit - prices[index], 1, maxProfit);
        }
        // sell - 's'
        if (stocks == 1) {
            action[index] = 's';
            dfs(action, index + 1, prices, sumProfit + prices[index], 0, maxProfit);
        }
        // hold/ no action - 'h'
        action[index] = 'h';
        dfs(action, index + 1, prices, sumProfit, stocks, maxProfit);
    }
}

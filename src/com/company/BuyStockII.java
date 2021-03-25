package com.company;

public class BuyStockII {
    public static void main(String[] args) {
        BuyStockII s = new BuyStockII();
        int[] array = new int[] {2, 3, 2, 1, 4, 5};
        System.out.println(s.maxProfit(array)); // 5
        System.out.println(s.maxProfitI(array)); // 5
        System.out.println(s.maxProfitII(array)); // 5
    }

    // Assumptions:
    // The give array is not null and is length of at least 2
    // Method 2: optimize space
    // Time O(n)
    // Space O(1)
    public int maxProfit(int[] array) {
        if (array == null || array.length < 2) {
            return 0;
        }
        int maxProfit = 0;
        for (int i = 1; i < array.length; i++) {
            int dailyProfit = array[i] - array[i - 1];
            // sum up profits valley and peak
            if (dailyProfit > 0) {
                maxProfit += dailyProfit;
            }
        }
        return maxProfit;
    }

    // Time O(n)
    // Space O(1)
    public int maxProfitI(int[] array) {
        if (array == null || array.length < 2) {
            return 0;
        }
        int maxProfit = 0;
        for (int i = 1; i < array.length; i++) {
            // price increase, add to total profit
            if (array[i] > array[i - 1]) {
                int dailyProfit = array[i] - array[i - 1];
                maxProfit += dailyProfit;
            }
        }
        return maxProfit;
    }

    // Method 1:
    // maxProfit is sum of all positive daily profit
    // Step 1: pre-processing to compute gain of one unit of stock if hold for 1 day
    // Step 2: sum all positive profits
    // Time O(n)
    // Space O(1)
    public int maxProfitII(int[] array) {
        // dailyProfits[i] represents profit can get to buy stock on day i - 1 and sell on day i
        int[] dailyProfits = new int[array.length];
        int maxProfit = 0;
        for (int i = 1; i < array.length; i++) {
            dailyProfits[i] = array[i] - array[i - 1];
            if (dailyProfits[i] > 0) {
                maxProfit += dailyProfits[i];
            }
        }
        return maxProfit;
    }
}

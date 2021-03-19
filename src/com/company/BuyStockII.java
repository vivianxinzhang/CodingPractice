package com.company;

public class BuyStockII {
    public static void main(String[] args) {
        BuyStockII s = new BuyStockII();
        int[] array = new int[] {2, 3, 2, 1, 4, 5};
        System.out.println(s.maxProfit(array)); // 4
        System.out.println(s.maxProfitI(array)); // 4
    }

    // Step 1: pre-processing to compute gain of one unit of stock if hold for 1 day
    // Step 2: sum all positive profits
    // Time O(n)
    // Space O(1)
    public int maxProfit(int[] array) {
        // dailyProfits[i] represents profit can get to buy stock on day i - 1 and sell on day i
        int[] dailyProfits = new int[array.length];
        int sum = 0;
        for (int i = 1; i < array.length; i++) {
            dailyProfits[i] = array[i] - array[i - 1];
            sum = dailyProfits[i] + dailyProfits[i];
        }
        return sum;
    }

    // Method 1: optimize space
    // Time O(n)
    // Space O(1)
    public int maxProfitI(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int totalProfit = 0;
        for (int i = 1; i < array.length; i++) {
            int dailyProfit = array[i] - array[i - 1];
            // sum up profits between peak and valley
            if (dailyProfit > 0) {
                totalProfit += dailyProfit;
            }
        }
        return totalProfit;
    }
}

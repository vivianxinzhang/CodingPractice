package com.company;

import java.util.Arrays;

public class CoinChange {
    public static void main(String[] args) {
        CoinChange s = new CoinChange();
        int[] coins = new int[] {2};
        System.out.println(s.coinChange(coins, 3));     // -1

        coins = new int[] {1, 2, 5};
        System.out.println(s.coinChange(coins, 2));     // 1
        System.out.println(s.coinChange(coins, 11));    // 3

        coins = new int[] {3, 4, 9, 10, 13, 15};
        System.out.println(s.coinChange(coins, 36));    // 3
    }

    // Note:
    // You may assume that you have an infinite number of each kind of coin.
    // Time O(n*k*amount/min(coin))
    // Space O(n)
    public int coinChange(int[] coins, int amount) {
        int[] M = new int[amount + 1];
        Arrays.fill(M, Integer.MAX_VALUE);
        for (int num : coins) {
            if (num <= amount) {
                M[num] = 1;
            }
        }
        for (int i = 0; i <= amount; i++) {
            // check all positions of amount-coint[j];
            for (int j = 0; j < coins.length; j++) {
                int maxNum = i / coins[j];
                for (int k = 1; k <= maxNum; k++) {
                    int preTarget = i - coins[j];
                    if (preTarget >= 0 && M[preTarget] != Integer.MAX_VALUE) {
                        M[i] = Math.min(M[i], M[preTarget] + 1);
                    } else {
                        break;
                    }
                }
            }
        }
        return M[amount] == Integer.MAX_VALUE ? -1 : M[amount];
    }
}

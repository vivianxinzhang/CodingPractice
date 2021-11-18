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
        for (int money = 1; money <= amount; money++) {
            for (int coin : coins) {
                int preAmount = money - coin;
                if (preAmount >= 0 && M[preAmount] != Integer.MAX_VALUE) {
                    M[money] = Math.min(M[money], M[preAmount] + 1);
                }
            }
        }
        return M[amount] == Integer.MAX_VALUE ? -1 : M[amount];
    }

    // Method 1: dfs
    // levels coins.length      branching factor: target/min(coins)
    // Time O((target/min(coins))^coins.length)
    // Space O(coins.length)
    public int coinChangeI(int[] coins, int target) {
        int[] min = new int[] {Integer.MAX_VALUE};
        dfs(coins, 0, target, 0, min);
        return min[0] == Integer.MAX_VALUE ? -1 : min[0];
    }

    private void dfs(int[] coins, int index, int amount, int count, int[] min) {
        if (amount == 0) {
            min[0] = Math.min(min[0], count);
            return;
        }
        if (index == coins.length) {
            return;
        }
        int max = amount / coins[index];
        for (int num = 0; num <= max; num++) {
            dfs(coins, index + 1, amount - coins[index] * num, count + num, min);
        }
    }
}

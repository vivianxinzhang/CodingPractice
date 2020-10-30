package com.company;

import java.util.Arrays;

public class CoinChangeII {
    public static void main(String[] args) {
        CoinChangeII s = new CoinChangeII();
        int[] coins = new int[] {1, 2, 3};
        System.out.println(s.coinChange(5, coins));
    }

    //      0     1     2        3       4       5
    // 1    1     1     1        1       1       1
    // 2    1     1     2(1+1)   2(1+1)  3(1+2)  3(1+2)
    // 3    1     1     2        3(2+1)  4(3+1)  5(3+2)
    // if j >= coin[i]  T[i][j] = T[i-1][j] + T[i][j-coin[i]]
    // else             T[i][j] = T[i-1][j]
    public int coinChange(int amount, int[] coins) {
        int arr[] = new int[amount+1];
        Arrays.fill(arr, 0);
        arr[0] = 1;
        for(int c : coins) {
            for(int i = c; i <= amount; i++) {
                arr[i] += arr[i-c];
            }
        }
        return arr[amount];
    }
}

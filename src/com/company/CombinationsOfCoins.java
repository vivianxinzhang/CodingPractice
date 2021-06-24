package com.company;
import java.util.*;

public class CombinationsOfCoins {
    public static void main(String[] args) {
        CombinationsOfCoins s = new CombinationsOfCoins();
        int[] array = new int[] {2, 1};
        System.out.println(s.combinations(4, array));
        // [[0, 4], [1, 2], [2, 0]]

        array = new int[] {5, 2, 1};
        System.out.println(s.combinations(9, array));
        // [[0, 0, 9], [0, 1, 7], [0, 2, 5], [0, 3, 3], [0, 4, 1], [1, 0, 4], [1, 1, 2], [1, 2, 0]]
    }

    // Method 2:
    // Time and Space complexity depend on int[] coins and target number, for 99 cents example
    // Time O(99^4) general O(target/min(coins)^coins.length)
    // Space O(1) general O(1)  recursion 有n层 但 in reality coins.length 不会特别长  n 不会趋向于无穷大
    // 所以这里的 space complexity 应该表示为 O(1)
    // 如果把coin array的长度视为恒定的 这样分析没问题，但要注意跟面试官解释清楚分析过程，那才是面试官注重的地方。
    // general ：target / min(coins) 决定了最多分出多少叉
    //           层数由 coins.length 决定
    public List<List<Integer>> combinations(int target, int[] coins) {
        // each combination is represented as a List<Integer> cur,
        // and cur.get(i) = the number of coins of coins[i]
        // all the combinations are stored in the results as a list of List<Integer>
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        helper(target, coins, 0, cur, result);
        return result;
    }

    // target: remaining cents we need to complete
    // coins: all the possible different coins
    // index: we want to see how many coins we need for coins[index]
    private void helper(int target, int[] coins, int index, List<Integer> cur, List<List<Integer>> result) {
        // terminate condition:
        // NOTICE: this can also be done at index == coins.length where all the coins
        // have been picked
        // but a probably better one is at a previous level to reduce the number of
        // unnecessary branches in the DFS
        // think about it, coins.length - 1 represents the last coin we can use and actually
        // what we can do at this level is to get target / coins[coins.length - 1] coins if possible
        if (index == coins.length - 1) {
            if (target % coins[index] == 0) {
                cur.add(target / coins[index]);
                result.add(new ArrayList<>(cur));
                cur.remove(cur.size() - 1);
            }
            return;
        }
        int max = target / coins[index];
        // for coins[index], we can pick 0, 1, 2, ..., target / coins[index] coins
        for (int i = 0; i <= max; i++) {
            cur.add(i);
            // remember to modify the remaining cents for the next level
            helper(target - coins[index] * i, coins, index + 1, cur, result);
            cur.remove(cur.size() - 1);
        }
    }

    // Method 1:
    // Time O(4^99)
    // Space O(9)
    public List<List<Integer>> combinationsI(int target, int[] coins) {
        List<List<Integer>> res = new ArrayList<>();
        int[] cur = new int[coins.length];
        helper(coins, 0, target, cur, res);
        return res;
    }

    private void helper(int[] coins, int index, int target, int[] array, List<List<Integer>> res) {
        if (index == coins.length) {
            if (target == 0) {
                res.add(toList(array));
            }
            return;
        }
        // use one
        if (target - coins[index] >= 0) {
            array[index] += 1;
            helper(coins, index, target - coins[index], array, res);
            array[index] -= 1;
        }
        // do not use
        helper(coins, index + 1, target, array, res);
    }

    private List<Integer> toList(int[] array) {
        List<Integer> list = new ArrayList<>();
        for (int num : array) {
            list.add(num);
        }
        return list;
    }
}

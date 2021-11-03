package com.company;
import java.util.*;

public class FactorCombinations {
    public static void main(String[] args) {
        FactorCombinations s = new FactorCombinations();

        System.out.println(s.getFactors(1));       // []
        System.out.println(s.combinations(1));     // []

        System.out.println(s.getFactors(24));      // [2, 3, 4, 6, 8, 12]
        System.out.println(s.combinations(24));    // [[4, 6], [3, 8], [2, 12], [2, 3, 4], [2, 2, 6], [2, 2, 2, 3]]
    }

    // recursion 层数：每层考虑一个factor, factor的个数决定了会有多少层 最多有多少个factor呢？
    //                target = a*b (a <= b)  那么a最大为 sqrt(target) 所以factor的数量最多为 2sqrt(target)
    // 每个node最多分支数：每个node最多几个叉   target/min(factors)可以除多少次   为 log(target)级别
    //                   factor最小为2  那么recursion 最多有 log_2target层
    // Time O(log(target)^2sqrt(target))
    // Space O(sqrt(target))  -- recursion 有 2sqrt(target) 层
    public List<List<Integer>> combinations(int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (target <= 1) {
            return result;
        }
        List<Integer> curr = new ArrayList<>();
        List<Integer> factors = getFactors(target);
        dfs(target, factors, 0, curr, result);
        return result;
    }

    private void dfs(int remain, List<Integer> factors, int index, List<Integer> curr, List<List<Integer>> result) {
        if (index == factors.size()) {
            if (remain == 1) {
                result.add(new ArrayList<>(curr));
            }
            return;
        }
        dfs(remain, factors, index + 1, curr, result);
        // int count = 0;
        int factor = factors.get(index);
        int size = curr.size();
        while (remain % factor == 0) {
            remain /= factor;
            curr.add(factor);
            // count++;
            dfs(remain, factors, index + 1, curr, result);
        }
        curr.subList(size, curr.size()).clear();
//        while (count > 0) {
//            curr.remove(curr.size() - 1);
//            count--;
//        }
    }

    private List<Integer> getFactors(int target) {
        List<Integer> result = new ArrayList<>();
        for (int i = 2; i <= target / 2; i++) {
            if (target % i == 0) {
                result.add(i);
            }
        }
        return result;
    }

    // Method 2:
    public List<List<Integer>> combinationsII(int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> factors = new ArrayList<>();
        for (int i = 2; i * 2 <= target; i++) {
            if (target % i == 0) {
                factors.add(i);
            }
        }
        if (factors.size() == 0) {
            return result;
        }
        List<Integer> cur = new ArrayList<>();
        helper(factors, 0, 1, target, cur, result);
        return result;
    }
    private void helper(List<Integer> factors, int start, int product, int target, List<Integer> cur,
                        List<List<Integer>> result) {
        if (product > target) {
            return;
        }
        if (product == target) {
            result.add(new ArrayList<Integer> (cur));
            return;
        }
        for (int i = start; i < factors.size(); i++) {
            cur.add(factors.get(i));
            helper(factors, i, product * factors.get(i), target, cur, result);
            cur.remove(cur.size() - 1);
        }
    }
}

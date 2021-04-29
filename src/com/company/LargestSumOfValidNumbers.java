package com.company;
import java.util.*;

public class LargestSumOfValidNumbers {
    public static void main(String[] args) {
        LargestSumOfValidNumbers s = new LargestSumOfValidNumbers();
        int[][] matrix = new int[][] {
                {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8},};
        System.out.println(s.largestSum(matrix));   // 80
    }

    // 1. Think about one way of how to observe the matrix:
    //    take each row as a single element, the matrix is
    //    actually and array of rows. The benefit of doing so
    //    is to convert a two dimensional space problem into
    //    a one dimensional space problem
    // 2. How do you represent each row? More accurately,
    //    we want to know for each row, what are all possible
    //    ways of choosing some numbers from the row. We call
    //    each of the ways is a "configuration". e.g.
    // 3. The problem is simply converted to:
    //    There are 8 configurations c1 and c2 need to maintain
    //    certain property: if index i is picked in c1, then
    //    c1, c1-1, c1+1 cannot be picked in c2.
    // 4. We need an efficient way for the representation of each
    //    of the "configurations", essentially what we need is just
    //    8 bit, each bit means if the corresponding index is picked
    //    in the configuration or not.
    // Method 1:
    // Time O(k*2^k + k*2^k*2^k)
    // Space O(2^k)
    public int largestSum(int[][] matrix) {
        // Assumptions: The given matrix is 8 * 8
        int k = 8;
        // Get all the possible configurations, each configuration is represented
        // by an int value, and we use the lowest 8 bit to know which indices has been picked.
        // Here we guarantee that no adjacent bit is been picked in any of the configurations.
        List<Integer> configs = validConfigs(k);
        // dp[i][j] = the max possible sum for the sub-matrix of row 0 - i
        // and row i pick jth configuration.
        int[][] largest = new int[k][configs.size()];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < configs.size(); j++) {
                // dp[i][j] = max(dp[i-1][k]) for all possible configuration k
                // that does not have conflict with configuration j.
                largest[i][j] = Integer.MIN_VALUE;
                if (i == 0) {
                    largest[i][j] = sum(matrix[i], configs.get(j));
                } else {
                    for (int l = 0; l < configs.size(); l++) {
                        if (noConflict(configs.get(j), configs.get(l))) {
                            largest[i][j] = Math.max(largest[i][j], largest[i - 1][l] + sum(matrix[i], configs.get(j)));
                        }
                    }
                }
            }
        }
        // the result is max(dp[7][k]) for all possible configuration k
        int result = largest[k - 1][0];
        for (int i = 1; i < configs.size(); i++) {
            result = Math.max(result, largest[k - 1][i]);
        }
        return result;
    }

    // get all possible configurations, each one is represented as an int value,
    // and we use the lowest 8 bits.
    // we guarantee no adjacent bit is chosen for the lowest 8 bits.
    private List<Integer> validConfigs(int k) {
        List<Integer> configs = new ArrayList<>();
        helper(configs, 0, k, 0);
        return configs;
    }

    // similar to subset problems.
    private void helper(List<Integer> configs, int index, int k, int cur) {
        configs.add(cur);
        for (int i = index; i < k; i++) {
            helper(configs, i + 2, k,cur | (1 << i));
        }
    }

    // trick to check if configuration c1 and configuration c2 has any conflict.
    // By conflict, it means if ith bit is 1 in c1, then ith, (i-1)th, (i+1)th bit
    // cannot be 1 in c2.
    // Time O(1)
    private boolean noConflict(Integer c1, Integer c2) {
        return (c1 & c2) == 0 && ((c1 << 1) & c2) == 0 && (c1 & (c2 << 1)) == 0;
    }

    // use the configuration to calculate the real sum
    private int sum(int[] array, Integer config) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            if (((config >>> i) & 1) != 0) {
                sum += array[i];
            }
        }
        return sum;
    }

    // Method 1:
    // Time O(k*2^k + k*2^k*2^k*k)
    // Space O(k*2^k)
    public int largestSumI(int[][] matrix) {
        // Assumptions: The given matrix is 8 * 8
        int k = 8;
        // Get all the possible configurations, each configuration is represented
        // by an int value, and we use the lowest 8 bit to know which indices has been picked.
        // Here we guarantee that no adjacent bit is been picked in any of the configurations.
        List<Boolean[]> configs = validConfigsHelper(k);      // 2^k
        int[][] largest = new int[k][configs.size()];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < configs.size(); j++) {  // choose configure j for row i
                // dp[i][j] = max(dp[i-1][k]) for all possible configuration k
                // that does not have conflict with configuration j.
                largest[i][j] = Integer.MIN_VALUE;
                if (i == 0) {
                    largest[i][j] = sum(matrix[i], configs.get(j));
                } else {
                    for (int l = 0; l < configs.size(); l++) {  // l is configure for previous row
                        if (noConflict(configs.get(j), configs.get(l))) {   // O(k)
                            largest[i][j] = Math.max(largest[i][j], largest[i - 1][l] + sum(matrix[i], configs.get(j)));
                        }
                    }
                }
            }
        }
        // the result is max(dp[7][k]) for all possible configuration k
        int result = largest[k - 1][0];
        for (int i = 1; i < configs.size(); i++) {
            result = Math.max(result, largest[k - 1][i]);
        }
        return result;
    }

    // Time O(n)
    private boolean noConflict(Boolean[] b1, Boolean[] b2) {
        for (int i = 0; i < b1.length; i++) {
            if (b1[i] && (chosen(b2, i - 1) || chosen(b2, i) || chosen(b2, i + 1))) {
                return false;
            }
        }
        return true;
    }

    private boolean chosen(Boolean[] array, int i) {
        if (i < 0 || i >= array.length) {
            return false;
        }
        return array[i];
    }

    private int sum(int[] array, Boolean[] chosen) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            if (chosen[i]) {
                sum += array[i];
            }
        }
        return sum;
    }

    // Time O(k*2^k)
    // Space O(k)
    private List<Boolean[]> validConfigsHelper(int k) {
        List<Boolean[]> res = new ArrayList<>();
        Boolean[] array = new Boolean[k];
        Arrays.fill(array, false);
        dfs(array, 0, k, res);
        return res;
    }

    private void dfs(Boolean[] array, int index, int k, List<Boolean[]> res) {
        if (index >= k) {
            res.add(Arrays.copyOf(array, k));
            return;
        }
        // choose
        array[index] = true;
        dfs(array, index + 2, k, res);
        array[index] = false;
        // not choose
        dfs(array, index + 1, k, res);
    }
}

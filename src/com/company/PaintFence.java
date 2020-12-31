package com.company;

public class PaintFence {
    public static void main(String[] args) {
        PaintFence s = new PaintFence();
        System.out.println(s.numWays(1, 3));
    }

    // DP: Define two DP arrays, diff[n] and same[i].
    // same[i] means the number of ways if fence i has the same color with fence i - 1.
    // Where diff[i] means the number of ways for the fence i which has different color with fence i -1.
    // Initialization:
    //      same[0] = 0, diff[0] = k.
    // Induction rule:
    //      same[i] = diff[i - 1].
    //      diff[i] = (k - 1) * (same[i - 1] + diff[i - 1]);
    // Final state: same[n - 1] + diff[n - 1].
    public int numWays(int n, int k) {
        if (n <= 0 || k <= 0) {
            return 0;
        }
        if (n == 1) {
            return k;
        }
        // i -1 and i -2 with the same color
        int[] same = new int[n];
        // i - 1 and i - 2 with diff. color
        int[] diff = new int[n];
        // Initialization
        same[0] = 0;
        diff[0] = k;
        for (int i = 1; i < n; i++) {
            same[i] = diff[i - 1];
            // if paint i  same color with i - 1
            // i - 1 must be different from i - 2
            diff[i] = (k - 1) * (same[i - 1] + diff[i - 1]);
            // there are (dp1[i - 1] + dp2[i - 1]) # of ways to paint previous i - 1
            // for each choice, (k - 1) ways to paint current fence
        }
        // Final state
        return same[n - 1] + diff[n - 1];
    }

    public int numWaysII(int n, int k) {
        if (n <= 0 || k <= 0) {
            return 0;
        }
        if (n == 1) {
            return k;
        }
        int preSame = 0;
        int preDiff = k;
        for (int i = 1; i < n; i++) {
            int same = preDiff;
            int diff = (k - 1) * (preSame + preDiff);
            preSame = same;
            preDiff = diff;
        }

        return preSame + preDiff;
    }

    // DFS
    // Time O(k^n)
    // Space O(n)
    public int numWaysI(int n, int k) {
        if (n <= 0) {
            return 1;
        }
        int[] count = new int[1];
        int[] paint = new int[n];
        dfs(paint, 0, k, count);
        return count[0];
    }

    private void dfs(int[] paint, int index, int k, int[] count) {
        if (index == paint.length) {
            count[0]++;
            return;
        }
        for (int i = 1; i <= k; i++) {
            if (index >= 2 && paint[index - 1] == i && paint[index - 2] == i) {
                continue;
            }
            int preColor = paint[index];
            paint[index] = i;
            dfs(paint, index + 1, k, count);
            paint[index] = preColor;
        }
    }
}

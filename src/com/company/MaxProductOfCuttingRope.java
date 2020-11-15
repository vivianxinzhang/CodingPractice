package com.company;

public class MaxProductOfCuttingRope {
    public static void main(String[] args) {
        MaxProductOfCuttingRope s = new MaxProductOfCuttingRope();
        System.out.println(s.maxProduct(2));

        System.out.println(s.maxProduct(12));
    }

    // Assumptions: length >= 2
    // Method 1:
    // Time O(n^2)
    // Space O(n)
    // M[i] represents the max product for cuting length n
    // M[i] = max(max(j, M[j]) * (i - j)) 	0<j<i
    // max(j, M[j]) 左大段 切或不切   右小段 不切
    public int maxProduct(int length) {
        // pay attention to base case
        // cannot make valid cuts if length < 0
        // length = 2: 1 * 1 result 1
        if (length < 2) {
            return -1;
        }
        int[] M = new int[length + 1];
        for (int i = 2; i <= length; i++) {
            // 枚举所有的 rightmost cut, enumeration all rightmost cut position
            // Pick the first cut.
            for (int j = 1; j < i; j++) {
                // For the left partition, we can choose not cutting it or cutting it,
                // so the max number we can get is either j or array[j]
                M[i] = Math.max(M[i], Math.max(j, M[j]) * (i - j));
            }
        }
        return M[length];
    }
}

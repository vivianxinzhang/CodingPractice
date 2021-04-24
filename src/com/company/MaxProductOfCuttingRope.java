package com.company;

public class MaxProductOfCuttingRope {
    public static void main(String[] args) {
        MaxProductOfCuttingRope s = new MaxProductOfCuttingRope();
        System.out.println(s.maxProduct(2));    // 1

        System.out.println(s.maxProduct(12));   // 81
    }

    // Assumptions: length >= 2
    // Method 1:
    // Time O(n^2)
    // Space O(n)
    // M[i] represents the max product for cutting length i
    // M[i] = max(max(j, M[j]) * (i - j)) 	0<j<i
    // max(j, M[j]) 左大段 切或不切   右小段 不切
    public int maxProduct(int length) {
        if (length < 2) {
            return -1;
        }
        int[] M = new int[length + 1];
        // i meters外层循环表示考虑几米长的绳子问题规模
        for (int i = 2; i <= length; i++) {
            // pick rightmost cut, enumeration all rightmost cut position
            // Pick the first cut.
            for (int j = 1; j < i; j++) {
                // For the left partition, we can choose not cutting it or cutting it,
                // so the max number we can get is either j or array[j]
                int left = Math.max(j, M[j]);
                int right = i - j;  // 右小段：不可再分
                // update M[i] in inner for loop considering different j
                M[i] = Math.max(M[i], left * right);
            }
        }
        // return return M[length] - represents the max product for cutting length
        return M[length];
    }
}
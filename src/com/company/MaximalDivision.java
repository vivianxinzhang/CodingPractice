package com.company;

public class MaximalDivision {
    public static void main(String[] args) {
        MaximalDivision s = new MaximalDivision();
        int[] nums = new int[] {1000, 100, 10, 2};
        System.out.println(s.maxDivision(nums));    // 1000/(100/10/2)

        nums = new int[] {1, 2, 3, 4};
        System.out.println(s.maxDivision(nums));    // 1/(2/3/4)

        nums = new int[] {4, 3, 2, 1};
        System.out.println(s.maxDivision(nums));    // 4/(3/2/1)
    }

    // Note:
    // 1. The length of the input array is [1, 10].
    // 2. Elements in the given array will be in range [2, 1000].
    // 3. There is only one optimal division for each test case.
    // need to show (a0* a2 * a3...*an)/a1 is the largest --> a0 / (a1/a2/a3.../an)
    // X1/X2/X3/../Xn will always be equal to (X1/X2) * Y, no matter how you place parentheses.
    // i.e no matter how you place parentheses, X1 always goes to the numerator and X2 always goes to the denominator.
    // Hence you just need to maximize Y.
    // And Y is maximized when it is equal to X3 *..*Xn. So the answer is always X1/(X2/X3/../Xn) = (X1 *X3 *..*Xn)/X2
    // Time O(n)
    // Space O(1)
    public String maxDivision(int[] nums) {
        StringBuilder sb = new StringBuilder();
        int n = nums.length;
        for(int i = 0; i < n; i++){
            if (i > 0) {
                sb.append("/");
            }
            if (i == 1 && n > 2) {
                sb.append("(");
            }
            sb.append(nums[i]);
            if (i == n - 1 && n > 2) {
                sb.append(")");
            }
        }
        return sb.toString();
    }
}

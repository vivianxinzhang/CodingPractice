package com.company;

public class MinimumSizeOfSubarraySum {
    public static void main(String[] args) {
        MinimumSizeOfSubarraySum s = new MinimumSizeOfSubarraySum();
        int[] array = new int[] {19};
        int sum = 18;
        System.out.println(s.minSubArrayLen(sum, array));

        array = new int[] {2,3,1,2,4,3};
        sum = 7;
        System.out.println(s.minSubArrayLen(sum, array));
    }

    // Time O(n)
    // Space O(1)
    // sliding window [slow, fast] sum of  sum ≥ s
    public int minSubArrayLen(int s, int[] array) {
        if (array == null) {
            return 0;
        }
        int left = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
            while (sum >= s) {
                minLength = Math.min(minLength, i - left + 1);
                sum -= array[left];
                left++;
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}

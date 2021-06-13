package com.company;

import java.util.Arrays;

public class ThreeSumClosest {
    public static void main(String[] args) {
        ThreeSumClosest s = new ThreeSumClosest();
        int[] array = new int[] {-1, 2, 1, -4};
        System.out.println(s.threeSumClosest(array, 1));    // 1
    }

    // Time O(n^2)
    // Space O(1)
    public int threeSumClosest(int[] array, int target) {
        Arrays.sort(array);
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            int left = i + 1;
            int right = array.length - 1;
            while (left < right) {
                int sum = array[i] + array[left] + array[right];
                int curDiff = Math.abs(sum - target);
                minDiff = Math.min(minDiff, curDiff);
                if (sum == target) {
                    return 0;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return minDiff;
    }
}

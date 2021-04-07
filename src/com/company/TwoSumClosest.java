package com.company;

import java.util.Arrays;
import java.util.List;

public class TwoSumClosest {
    public static void main(String[] args) {
        TwoSumClosest s = new TwoSumClosest();
        int[] array = new int[] {1, 2};
        System.out.println(s.closest(array, 100));
        // [1, 2]

        array = new int[] {1, 4, 7, 13};
        System.out.println(s.closest(array, 7));
        // [1, 7]
    }

    // Assumptions:
    // The given array is not null and has length of at least 2
    // Time O(nlogn)
    // Space O(1)
    public List<Integer> closest(int[] array, int target) {
        int n = array.length;
        List<Integer> res = Arrays.asList(array[0], array[n - 1]);
        Arrays.sort(array);
        int left = 0;
        int right = n - 1;
        int minDiff = Integer.MAX_VALUE;
        while (left < right) {
            int twoSum = array[left] + array[right];
            int currDiff = Math.abs(twoSum - target);
            if (currDiff < minDiff) {
                minDiff = currDiff;
                res.set(0, array[left]);
                res.set(1, array[right]);
            }
            if (twoSum == target) {
                return res;
            } else if (twoSum < target) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }
}

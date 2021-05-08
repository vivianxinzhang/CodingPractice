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
        Arrays.sort(array);
        int left = 0;
        int right = array.length - 1;
        List<Integer> res = Arrays.asList(array[0], array[right]);
        int closestTwoSum = array[left] + array[right];
        while (left < right) {
            int curTwoSum = array[left] + array[right];
            if (Math.abs(curTwoSum - target) < Math.abs(closestTwoSum - target)) {
                closestTwoSum = curTwoSum;
                res.set(0, array[left]);
                res.set(1, array[right]);
            }
            if (curTwoSum == target) {
                return res;
            } else if (curTwoSum < target) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }
}

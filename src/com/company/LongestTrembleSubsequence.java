package com.company;

import java.util.HashMap;
import java.util.Map;

public class LongestTrembleSubsequence {
    public static void main(String[] args) {
        LongestTrembleSubsequence s = new LongestTrembleSubsequence();
        int[] array = new int[] {1, 6, 1, 6, 1, 6, 1, 6, 7};
        System.out.println(s.longestTrembleSubsequence(array));
        // [6,6,6,6,7]      5

        array = new int[] {9925,-6055,-3554,8011,7360,-1801,1771,293,593,-1500,9153,1696,-3217,4840,6683,7085,-9697,6066,419,2871,4873,9156,9730,5153,3389,3368,8205,1830,-3954,-989,9136};
        System.out.println(s.longestTrembleSubsequence(array));
    }

    // Given an integer array, a tremble subsequence is defined as a subsequence in which
    // the difference between maximum value and minimum value is exactly
    // 1. Return the length of the longest tremble subsequence of the given array.
    // Time O(n)
    // Space O(n)
    public int longestTrembleSubsequence(int[] array) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : array) {
            int count = map.getOrDefault(num, 0);
            map.put(num, count + 1);
        }
        int res = 0;
        for (int key : map.keySet()) {
            Integer countSmaller = map.get(key - 1);
            if (countSmaller != null) {
                res = Math.max(res, countSmaller + map.get(key));
            }
        }
        return res;
    }
}

package com.company;

import java.util.Arrays;
import java.util.HashMap;

public class TwoDifferenceInSortedArray {
    public static void main(String[] args) {
        TwoDifferenceInSortedArray s = new TwoDifferenceInSortedArray();

        int[] array = new int[] {1, 2, 3, 6, 9};
        System.out.println(Arrays.toString(s.twoDiff(array, 2)));   // [0, 2]
        System.out.println(Arrays.toString(s.twoDiff(array, -2)));  // [2, 0]
        array = new int[] {1, 4, 4, 8, 14};
        System.out.println(Arrays.toString(s.twoDiff(array, 0)));   // [1, 2]
        array = new int[] {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(s.twoDiff(array, 0)));   // []
    }

    // Assumptions:
    // 1. The given array is not null and has length of at least 2.
    // 2. If more than one pair of index exits, return the one with ascending order.
    // Method 3: two pointers
    // Time O(n)
    // Space O(n)
    public int[] twoDiff(int[] array, int target) {
        // only need to take sign in this method
        int sign = target >= 0 ? 1 : -1;
        target = Math.abs(target);
        int left = 0;
        int right = 1;
        int[] res = new int[2];
        while (right < array.length) {
            if (array[right] - array[left] == target) {
                res[0] = sign > 0 ? left : right;
                res[1] = sign > 0 ? right : left;
                return res;
            } else if (array[right] - array[left] < target) {
                right++;
            } else {
                left++;
            }
            if (left == right) {
                right++;
            }
        }
        return new int[0];
    }

    // Method 2: Use HashMap, no need to consider sign in this method
    // Time O(n)
    // Space O(n)
    public int[] twoDiffII(int[] array, int target) {
        if (array == null || array.length < 2) {
            return new int[0];
        }
        int[] result = new int[2];
        HashMap<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            if (seen.containsKey(array[i] - target)) {
                result[0] = seen.get(array[i] - target);
                result[1] = i;
                return result;
            }
            if (seen.containsKey(array[i] + target)) {
                result[0] = i;
                result[1] = seen.get(array[i] + target);
                return result;
            }
            seen.put(array[i], i);
        }
        return new int[0];
    }

    // Method 2: brute force
    // Time O(n^2)
    // Space O(1)
    public int[] twoDiffI(int[] array, int target) {
        if (array == null || array.length < 2) {
            return new int[0];
        }
        int[] result = new int[2];
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] - array[i] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
                if (array[i] - array[j] == target) {
                    result[0] = j;
                    result[1] = i;
                    return result;
                }
            }
        }
        return new int[0];
    }
}

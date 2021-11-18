package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class ContainsDuplicateIII {
    public static void main(String[] args) {
        ContainsDuplicateIII s = new ContainsDuplicateIII();

        int[] array = new int[] {58, 21};
        System.out.println(s.containsNearbyAlmostDuplicate(array, 18, 4));   // false
    }

    // Method 3: TreeSet
    // Record numbers in a Binary Search Tree(TreeSet in Java), check if the diff
    // between the new number came in and its closest number in the BST is less than target.
    // index = 0    1    2    3   4
    // nums = {11,  20,  5,  30,  1}
    // index        number      TreeSet(BST)
    // 0            11          11
    // 1            20          11
    //                             \
    //                              20
    // 2            5            11
    //                         /   \
    //                        5     20
    // 3            30           20
    //                         /    \
    //                        5     30
    // 4            1           20
    //                         /    \
    //                        5     30
    // Time O(nlogk)
    // Space O(k)
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0 || k <= 0 || t < 0) {
            return false;
        }
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            // the smallest number larger than nums[i]
            Integer cell = set.ceiling(nums[i]);
            if (cell != null && cell - nums[i] <= t) {
                return true;
            }
            // the largest number smaller than nums[i]
            Integer floor = set.floor(nums[i]);
            if (floor != null && nums[i] - floor <= t) {
                return true;
            }
            set.add(nums[i]);
            if (i >= k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

    // Method 2:
    // Time O(nk)
    // Space O(1)
    public boolean containsNearbyAlmostDuplicateII(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0 || k <= 0 || t < 0) {
            return false;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j <= Math.min(i + k, nums.length - 1); j++) {
                if (Math.abs(nums[i] - nums[j]) <= t) {
                    return true;
                }
            }
        }
        return false;
    }

    // Method 1: brute force
    // Time O(n^2)
    // Space O(1)
    public boolean containsNearbyAlmostDuplicateI(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0 || k <= 0 || t < 0) {
            return false;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                if (Math.abs(nums[i] - nums[j]) <= t && Math.abs(i - j) < t) {
                    return true;
                }
            }
        }
        return false;
    }
}

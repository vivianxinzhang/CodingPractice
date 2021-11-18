package com.company;

public class IncreasingTripletSubsequence {
    public static void main(String[] args) {
        IncreasingTripletSubsequence s = new IncreasingTripletSubsequence();

        int[] array = new int[] {1, 2, 3, 4, 5};
        System.out.println(s.increasingTriplet(array));     // true
        array = new int[] {4, 5, 1, 2, 0};
        System.out.println(s.increasingTriplet(array));     // false
    }

    // The main idea is keep two values when check all elements in the array:
    // the minimum value min until now and the second minimum value secondMin from the minimum value's position until now.
    // Then if we can find the third one that larger than those two values at the same time,
    // it must exists the triplet subsequence and return true.
    // What need to be careful is: we need to include the condition that some value has the same value
    // with minimum number, otherwise this condition will cause the secondMin change its value.
    // Method 2: math
    // Time O(n)
    // Space O(1)
    public boolean increasingTriplet(int[] nums) {
        int firstMin = Integer.MAX_VALUE;
        int secondMin = Integer.MAX_VALUE;
        for (int i : nums) {
            if (i <= firstMin) {     // consider the duplicates
                firstMin = i;
            } else if (i < secondMin) {
                secondMin = i;
            } else if (i > secondMin) {
                return true;
            }
        }
        return false;
    }

    // Method 1: dynamic programming
    // Time O(n^2)
    // Space O(n)
    public boolean increasingTripletI(int[] nums) {
        int[] M = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            M[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    M[i] = Math.max(M[i], M[j] + 1);
                }
            }
            if (M[i] >= 3) {
                return true;
            }
        }
        return false;
    }
}

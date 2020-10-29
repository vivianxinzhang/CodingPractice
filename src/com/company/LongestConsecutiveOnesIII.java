package com.company;

public class LongestConsecutiveOnesIII {
    public static void main(String[] args) {
        LongestConsecutiveOnesIII s = new LongestConsecutiveOnesIII();
        int[] array = new int[] {1, 0, 0, 1, 1, 1, 0, 0};
         System.out.println(s.longestConsecutiveOnes(array, 2));
    }

    // Method 1: dp


    // Method 2: sliding window
    // Time O(n)
    // Space O(1)
    public int longestConsecutiveOnes(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int slow = 0;
        int fast = -1;
        int maxLength = 0;
        int flip = 0;
        while (fast < nums.length - 1) {
            if (nums[fast + 1] == 1) {
                fast++;
            } else if (flip < k) {
                fast++;
                flip++;
            } else if (nums[slow] == 0) {
                flip--;
                slow++;
            } else {
                slow++;
            }
            maxLength = Math.max(fast - slow + 1, maxLength);
        }
        return maxLength;
    }
}

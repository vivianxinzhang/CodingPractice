package com.company;

public class LongestConsecutiveOnesII {
    public static void main(String[] args) {
        LongestConsecutiveOnesII s = new LongestConsecutiveOnesII();
        int[] array = new int[] {1, 0, 1, 1};
        System.out.println(s.longestConsecutiveOnes(array));
    }

    // Time O(n)
    // Space O(1)
    public int longestConsecutiveOnes(int[] nums) {
        int slow = 0;
        int fast = -1;
        int maxLength = 0;
        boolean flip = false;
        while (fast < nums.length - 1) {
            if (nums[fast + 1] == 1) {
                fast++;
                maxLength = Math.max(maxLength, fast - slow + 1);
            } else if (!flip) {
                fast++;
                flip = true;
                maxLength = Math.max(maxLength, fast - slow + 1);
            } else if (nums[slow] == 0) {
                slow++;
                flip = false;
            } else {
                slow++;
            }
        }
        return maxLength;
    }
}

package com.company;
import java.util.*;

public class SubarraySumEqualsK {
    public static void main(String[] args) {
        SubarraySumEqualsK s = new SubarraySumEqualsK();
        int[] array = new int[]{1, 1, 1};
        System.out.println(s.subarraySum(array, 2));

        array = new int[]{1, 2, 3};
        System.out.println(s.subarraySum(array, 3));
    }

    // Method 4:
    // Time O(n)
    // Space O(n)
    public int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            // subarray ending at index i
            // [0 ... ... i] sum is sum
            // [0 ...j] sum is (sum-k)
            // [j+1 ... i] sum is k
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    // Method 1: Brute Force
    // Time O(n^3)
    // Space O(1)
    public int subarraySumI(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            for (int end = start + 1; end <= nums.length; end++) {
                int sum = 0;
                for (int i = start; i < end; i++)
                    sum += nums[i];
                if (sum == k)
                    count++;
            }
        }
        return count;
    }

    // Method 2: prefix Sum
    // Time O(n^2)
    // Space O(n)
    public int subarraySumII(int[] nums, int k) {
        int count = 0;
        int[] sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i <= nums.length; i++)
            sum[i] = sum[i - 1] + nums[i - 1];
        for (int start = 0; start < nums.length; start++) {
            for (int end = start + 1; end <= nums.length; end++) {
                if (sum[end] - sum[start] == k)
                    count++;
            }
        }
        return count;
    }

    // Method 3:
    // Time O(n^2)
    // Space O(n)
    public int subarraySumIII(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            // Whenver, we update the startstart index,
            // we need to reset the sumsum value to 0.
            int sum = 0;
            for (int end = start; end < nums.length; end++) {
                sum += nums[end];
                if (sum == k)
                    count++;
            }
        }
        return count;
    }
}
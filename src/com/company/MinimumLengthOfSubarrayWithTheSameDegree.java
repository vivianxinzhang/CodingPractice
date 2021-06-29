package com.company;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MinimumLengthOfSubarrayWithTheSameDegree {
    public static void main(String[] args) {
        MinimumLengthOfSubarrayWithTheSameDegree s = new MinimumLengthOfSubarrayWithTheSameDegree();

        int[] array = new int[] {1, 2, 2, 1};
        System.out.println(s.minimumLengthOfSubarray(array));   // 2
    }

    // Assumption:
    // The given array is not null and not empty.
    // Method 2:
    // An array that has degree d, must have some element x occur d times.
    // sub-array has the same degree, element x still occurs d times.
    // The shortest such sub-array would be from the first occurrence of x until the last occurrence of x.
    // For each element in the given array, need to know the index of its first occurrence and the index of its last occurrence.
    // For example, with nums = [1,2,3,2,5]  we have left[2] = 1 and right[2] = 3.
    // Then, for each element x that occurs the maximum number of times, right[x] - left[x] + 1 will be our candidate answer,
    // and we'll take the minimum of those candidates.
    // Time O(n)
    // Space O(n)
    public int minimumLengthOfSubarray(int[] nums) {
        Map<Integer, Integer> firstOccur = new HashMap<>();
        Map<Integer, Integer> lastOccur = new HashMap<>();
        Map<Integer, Integer> count = new HashMap<>();
        // Step 1: pre-processing           - O(n)
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            if (firstOccur.get(x) == null) {
                firstOccur.put(x, i);
            }
            lastOccur.put(x, i);
            count.put(x, count.getOrDefault(x, 0) + 1);
        }
        // Step 2: iterate through keySet(), update minLength when necessary
        // for each different num, minLength is lastOccur index - firstOccur index + 1
        int minLength = nums.length;
        int degree = Collections.max(count.values());
        for (int num : count.keySet()) {
            if (count.get(num) == degree) {
                int curLength = lastOccur.get(num) - firstOccur.get(num) + 1;
                minLength = Math.min(minLength, curLength);
            }
        }
        return minLength;
    }

    // Method 1:
    // Time O(n^3)
    // Space O(n)
    public int minimumLengthOfSubarrayI(int[] nums) {
        int degree = findDegree(nums, 0, nums.length - 1);
        int length = nums.length;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int curDegree = findDegree(nums, i, j);
                if (curDegree == degree) {
                    length = Math.min(length, j - i + 1);
                }
            }
        }
        return length;
    }

    private int findDegree(int[] nums, int left, int right) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = left; i <= right; i++) {
            int count = map.getOrDefault(nums[i], 0);
            map.put(nums[i], count + 1);
        }
        int degree = 0;
        for (Integer frequency : map.values()) {
            degree = Math.max(degree, frequency);
        }
        return degree;
    }
}
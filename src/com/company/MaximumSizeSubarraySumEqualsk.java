package com.company;
import java.util.*;

public class MaximumSizeSubarraySumEqualsk {
    public static void main(String[] args) {
        MaximumSizeSubarraySumEqualsk s = new MaximumSizeSubarraySumEqualsk();
        int[] nums = {1, -1, 5, -2, 3};
        System.out.println(s.maxSubArrayLen(nums, 3));

        nums = new int[] {-2, -1, 2, 1};
        System.out.println(s.maxSubArrayLen(nums, 1));
    }

    // Time O(n)
    // Space O(n)
    public int maxSubArrayLen(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int result = 0;
        int sum = 0;
        for(int i=0; i<nums.length; i++){
            sum += nums[i];
            if(map.containsKey(sum - k)){
                result = Math.max(result, i - map.get(sum - k));
            }
            map.putIfAbsent(sum, i);
        }
        return result;
    }
}

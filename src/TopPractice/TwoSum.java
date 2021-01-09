package TopPractice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    /** Given an array of integers nums and an integer target, return indices of the two numbers
     such that they add up to target.
     You may assume that each input would have exactly one solution,
     and you may not use the same element twice.
     You can return the answer in any order.
     * */
    public static void main(String[] args) {
        TwoSum s = new TwoSum();
        int[] nums = new int[] {2, 7, 11, 15};
        System.out.println(Arrays.toString(s.twoSum(nums, 9)));

        nums = new int[] {3, 2, 4};
        System.out.println(Arrays.toString(s.twoSum(nums, 6)));

        nums = new int[] {3, 3};
        System.out.println(Arrays.toString(s.twoSum(nums, 6)));
    }

    // Assumptions: array is not null, array.length >= 2
    // return indices of the sum pair
    // Time O(n)
    // Space O(n)
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                result[0] = map.get(target - nums[i]);
                result[1] = i;
                return result;
            }
            map.put(nums[i], i);
        }
        return result;
    }
}

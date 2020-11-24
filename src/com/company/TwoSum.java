package com.company;
import java.util.*;

public class TwoSum {
    public static void main(String[] args) {
        TwoSum s = new TwoSum();
        int[] array = new int[] {1, 3, 2, 4};
        System.out.println(s.existSum(array, 5));

        array = new int[] {1, 2, 2, 4};
        System.out.println(s.existSum(array, 6));
    }

    // Assumptions: array is not null, and has size >= 2
    // Method 1: sort the array first, then use two pointers.
    // Time O(n)
    // Space O(n)
    public boolean existSumI(int[] array, int target) {
        if (array == null || array.length <= 1) {
            return false;
        }
        Arrays.sort(array);
        int left = 0;
        int right = array.length - 1;
        while (left < right) {  // not including = , because left and right need to be different
            if (array[left] + array[right] == target) {
                return true;
            } else if (array[left] + array[right] < target) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }

    // Method 2: use HashSet to record the previous traversed values
    // Time O(n)
    // Space O(n)
    public boolean existSum(int[] array, int target) {
        Set<Integer> set = new HashSet<>();
        for (int num : array) {
            if (set.contains(target - num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }
}

package com.company;
import java.util.*;

public class TwoSumAllPairII {
    public static void main(String[] args) {
        TwoSumAllPairII s = new TwoSumAllPairII();
        int[] array = new int[] {1, 3, 2, 4};
        System.out.println(s.allPairs(array, 5));

        array = new int[] {1, 2, 2, 4};
        System.out.println(s.allPairs(array, 6));
    }

    // Assumptions: array is not null, array.length >= 2.
    // Method 1: sort the array first, then use two pointers.
    // Time O(nlogn)
    // Space O(n)
    public List<List<Integer>> allPairs(int[] array, int target) {
        Arrays.sort(array);
        List<List<Integer>> result = new ArrayList<>();
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            // ignore all the consecutive duplicate values when we want
            // to determine the smaller element of the pair.
            if (left > 0 && array[left] == array[left - 1]) {
                left++;
                continue;
            }
            int cur = array[left] + array[right];
            if (cur == target) {
                result.add(Arrays.asList(array[left], array[right]));
                left++;
                right--;
            } else if (cur < target) {
                left++;
            } else {
                right--;
            }
        }
        return result;
    }

    // Method 2:
    // Time O(n)
    // Space O(n)
    public List<List<Integer>> allPairsI(int[] array, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // Record the number of existence of values
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : array) {
            Integer count = map.get(num);
            // Two cases when we need to make the pair a solution
            // 1. if 2 * x == target, and we need to make sure there is no duplicates
            // 2. if x + y == target, and this is the first time both x and y
            // are present, so we can make sure there is no duplicates
            if (num * 2 == target && count != null && count == 1) {
                result.add(Arrays.asList(num, num));
            } else if (map.containsKey(target - num) && count == null) {
                result.add(Arrays.asList(target - num, num));
            }
            if (count == null) {
                map.put(num, 1);
            } else {
                map.put(num, count + 1);
            }
        }
        return result;
    }
}

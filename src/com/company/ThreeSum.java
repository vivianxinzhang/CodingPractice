package com.company;
import java.util.*;

public class ThreeSum {
    public static void main(String[] args) {
        ThreeSum s = new ThreeSum();
        int[] array = new int[] {2, 1, 3, 2, 4, 3, 4, 2};
        System.out.println(s.allTriples(array, 8));

        array = new int[] {1, 2, 3, 2, 1, 2, 3, 2, 1};
        System.out.println(s.allTriples(array, 6));
    }

    // Method 2: cannot sort the array, how to deduplicate?
    // O(n^2)
    // O(n)
    public List<List<Integer>> allTriples(int[] array, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // key: num  value: count
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            if (set.contains(array[i])) {
                continue;
            }
            set.add(array[i]);
            int twoSumTarget = target - array[i];
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = i + 1; j < array.length; j++) {
                Integer count = map.get(array[j]);
                if (array[j] * 2 == twoSumTarget && count != null && count == 1) {
                    result.add(Arrays.asList(array[i], array[j], array[j]));
                } else if (map.containsKey(twoSumTarget - array[j]) && count == null) {
                    result.add(Arrays.asList(array[i], twoSumTarget - array[j], array[j]));
                }
                // update map
                if (count == null) {
                    map.put(array[j], 1);
                } else {
                    map.put(array[j], count + 1);
                }
            }
        }
        return result;
    }

    // Assumptions: array is not null, array.length >= 3.
    // Method 1:
    // O(n^2)
    // O(1)
    public List<List<Integer>> allTriplesI(int[] array, int target) {
        // Write your solution here
        // Assumptions: array is not null, array.length >= 3;
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(array);
        for (int i = 0; i < array.length - 2; i++) {
            // Our goal is to find i < j < k, such that
            // array[i] + array[j] + array[k] == target,
            // To make sure there is no duplicate tuple,
            // we ignore all the duplicate possible i.
            // e.g, if we have 2, 2, 2, only the first 2 will be selected as i.
            if (i > 0 && array[i] == array[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = array.length - 1;
            while (left < right) {
                int tmp = array[left] + array[right];
                if (tmp + array[i] == target) {
                    result.add(Arrays.asList(array[i], array[left], array[right]));
                    left++;
                    // ignore all possible duplicate j as well.
                    while (left < right && array[left] == array[left - 1]) {
                        left++;
                    }
                }
                else if (tmp + array[i] < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }
}

package com.company;
import java.util.*;

public class ThreeSum {
    public static void main(String[] args) {
        ThreeSum s = new ThreeSum();
        int[] array = new int[] {3, 4, 0, -1, 2, 0, 5};
        System.out.println(s.allTriples(array, 4)); // [[-1, 0, 5], [-1, 2, 3], [0, 0, 4]]

        array = new int[] {2, 1, 3, 2, 4, 3, 4, 2};
        System.out.println(s.allTriples(array, 8)); // [[2, 3, 3]]

        array = new int[] {1, 2, 3, 2, 1, 2, 3, 2, 1};
        System.out.println(s.allTriples(array, 6)); // [[2, 2, 2]]
    }

    // Method 2: cannot sort the array, use Set and sorting to deduplicate
    // Assumptions:
    // 1. The given array is not null and has length of at least 3
    // 2. No duplicate triples should be returned, order of the values in the tuple does not matter
    // Time O(n^2)
    // Space O(n)
    public List<List<Integer>> allTriples(int[] array, int target) {
        Set<List<Integer>> set = new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            // two sum
            int twoSum = target - array[i];
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = i + 1; j < array.length; j++) {
                int num = array[j];
                int count = map.getOrDefault(num, 0);
                if (num * 2 == twoSum && count == 1) {
                    List<Integer> currRes = Arrays.asList(array[i], num, num);
                    Collections.sort(currRes);
                    System.out.println(currRes);
                    set.add(new ArrayList<>(currRes));
                } else if (count == 0 && map.containsKey(twoSum - array[j])) {
                    List<Integer> currRes = Arrays.asList(array[i], twoSum - num, num);
                    Collections.sort(currRes);
                    System.out.println(currRes);
                    set.add(new ArrayList<>(currRes));
                }
                map.put(num, count + 1);
            }
        }
        return new ArrayList<>(set);
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

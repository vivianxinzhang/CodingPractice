package com.company;
import java.util.*;

public class TwoSumAllPairI {
    public static void main(String[] args) {
        TwoSumAllPairI s = new TwoSumAllPairI();

        int[] array = new int[] {1, 3, 2, 4};
        System.out.println(s.allPairs(array, 5));
        // [[1, 2], [0, 3]]

        array = new int[] {1, 2, 2, 4};
        System.out.println(s.allPairs(array, 6));
        // [[1, 3], [2, 3]]

        array = new int[] {3, 9, 1, 2, 3};
        System.out.println(s.allPairs(array, 4));
        // [[0, 2], [2, 4]]

        array = new int[] {3, 5, 3, 2, 4, 4};
        System.out.println(s.allPairs(array, 7));
        // [[1, 3], [0, 4], [2, 4], [0, 5], [2, 5]]
    }

    // Assumptions: array is not null, array.length >= 2
    // Time O(n)
    // Space O(n)
    public List<List<Integer>> allPairs(int[] array, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // key: number, value: list of all possible indices
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            List<Integer> indices = map.get(target - array[i]);
            // if target - array[i] is in the map,
            // we can get all the pairs of (j, i), with i as the larger index.
            if (indices != null) {
                for (int j : indices) {
                    result.add(Arrays.asList(j, i));
                }
            }
            // add current index i to all the possible indices for value of array[i]
            if (!map.containsKey(array[i])) {
                map.put(array[i], new ArrayList<>());
            }
            map.get(array[i]).add(i);
        }
        return result;
    }
}

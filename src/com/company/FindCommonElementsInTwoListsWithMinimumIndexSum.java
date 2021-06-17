package com.company;

import java.util.*;

public class FindCommonElementsInTwoListsWithMinimumIndexSum {
    public static void main(String[] args) {
        FindCommonElementsInTwoListsWithMinimumIndexSum s = new FindCommonElementsInTwoListsWithMinimumIndexSum();
        String[] array1 = new String[] {"amazon", "microsoft", "linkedin", "google"};
        String[] array2 = new String[] {"stark", "intel", "amd", "amazon"};
        System.out.println(s.getCommonInterest(array1, array2));    // [amazon]

        array1 = new String[] {"amazon", "microsoft", "linkedin", "google"};
        array2 = new String[] {"stark", "intel", "amd", "amazon","microsoft"};
        System.out.println(s.getCommonInterest(array1, array2));    // [amazon]

        array1 = new String[] {"amazon", "microsoft", "linkedin", "google"};
        array2 = new String[] {"stark", "intel", "microsoft", "amazon"};
        System.out.println(s.getCommonInterest(array1, array2));    // [amazon, microsoft]
    }

    // Assumptions:
    // 1. There is no duplicate in either list.
    // 2. The given lists are unordered.
    // 3. The index is in range between 0 and length of list - 1.
    // Time O(m + n)
    // Space O(m)
    public List<String> getCommonInterest(String[] array1, String[] array2) {
        List<String> res = new ArrayList<>();
        if (array1 == null || array1.length == 0 || array2 == null || array2.length == 0) {
            return res;
        }
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < array1.length; i++) {
            map.put(array1[i], i);
        }
        int minSumIndex = Integer.MAX_VALUE;
        for (int i = 0; i < array2.length; i++) {
            if (map.containsKey(array2[i])) {
                int curMin = map.get(array2[i]) + i;
                if (curMin == minSumIndex) {
                    res.add(array2[i]);
                } else if (curMin < minSumIndex) {
                    res = new ArrayList<>();
                    res.add(array2[i]);
                    minSumIndex = curMin;
                }
            }
        }
        Collections.sort(res);
        return res;
    }
}

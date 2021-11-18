package com.company;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ContainsDuplicateII {
    public static void main(String[] args) {
        ContainsDuplicateII s = new ContainsDuplicateII();

        int[] array = new int[] {58, 21};
        System.out.println(s.containsNearbyDuplicate(array, 2));   // false
        array = new int[] {1, 0, 1};
        System.out.println(s.containsNearbyDuplicate(array, 1));   // false
    }

    // Method 2: map
    // Time O(n)
    // Space O(n)
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // key: num    value: most nearby num index
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer preIdx = map.get(nums[i]);
            if (preIdx != null) {
                if (i - preIdx <= k) {
                    return true;
                }
            }
            map.put(nums[i], i);
        }
        return false;
    }

    // Method 1:
    // Time O(nk)
    // Space O(1)
    public boolean containsNearbyDuplicateI(int[] array, int k) {
        if (k < 0) {
            return false;
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j <= Math.min(i + k, array.length - 1); j++) {
                if (array[i] == array[j]) {
                    return true;
                }
            }
        }
        return false;
    }
}

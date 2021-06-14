package com.company;

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {
    public static void main(String[] args) {
        ContainsDuplicate s = new ContainsDuplicate();
        int[] array = new int[] {1, 3, 2, 1};
        System.out.println(s.containsDuplicate(array));
    }

    // Time O(n)
    // Space O(n)
    public boolean containsDuplicate(int[] array) {
        if (array == null || array.length <= 1) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            if (!set.add(array[i])) {
                return false;
            }
        }
        return true;
    }
}

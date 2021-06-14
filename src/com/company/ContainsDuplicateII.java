package com.company;

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicateII {
    public static void main(String[] args) {
        ContainsDuplicateII s = new ContainsDuplicateII();

        int[] array = new int[] {58, 21};
        System.out.println(s.containsNearbyDuplicate(array, 2));   // false
    }

    // Method 2: two pointers
    // Time O(n)
    // Space O(n)
    public boolean containsNearbyDuplicate(int[] array, int k) {
        int slow = 0;
        int fast = 0;
        int minDistance = Integer.MAX_VALUE;
        Set<Integer> unmatched = new HashSet<>();
        for (fast = 0; fast < array.length; fast++) {
            if (unmatched.add(array[fast])) {
                continue;
            } else {    // 遇到相同的出现过的数字了
                while (array[slow] != array[fast]) {
                    slow++;
                }
                minDistance = Math.min(minDistance, fast - slow);
                if (minDistance <= k) {
                    return true;
                }
                unmatched.remove(array[slow]);
                slow++;
            }
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

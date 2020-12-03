package com.company;
import java.util.*;

public class AllSubsetsII {
    public static void main(String[] args) {
        AllSubsetsII s = new AllSubsetsII();
        System.out.println(s.subSets(""));  // []
        System.out.println(s.subSets("aa")); // [aa, a, ]
        System.out.println(s.subSets("abb")); // [abb, ab, a, bb, b, ]
        System.out.println(s.subSets("abab")); // [aabb, aab, aa, abb, ab, a, bb, b, ]
    }

    // Time O(2^n * n)
    // Space O(n)
    public List<String> subSets(String set) {
        List<String> result = new ArrayList<>();
        if (set == null) {
            return result;
        }
        char[] array = set.toCharArray();   // O(n)
        Arrays.sort(array);     // O(nlogn)
        // record the current subset
        StringBuilder sb = new StringBuilder();
        helper(array, 0, sb, result);
        return result;
    }

    // at each level, determine the character at the position "index" to be picked or not
    private void helper(char[] array, int i, StringBuilder sb, List<String> result) {
        // terminate condition:
        // when we finishes determining for all the characters pick or not,
        // we have a complete subset
        if (i == array.length) {
            result.add(sb.toString());
            return;
        }
        // 1. pick the character at index
        sb.append(array[i]);
        helper(array, i + 1, sb, result);
        // remember to remove the added character when back tracking to previous level
        sb.deleteCharAt(sb.length() - 1);
        // 2. not pick the character at index
        while (i + 1 < array.length && array[i + 1] == array[i]) {
            i++;
        }
        helper(array, i + 1, sb, result);
    }

    // at each level, determine the character at the position "index" to be picked or not
    private void helperI(char[] array, int i, StringBuilder sb, List<String> result) {
        // terminate condition:
        // when we finishes determining for all the characters pick or not,
        // we have a complete subset
        if (i == array.length) {
            result.add(sb.toString());
            return;
        }
        // 2. not pick the character at index
        int currIdx = i;
        while (i + 1 < array.length && array[i + 1] == array[i]) {
            i++;
        }
        helper(array, i + 1, sb, result);
        i = currIdx;
        // 1. pick the character at index
        sb.append(array[i]);
        helper(array, i + 1, sb, result);
        // remember to remove the added character when back tracking to previous level
        sb.deleteCharAt(sb.length() - 1);
    }
}
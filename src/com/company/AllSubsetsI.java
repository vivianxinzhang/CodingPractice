package com.company;
import java.util.*;

public class AllSubsetsI {
    public static void main(String[] args) {
        AllSubsetsI s = new AllSubsetsI();
        System.out.println(s.subSets("a"));
    }

    // Time O(2^n * n)
    // Space O(n)
    public List<String> subSets(String set) {
        List<String> result = new ArrayList<>();
        if (set == null || set.length() == 0) {
            return result;
        }
        // record the current subset
        StringBuilder sb = new StringBuilder();
        helper(set, 0, sb, result);
        return result;
    }

    // at each level, determine the character at the position "index" to be picked or not
    private void helper(String set, int i, StringBuilder sb, List<String> result) {
        // terminate condition:
        // when we finishes determining for all the characters pick or not,
        // we have a complete subset
        if (i == set.length()) {
            result.add(sb.toString());
            return;
        }
        // 1. not pick the character at index
        helper(set, i + 1, sb, result);
        // 2. pick the character at index
        sb.append(set.charAt(i));
        helper(set, i + 1, sb, result);
        // remember to remove the added character when back tracking to previous level
        sb.deleteCharAt(sb.length() - 1);
    }
}

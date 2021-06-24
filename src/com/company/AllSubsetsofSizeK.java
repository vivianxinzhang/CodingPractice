package com.company;
import java.util.*;

public class AllSubsetsofSizeK {
    public static void main(String[] args) {
        AllSubsetsofSizeK s = new AllSubsetsofSizeK();
        System.out.println(s.subSetsOfSizeK("xak", 2));
        // [ak, xk, xa]
        System.out.println(s.subSetsOfSizeK("cba", 2));
        // [ba, ca, cb]
        System.out.println(s.subSetsOfSizeK("bab", 2));
        // [ab, bb, ba]
        System.out.println(s.subSetsOfSizeK("abab", 2));
        // [ab, bb, ba, ab, aa, ab]
    }

    // Assumptions:
    // There are no duplicate characters in the original set.
    // Time O(2^n * k)
    // Space O(n)
    public List<String> subSetsOfSizeK(String set, int k) {
        List<String> result = new ArrayList<>();
        if (set == null) {
            return result;
        }
        // record the current subset
        StringBuilder sb = new StringBuilder();
        dfs(set, k, 0, sb, result);
        return result;
    }

    // at each level, determine the character at the position "index" to be picked or not.
    private void dfs(String set, int k, int index, StringBuilder sb, List<String> result) {
        // terminate condition:
        // when we have picked k elements, we add the subset to teh result and return.
        if (sb.length() == k) {
            result.add(sb.toString());
            return;
        }
        // when we finishes determining for all the characters pick or not, we must return.
        if (index == set.length()) {
            return;
        }
        // 1. not pick the character at index
        // not add set.charAt(i)
        dfs(set, k, index + 1, sb, result);
        // add set.charAt(i)
        sb.append(set.charAt(index));
        dfs(set, k, index + 1, sb, result);
        sb.deleteCharAt(sb.length() - 1);
    }
}

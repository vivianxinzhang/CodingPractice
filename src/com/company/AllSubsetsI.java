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
        // Write your solution here.
        List<String> result = new ArrayList<>();
        if (set == null || set.length() == 0) {
            return result;
        }
        StringBuilder sb = new StringBuilder();
        helper(set, 0, sb, result);
        return result;
    }

    private void helper(String set, int i, StringBuilder sb, List<String> result) {
        if (i == set.length()) {
            result.add(sb.toString());
            return;
        }
        helper(set, i + 1, sb, result);
        sb.append(set.charAt(i));
        helper(set, i + 1, sb, result);
        sb.deleteCharAt(sb.length() - 1);
    }
}

package com.company;
import java.util.*;

public class MinimumWindowSubstring {
    public static void main(String[] args) {
        MinimumWindowSubstring s = new MinimumWindowSubstring();
        System.out.println(s.minWindow("A", ""));    // ""
        System.out.println(s.minWindow("", "a"));    // ""
        System.out.println(s.minWindow("ADOBECODEBANC", "ABC"));    // BANC
        System.out.println(s.minWindow("acbba", "aab"));    // acbba
    }

    // Method 1: brute force
    // Time: O(n)
    // Space: O(m)
    public String minWindow(String source, String target) {
        if (target.isEmpty()) {
            return "";
        }
        Map<Character, Integer> map = toMap(target);     // O(m)
        int slow = 0;
        int fast = 0;
        int match = 0;
        int minWindow = Integer.MAX_VALUE;
        int globalLeft = 0;
        while (fast < source.length() || match == map.size()) {
            if (match < map.size()) {
                Integer count = map.get(source.charAt(fast));
                if (count != null) {
                    map.put(source.charAt(fast), count - 1);
                    if (count == 1) {
                        match++;
                    }
                }
                fast++;
            } else {
                Integer count = map.get(source.charAt(slow));
                if (count != null) {
                    map.put(source.charAt(slow), count + 1);
                    if (count == 0) {
                        match--;
                    }
                }
                slow++;
            }
            if (match == map.size()) {
                int curWindow = fast - slow;
                if (curWindow < minWindow) {
                    minWindow = curWindow;
                    globalLeft = slow;
                }
            }
        }
        return minWindow != Integer.MAX_VALUE ? source.substring(globalLeft, globalLeft + minWindow) : "";
    }

    private Map<Character, Integer> toMap(String target) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < target.length(); i++) {
            int count = map.getOrDefault(target.charAt(i), 0);
            map.put(target.charAt(i), count + 1);
        }
        return map;
    }

    // Method 1: brute force
    // Time: O(n^3*m)
    // Space: O(n^2*m)
    public String minWindowI(String source, String target) {
        if (target.isEmpty()) {
            return "";
        }
        int minWindow = Integer.MAX_VALUE;
        int globalLeft = 0;
        for (int i = 0; i < source.length(); i++) {
            for (int j = i; j < source.length(); j++) {
                if (match(source, i, j, target)) {
                    int curWindow = j - i + 1;
                    if (curWindow < minWindow) {
                        minWindow = curWindow;
                        globalLeft = i;
                    }
                }
            }
        }
        return minWindow != Integer.MAX_VALUE ? source.substring(globalLeft, globalLeft + minWindow) : "";
    }

    private boolean match(String source, int left, int right, String target) {
        Map<Character, Integer> map = toMap(target);     // O(m)
        int match = 0;
        for (int i = left; i <= right; i++) {   // O(n)
            Integer count = map.get(source.charAt(i));
            if (count != null) {
                map.put(source.charAt(i), count - 1);
                if (count == 1) {
                    map.remove(source.charAt(i));
                }
            }
        }
        return map.size() == 0;
    }
}

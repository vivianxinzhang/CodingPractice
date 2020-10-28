package com.company;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        String input = "bananaandbanana";
        int[] array = new int[] {1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0};
        System.out.println(s.allAnagrams("ab", "abcbac"));
    }

    // Find All anagrams of String sh in string lo, return all the starting indices
    // Assumption: s, 1 are not null, s is not empty
    // use a hash map to store characters of sh, and take the total numbers of unmatched characters
    // sliding window [i - sh.length, i], when # of unmatched characters is zero in window, find an anagram
    // Time average O(n) <-- O(m + n + n)String s: m  String l: m  worst O(mn) <-- (m^2+ n + n*m + (n-m)m)
    // Space O(m)
    public List<Integer> allAnagrams(String sh, String lo) {
        List<Integer> result = new ArrayList<>();
        if (lo.length() == 0) {
            return result;
        }
        if (sh.length() > lo.length()) {
            return result;
        }
        int match = 0;
        Map<Character, Integer> map = countMap(sh);
        for (int i = 0; i < lo.length(); i++){
            // deal with incoming char
            char incomingChar = lo.charAt(i);
            Integer count = map.get(incomingChar);
            if (count != null) {
                if (count == 1) {
                    match++;
                }
                map.put(incomingChar, count - 1);
            }

            // deal with outgoing char
            if (i >= sh.length()) {
                char outgoingChar = lo.charAt(i - sh.length());
                count = map.get(outgoingChar);
                if (count != null) {
                    if (count == 0) {
                        match--;
                    }
                    map.put(outgoingChar, count + 1);
                }
            }

            // if all char matched, add left border to result
            if (match == map.size()) {
                result.add(i - sh.length() + 1);
            }
        }
        return result;
    }

    private Map<Character, Integer> countMap(String sh) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < sh.length(); i++) {
            Integer count = map.getOrDefault(sh.charAt(i), 0);
            map.put(sh.charAt(i), count + 1);
        }
        return map;
    }
}
























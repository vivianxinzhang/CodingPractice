package com.company;
import java.util.*;

public class SmallestSubstringContainingAllCharactersOfAnotherString {
    public static void main(String[] args) {
        SmallestSubstringContainingAllCharactersOfAnotherString s = new SmallestSubstringContainingAllCharactersOfAnotherString();
        String lo = "test strings";
        String sh = "itsst";
        System.out.println(s.smallest(lo, sh)); // "st stri"

        lo = "orangedognotehand";
        sh = "tank";
        System.out.println(s.smallest(lo, sh)); // ""
    }

    // Assumptions: s1 and s2 are not null or empty
    // Time O(n) - long length n
    // Space O(m) - short length m
    // sliding window [slow, fast]
    public String smallest(String s1, String s2) {
        Map<Character, Integer> map = getCountMap(s2);
        int slow = 0;
        int fast = 0;
        int globalLeft = 0;
        int globalRight = 0;
        int minLength = Integer.MAX_VALUE;
        int match = 0;
        while (fast < s1.length() || match == map.size()) {
            if (match < map.size()) {
                Integer countIn = map.get(s1.charAt(fast));
                if (countIn != null) {
                    if (countIn == 1) {
                        match++;
                    }
                    map.put(s1.charAt(fast), countIn - 1);
                }
                fast++;
            } else {
                Integer countOut = map.get(s1.charAt(slow));
                if (countOut != null) {
                    map.put(s1.charAt(slow), countOut + 1);
                    if (countOut == 0) {
                        match--;
                    }
                }
                slow++;
            }
            if (match == map.size() && fast - slow < minLength) {
                minLength = fast - slow;
                globalLeft = slow;
                globalRight = fast;
            }
        }
        return s1.substring(globalLeft, globalRight);
    }

    private Map<Character, Integer> getCountMap(String s2) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s2.length(); i++) {
            int count = map.getOrDefault(s2.charAt(i), 0);
            map.put(s2.charAt(i), count + 1);
        }
        return map;
    }
}
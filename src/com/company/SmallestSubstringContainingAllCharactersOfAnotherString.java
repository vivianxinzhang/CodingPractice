package com.company;
import java.util.*;

public class SmallestSubstringContainingAllCharactersOfAnotherString {
    public static void main(String[] args) {
        SmallestSubstringContainingAllCharactersOfAnotherString s = new SmallestSubstringContainingAllCharactersOfAnotherString();
        String lo = "test strings";
        String sh = "itsst";
        System.out.println(s.smallest(lo, sh)); // "itsst"

        lo = "orangedognotehand";
        sh = "tank";
        System.out.println(s.smallest(lo, sh)); // ""
    }

    // Time O(n) - long length n
    // Space O(m) - short length m
    // sliding window [slow, fast]
    public String smallest(String lo, String sh) {
        List<Integer> result = new ArrayList<>();
        if (lo.length() == 0 || sh.length() > lo.length()) {
            return "";
        }
        int match = 0;
        Map<Character, Integer> map = countMap(sh);
        int globalLeft = 0;
        int globalRight = -1;   // must initialize with -1 to guarantee no element in sliding window at the beginning
        int minLength = Integer.MAX_VALUE;
        int fast = -1;  // must initialize with -1 to guarantee no element in sliding window at the beginning
        int slow = 0;
        while (fast < lo.length() - 1) {
            if (match < map.size()) {   // deal with incoming char
                fast++;
                char incomingChar = lo.charAt(fast);
                Integer count = map.get(incomingChar);
                if (count != null) {
                    if (count == 1) {
                        match++;
                    }
                    map.put(incomingChar, count - 1);
                }
            } else {    // deal with outgoing char
                char outgoingChar = lo.charAt(slow);
                Integer count = map.get(outgoingChar);
                if (count != null) {
                    if (count == 0) {
                        match--;
                    }
                    map.put(outgoingChar, count + 1);
                }
                slow++;
            }
            // if all char matched, update result if necessary
            if (match == map.size() && fast - slow + 1 < minLength) {
                minLength = fast - slow + 1;
                globalLeft = slow;
                globalRight = fast;
            }
        }
        return lo.substring(globalLeft, globalRight + 1);
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
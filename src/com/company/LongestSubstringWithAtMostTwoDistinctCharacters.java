package com.company;
import java.util.*;

public class LongestSubstringWithAtMostTwoDistinctCharacters {
    public static void main(String[] args) {
        LongestSubstringWithAtMostTwoDistinctCharacters s = new LongestSubstringWithAtMostTwoDistinctCharacters();
        String input = "eceba";
        System.out.println(s.lengthOfLongestSubstringTwoDistinct(input));   // 3
    }

    // [slow, fast)
    // Map: key - Character, value is count int the sliding window
    // initialize:
    // slow = 0
    // fast = 0
    // maxLength = 0
    // algorithm:
    // case1: if map has less than two keys, update map, fast++
    // case2: if map has two keys, fast is in the map, update map, fast++
    // case3: update map, slow++
    // Time O(n)
    // Space O(1)
    public int lengthOfLongestSubstringTwoDistinct(String input) {
        if (input == null || input.length() == 0) {
            return 0;
        }
        int slow = 0;
        int fast = 0;
        int maxLength = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (fast < input.length()) {
            if (map.size() < 2 || map.containsKey(input.charAt(fast))) {
                int count = map.getOrDefault(input.charAt(fast), 0);
                map.put(input.charAt(fast), count + 1);
                fast++;
                maxLength = Math.max(fast - slow, maxLength);
            } else {
                int count = map.get(input.charAt(slow));
                if (count == 1) {
                    map.remove(input.charAt(slow));
                } else {
                    map.put(input.charAt(slow), count - 1);
                }
                slow++;
            }
        }
        return maxLength;
    }
}

package com.company;
import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithExactlyKTypedCharacters {
    public static void main(String[] args) {
        LongestSubstringWithExactlyKTypedCharacters s = new LongestSubstringWithExactlyKTypedCharacters();
        System.out.println();
        String input = "adaabccaabf";
        System.out.println(s.longest(input, 3));

        input = "aabcc";
        System.out.println(s.longest(input, 3));

        input = "aabcccc";
        System.out.println(s.longest(input, 2));
    }

    // [slow, fast)
    // Map: key - Character, value is count int the sliding window
    // initialize:
    // slow = 0
    // fast = 0
    // maxLength = 0
    // algorithm:
    // case1: if map has less than k keys, update map, fast++
    // case2: if map has k keys, fast is in the map, update map, fast++
    // case3: update map, slow++
    // only update result when map has exactly k keys
    // Time O(n)
    // Space O(1)
    public String longest(String input, int k) {
        if (input == null || input.length() == 0) {
            return input;
        }
        int slow = 0;
        int fast = 0;
        int globalLeft = 0;
        int globalRight = 0;
        int maxLength = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (fast < input.length()) {
            if (map.size() < k || map.containsKey(input.charAt(fast))) {
                int count = map.getOrDefault(input.charAt(fast), 0);
                map.put(input.charAt(fast), count + 1);
                fast++;

                if (map.size() == k && fast - slow > maxLength) {
                    maxLength = fast - slow;
                    globalLeft = slow;
                    globalRight = fast;
                }
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
        return input.substring(globalLeft, globalRight);
    }
}

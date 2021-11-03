package com.company;
import java.util.*;

public class ShortestSubstringWithKTypedCharacters {
    public static void main(String[] args) {
        ShortestSubstringWithKTypedCharacters s = new ShortestSubstringWithKTypedCharacters();

        String input = "";
        System.out.println(s.shortest(input, 3));   // ""
        input = "aabbbccc";
        System.out.println(s.shortest(input, 4));   // ""
        input = "aabcc";
        System.out.println(s.shortest(input, 3));   // "abc"
        input = "aabbbcccc";
        System.out.println(s.shortest(input, 3));   // "abbbc"
        input = "aaaaabbdaaaacccdddddeeffbbbbbbf";
        System.out.println(s.shortest(input, 5));   // "cdddddeeffb"
    }

    // Assumptions:
    // 1. The given string is not null.
    // 2. k >= 0.
    // Method 1: two pointers
    // Time O(n)
    // Space O(n)
    public String shortest(String input, int k) {
        if (input == null || input.length() == 0 || k == 0) {
            return "";
        }
        Map<Character, Integer> map = new HashMap<>();
        int slow = 0;
        int fast = 0;
        int globalMin = Integer.MAX_VALUE;
        int globalLeft = 0;
        while (fast < input.length() || map.size() == k) {
            if (map.size() < k) {
                int count = map.getOrDefault(input.charAt(fast), 0);
                map.put(input.charAt(fast), count + 1);
                fast++;
            } else {
                int count = map.get(input.charAt(slow));
                if (count == 1) {
                    map.remove(input.charAt(slow));
                } else {
                    map.put(input.charAt(slow), count - 1);
                }
                slow++;
            }
            int currMin = fast - slow;
            if (map.size() == k && currMin < globalMin) {
                globalMin = currMin;
                globalLeft = slow;
            }
        }
        return globalMin == Integer.MAX_VALUE ? "" : input.substring(globalLeft, globalLeft + globalMin);
    }

    // Method 2: linear scan
    // Time O(n)
    // Space O(n)
    public String shortestI(String s, int k) {
        if (k == 0) {
            return "";
        }
        // Associative array to store the count of characters
        int count[] = new int[26];
        int currStart = 0;
        int currEnd = 0;
        // Also initialize values for result longest window
        int minLength = Integer.MAX_VALUE;
        int globalLeft = 0;
        int globalRight = 0;
        // Start from the second character and add characters in window according to above explanation
        while (currEnd < s.length()) {
            // Add the character 's[i]' to current window
            if (countDiffChar(count) < k) {
                count[s.charAt(currEnd) - 'a']++;
                currEnd++;
            } else { // If there are k unique characters in current window, remove from left side
                count[s.charAt(currStart) - 'a']--;
                currStart++;
            }
            // Update the max window size if required
            if (countDiffChar(count) == k ) {
                char charLeft = s.charAt(currStart);
                while (currStart < s.length() && s.charAt(currStart + 1) == charLeft) {
                    count[s.charAt(currStart) - 'a']--;
                    currStart++;
                }
                if (currEnd- currStart + 1 < minLength) {
                    minLength = currEnd - currStart + 1;
                    globalLeft = currStart;
                    globalRight = currEnd;
                }
            }
        }
        return s.substring(globalLeft, globalRight);
    }

    private int countDiffChar(int[] count) {
        int num = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                num++;
            }
        }
        return num;
    }
}

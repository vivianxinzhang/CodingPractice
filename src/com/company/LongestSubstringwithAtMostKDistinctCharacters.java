package com.company;
import java.util.*;

public class LongestSubstringwithAtMostKDistinctCharacters {
    public static void main(String[] args) {
        LongestSubstringwithAtMostKDistinctCharacters s = new LongestSubstringwithAtMostKDistinctCharacters();
        String input = "";
        System.out.println(s.lengthOfLongestSubstringKDistinct(input, 1));  // 0

        input = "xiisbqgbtjai";
        System.out.println(s.lengthOfLongestSubstringKDistinct(input, 12));  // 4

        input = "abcedde";
        System.out.println(s.lengthOfLongestSubstringKDistinct(input, 2));  // 4

        input = "eceba";
        System.out.println(s.lengthOfLongestSubstringKDistinct(input, 2));  // 3
    }

    // Time O(n)
    // Space O(1) -- max size of map 26 or 256
    public int lengthOfLongestSubstringKDistinct(String input, int k) {
        Map<Character, Integer> map = new HashMap<>();
        int globalLeft = 0;
        int globalRight = -1;   // must initialize with -1 to guarantee no element in sliding window at the beginning
        int maxLength = 0;
        int fast = -1;  // must initialize with -1 to guarantee no element in sliding window at the beginning
        int slow = 0;
        int numOfDistinctChar = 0;
        while (fast < input.length() - 1) {
            if (map.containsKey(input.charAt(fast + 1))) {
                fast++;
                Integer count = map.get(input.charAt(fast));
                map.put(input.charAt(fast), count + 1);
            } else if (numOfDistinctChar < k) {
                fast++;
                map.put(input.charAt(fast), 1);
                numOfDistinctChar++;
            } else {
                Integer count = map.get(input.charAt(slow));
                if (count == 1) {
                    map.remove(input.charAt(slow));
                    numOfDistinctChar--;
                } else {
                    map.put(input.charAt(slow), count - 1);
                }
                slow++;
            }
            if (numOfDistinctChar <= k && fast - slow + 1 > maxLength) {
                maxLength = fast - slow + 1;
                globalLeft = slow;
                globalRight = fast;
            }
        }
        return maxLength;
    }
}

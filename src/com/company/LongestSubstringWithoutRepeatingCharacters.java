package com.company;
import java.util.*;

public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters s = new LongestSubstringWithoutRepeatingCharacters();
        System.out.println(s.longest("bcdfbd"));
    }

    // Time O(n)
    // Space O(n)
    public int longest(String input) {
        // Write your solution here
        // Assumption: the input string is not null
        // the set contains all distinct characters in the sliding window of [slow, fast)
        Set<Character> distinct = new HashSet<>();
        int slow = 0;
        int fast = 0;
        int longest = 0;
        while (fast < input.length()) {
            if (distinct.contains(input.charAt(fast))) {
                // if there is duplicate character, we need to move the slow pointer
                distinct.remove(input.charAt(slow++));
            } else {
                // if there is no duplicate character, we can slide fast pointer and we have
                // a new sliding window of (slow, fast) containing all distinct characters
                distinct.add(input.charAt(fast++));
                longest = Math.max(longest, fast - slow);
            }
        }
        return longest;
    }
}

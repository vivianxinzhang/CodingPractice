package com.company;
import java.util.*;

// Time O(logk)
// Space O(1)
public class Solution {
    public String decompress(String input) {
        // Write your solution here
        if (input == null || input.length() == 0) {
            return input;
        }
        Deque<Character> stack = new ArrayDeque<>();
        int i = 0;
        StringBuilder s = new StringBuilder();
        while (i < input.length()) {
            if (input.charAt('i') == '[') {
                stack.push(input.charAt('i'));
                i++;
            } else if (input.charAt(i) == '|') {
                int count = stack.pop();
                for (int j = 0; j < count; j++) {
                    while (input.charAt(i) != ']') {
                        s.append(input.charAt(i));
                        i++;
                    }
                }
            }
        }
        return s.toString();
    }
}


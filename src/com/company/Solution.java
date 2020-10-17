package com.company;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        String input = "   I     love MTV   ";
        System.out.println(s.removeSpaces(input));
    }

    // Time O(n)
    // Space O(n)
    // Time O(n)
    // Space O(n)
    public String deDup(String input) {
        // Write your solution here
        if (input == null || input.length() <= 1) {
            return input;
        }
        char[] array = input.toCharArray();
        int slow = 1;
        int fast = 1;
        while (fast < array.length) {
            if (array[fast] == array[slow - 1]) {
                while (fast < array.length && array[fast] == array[slow - 1]) {
                    fast++;
                }
            } else {
                array[slow++] = array[fast++];
            }
        }
        return new String(array, 0, slow);
    }

    public String removeSpaces(String input) {
        // Write your solution here
        if (input == null || input.length() == 0) {
            return input;
        }
        char[] array = input.toCharArray();
        int slow = 0;
        int fast = 0;
        while (fast < array.length) {
            if (array[fast] == ' ' && (fast == 0 || array[fast - 1] == ' ')) {
                fast++;
            } else {
                array[slow] = array[fast];
                slow++;
                fast++;
            }
        }
        if (slow > 0 && array[slow - 1] == ' ') {
            slow--;
        }
        return new String(array, 0, slow);
    }

    public String remove(String input, String target) {
        // Write your solution here
        if (input == null || input.length() == 0 || target == null || target.length() == 0) {
            return input;
        }
        char[] array = input.toCharArray();
        Set<Character> charsNeedRemove = getCharsNeedRemove(target);
        int slow = 0;
        int fast = 0;
        while (fast < array.length) {
            if (!charsNeedRemove.contains(array[fast])) {
                array[slow] = array[fast];
                slow++;
            }
            fast++;
        }
        return new String(array, 0, slow);
    }

    private Set<Character> getCharsNeedRemove(String target) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < target.length(); i++) {
            set.add(target.charAt(i));
        }
        return set;
    }
}
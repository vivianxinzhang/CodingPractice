package com.company;
import java.util.*;

public class RemoveCertainCharacters {
    public static void main(String[] args) {
        System.out.println("main");
        RemoveCertainCharacters s = new RemoveCertainCharacters();

        String input = "abcdefg";
        String target = "af";
        System.out.println(s.remove(input, target));
    }
    // input length n, target length m
    // Time average O(m + n + n)    worst case O(m^2 + mn + n)
    // Space O(n)
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

    // Time average O(m + n + n)    worst case O(m^2 + mn + n)
    // input length n, target length m
    // Space O(n)
    // Assumptions: input and t are not null
    public String removeI(String input, String t) {
        if (input == null || input.length() == 0 || t == null || t.length() == 0) {
            return input;
        }
        // Write your solution here
        // we need to know how to solve this problem with inplace way
        // usually we can convert the immutable String to char[]
        char[] array = input.toCharArray();
        // get set of all distinct characters in t so that lookup operation
        // will be efficient
        Set<Character> charsNeedRemove = getCharsNeedRemove(t);
        // slow: [0, slow) contains the valid result
        // fast: [fast, array.length) is the area to explore
        int slow = 0;
        for (int fast = 0; fast < array.length; fast++) {
            // the exploring character can only be put into valid area
            // if it is not in the set
            if (!charsNeedRemove.contains(array[fast])) {
                array[slow++] = array[fast];
            }
        }
        return new String(array, 0, slow);
    }
}

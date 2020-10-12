package com.company;
import java.util.*;

public class AllPermutationsI {
    // DFS solution with swapping
    // Time O(n! * n)
    // Space O(n)
    public List<String> permutations(String input) {
        // Write your solution here
        List<String> result = new ArrayList<>();
        char[] array = input.toCharArray();
        helper(array, 0, result);
        return result;
    }

    // choose the character to be a the position of "index"
    // all the already chosen position are (0, index - 1)
    // all the candidate characters can be at position "index"
    // are in the sub-array of (index, array.length - 1)
    private void helper(char[] array, int index, List<String> result) {
        // terminate condition:
        // only when we have already chosen the characters for all the positions,
        // we can have a complete permutation
        if (index == array.length) {
            result.add(new String(array));
            return;
        }
        // all the possible characters could be placed at index are
        // the characters in the sub-array (index, array.length - 1)
        for (int i = index; i < array.length; i++) {
            swap(array, index, i);
            helper(array, index + 1, result);
            // remember to swap back when back track to previous level
            swap(array, index, i);
        }
    }

    private void swap(char[] array, int i, int j) {
        char tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}


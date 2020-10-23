package com.company;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] array = new int[] {5,3,4,2,1,1,2,1,8,4,4,9,13,5,8};
        System.out.println(s.permutations("aba"));
    }

    // Time O(n!)
    // Space O(n^2)
    public List<String> permutations(String input) {
        // Write your solution here
        List<String> result = new ArrayList<>();
        if (input == null || input.length() == 0) {
            return result;
        }
        char[] array = input.toCharArray();
        dfs(array, 0, result);
        return result;
    }

    private void dfs(char[] array, int index, List<String> result) {
        if (index == array.length) {
            result.add(new String(array));
            return;
        }
        Set<Character> used = new HashSet<>();
        for (int i = index; i < array.length; i++) {
            if (used.add(array[i])) {
                swap(array, i, index);
                dfs(array, index + 1, result);
                swap(array, i, index);
            }
        }
    }

    private void swap(char[] array, int i, int j) {
        char tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}



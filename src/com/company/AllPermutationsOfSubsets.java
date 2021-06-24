package com.company;
import java.util.*;

public class AllPermutationsOfSubsets {
    public static void main(String[] args) {
        AllPermutationsOfSubsets s = new AllPermutationsOfSubsets();
        System.out.println(s.allPermutationsOfSubsets("a"));
        System.out.println(s.allPermutationsOfSubsets("ab"));
        System.out.println(s.allPermutationsOfSubsets("abc"));
    }

    // Assumption:
    // 1. input string is not null
    // 2. no duplicate characters in input
    // Time O(n!*n)
    // Space O(n)
    public List<String> allPermutationsOfSubsets(String set) {
        List<String> result = new ArrayList<>();
        if (set == null) {
            return result;
        }
        char[] array = set.toCharArray();
        dfs(array, 0, result);
        return result;
    }

    private void dfs(char[] array, int index, List<String> result) {
        result.add(new String(array, 0, index));
        if (index == array.length) {
            return;
        }
        for (int i = index; i < array.length; i++) {
            swap(array, i, index);
            dfs(array, index + 1, result);
            swap(array, i, index);
        }
    }

    private void swap(char[] array, int i, int j) {
        char tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}

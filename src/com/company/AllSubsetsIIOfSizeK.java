package com.company;
import java.util.*;

public class AllSubsetsIIOfSizeK {
    public static void main(String[] args) {
        AllSubsetsIIOfSizeK s = new AllSubsetsIIOfSizeK();

        System.out.println(s.subSetsIIOfSizeK("xak", 2));   // expected:<["ak", "ax", "kx"]> but was:<["ak", "ax", "xk"]>

        System.out.println(s.subSetsIIOfSizeK("cba", 2));

        System.out.println(s.subSetsIIOfSizeK("bab", 2));

        System.out.println(s.subSetsIIOfSizeK("abab", 2));
    }

    // Method 1:
    // DFS solution select and not select
    // Time O(n! * k)
    // Space O(k)
    public List<String> subSetsIIOfSizeKI(String set, int k) {
        // Write your solution here
        List<String> result = new ArrayList<>();
        if (set == null) {
            return result;
        }
        char[] array = set.toCharArray();
        // Make sure the multi-set is sorted so that can dedup.
        Arrays.sort(array);
        StringBuilder sb = new StringBuilder();
        dfs(array, 0, k, sb, result);
        return result;
    }

    // index: at current level, determine if the element at "index" should be included
    // in the subset or not.
    private void dfs(char[] array, int index, int k, StringBuilder sb, List<String> result) {
        if (sb.length() == k) {
            result.add(sb.toString());
            return;
        }
        if (index == array.length) {
            return;
        }
        // add
        sb.append(array[index]);
        dfs(array, index + 1, k, sb, result);
        sb.deleteCharAt(sb.length() - 1);
        // not add
        // skip all the consecutive and duplicate elements
        while (index < array.length - 1 && array[index + 1] == array[index]) {
            index++;
        }
        dfs(array, index + 1, k, sb, result);
    }


    private void swap(char[] array, int i, int j) {
        char tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    // Method 2:
    // DFS solution
    // determine how many to select for a type of char in each level
    // Time O(2^n * k)
    // Space O(k)
    public List<String> subSetsIIOfSizeK(String set, int k) {
        // Write your solution here
        List<String> result = new ArrayList<>();
        if (set == null) {
            return result;
        }
        Map<Character, Integer> charCount = count(set);
        List<Character> uniqueChars = getUniqueChars(charCount);
        Collections.sort(uniqueChars);
        StringBuilder sb = new StringBuilder();
        helper(uniqueChars,0, sb, charCount, k, result);
        return result;
    }

    private List<Character> getUniqueChars(Map<Character, Integer> map) {
        List<Character> list = new ArrayList<>();
        for (Character ch : map.keySet()) {
            list.add(ch);
        }
        return list;
    }

    private void helper(List<Character> uniqueChars, int index, StringBuilder sb, Map<Character, Integer> map, int k, List<String> result) {
        if (sb.length() == k) {
            result.add(sb.toString());
            return;
        }
        if (index == map.size()) {
            return;
        }
        // 填0个
        helper(uniqueChars, index + 1, sb, map, k, result);
        // 填 1， 2，... 个
        char cur = uniqueChars.get(index);
        for (int i = 0; i < map.get(cur); i++) {
            sb.append(cur);
            helper(uniqueChars, index + 1, sb, map, k, result);
        }
        for (int i = 0; i < map.get(cur); i++) {
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    private Map<Character, Integer> count(String set) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : set.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }
}

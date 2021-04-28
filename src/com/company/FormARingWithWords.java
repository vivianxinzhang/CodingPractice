package com.company;

public class FormARingWithWords {
    public static void main(String[] args) {
        FormARingWithWords s = new FormARingWithWords();

        String[] input = {"a","bc","c","ddb","cd","ba","aad"};
        System.out.println(s.formRing(input));  // false

        input = new String[] {"aab","aa"};
        System.out.println(s.formRing(input));  // false

        input = new String[] {"aab"};
        System.out.println(s.formRing(input));  // false

        input = new String[] {"a"};
        System.out.println(s.formRing(input));  // true
    }


    // Assumptions:
    // 1. The given array is not null or empty.
    // 2. None of the strings in the array is null or empty.
    // Time O(n!)
    // Space O(n)
    public boolean formRing(String[] input) {
        return dfs(input, 1);
    }

    private boolean dfs(String[] input, int index) {
        if (index == input.length) {
            String last = input[input.length - 1];
            String first = input[0];
            if (last.charAt(last.length() - 1) == first.charAt(0)) {
                return true;
            }
            return false;
        }
        String pre = input[index - 1];
        for (int i = index; i < input.length; i++) {
            String cur = input[i];
            if (cur.charAt(0) == pre.charAt(pre.length() - 1)) {
                swap(input, index, i);
                if (dfs(input, index + 1)) {
                    return true;
                }
                swap(input, index, i);
            }
        }
        return false;
    }

    private void swap(String[] input, int i, int j) {
        String tmp = input[i];
        input[i] = input[j];
        input[j] = tmp;
    }
}

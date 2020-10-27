package com.company;

public class FormARingWithWords {
    public static void main(String[] args) {
        FormARingWithWords s = new FormARingWithWords();
        String[] input = {"a","bc","c","ddb","cd","ba","aad"};
        System.out.println(s.formRing(input));

        input = new String[] {"aab","aa"};
        System.out.println(s.formRing(input));

        input = new String[] {"aab"};
        System.out.println(s.formRing(input));

        input = new String[] {"a"};
        System.out.println(s.formRing(input));
    }

    // Time O(n!)
    // Space O(n)
    public boolean formRing(String[] input) {
        // Write your solution here
        if (input == null || input.length == 0) {
            return true;
        }
        if (input.length == 1) {
            return input[0].charAt(input[0].length() - 1) == input[0].charAt(0);
        }
        boolean[] canFormRing = new boolean[1];
        for (int i = 1; i < input.length; i++) {
            if (input[i].charAt(0) == input[0].charAt(input[0].length() - 1)) {
                dfs(input, 1, canFormRing);
            }
        }
        return canFormRing[0];
    }

    private void dfs(String[] input, int index, boolean[] canFormRing) {
        if (index == input.length) {
            char endChar = input[index - 1].charAt(input[index - 1].length() - 1);
            if (endChar == input[0].charAt(0)) {
                canFormRing[0] = true;
            }
            return;
        }
        char preEnd = input[index - 1].charAt(input[index - 1].length() - 1);
        for (int i = index; i < input.length; i++) {
            char currStart = input[i].charAt(0);
            if (currStart == preEnd) {
                swap(input, i, index);
                dfs(input, index + 1, canFormRing);
                swap(input, i, index);
            }
        }
    }

    private void swap(String[] input, int i, int j) {
        String tmp = input[i];
        input[i] = input[j];
        input[j] = tmp;
    }
}

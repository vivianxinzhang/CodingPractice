package com.company;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        String input = "   I     love MTV   ";
        System.out.println(s.removeSpaces(input));
    }


    // level is the current level to try
    public void permutation(char[] input, int index) {
        // base case
        if (index == input.length) {
            System.out.println(input);
            return;
        }
        Set<Character> used = new HashSet<>();
        for (int i = index; i < input.length; i++) {
            if (used.contains(input[i])) {
                continue;
            }
            used.add(input[i]);
            swap(input, index, i);
            permutation(input, index + 1);
            swap(input, index, i);
        }
    }

    private void swap(char[] input, int i, int j) {
        char tmp = input[i];
        input[i] = input[j];
        input[j] = tmp;
    }

    void convert(char[] a, int left, int right) {
        // base case
        if (right - left <= 1) {
            return;
        }
        int size = right - left + 1;    // how many elements in the section
        int mid = left + size / 2;
        int leftmid = left + size / 4;
        int rightmid = left + size * 3 / 4;

        reverse(a, leftmid, mid - 1);   // I love yahoo trick is here!!
        reverse(a, mid, rightmid - 1);
        reverse(a, leftmid, rightmid - 1);  // DE123 --> 123DE

        convert(a, left, left + 2 * (leftmid - left) - 1);
        convert(a, left + 2 * (leftmid - left), right);
    }

    private void reverse(char[] a, int leftmid, int i) {
    }

    String reverse(String input) {
        if (input.length() <= 1) {
            return input;
        }
        return reverse(input.substring(1)) + input.charAt(0);
    }

    // Time O(n)
    // Space O(n)
    // Time O(mn)
    // Space O(1)
    public int strstr(String large, String small) {
        if (large == null || small == null || large.length() < small.length()) {
            return -1;
        }
        if (small.length() == 0) {
            return 0;
        }
        for (int i = 0; i <= large.length() - small.length(); i++) {
            int j = 0;
            while (j < small.length() && large.charAt(i + j) == small.charAt(j)) {
                j++;
            }
            if (j == small.length()) {
                return i;
            }
        }
        return -1;
    }

    private boolean equals(String large, int index, String small) {
        for (int i = 0; i < small.length(); i++) {
            if (large.charAt(index + i) != small.charAt(i)) {
                return false;
            }
        }
        return true;
    }
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
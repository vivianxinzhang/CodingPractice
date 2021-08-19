package com.company;

public class LongestCommonPrefix {
    public static void main(String[] args) {
        LongestCommonPrefix s = new LongestCommonPrefix();

        String[] strs = {""};
        System.out.println(s.longestCommonPrefix(strs));    // ""

        strs = new String[]{"abcd","abcdr","abcdr","abcdrf","abc","a"};
        System.out.println(s.longestCommonPrefix(strs));    // a
    }

    // Time O(mn)
    // Space O(1)
    public String longestCommonPrefix(String[] strs) {
        int index = 0;
        while (index < strs[0].length()) {
            if (existInAllRows(strs, index, strs[0].charAt(index))) {
                index++;
            } else {
                break;
            }
        }
        return strs[0].substring(0, index);
    }

    private boolean existInAllRows(String[] array, int index, char ch) {
        for (int row = 1; row < array.length; row++) {
            if (index >= array[row].length() || array[row].charAt(index) != ch) {
                return false;
            }
        }
        return true;
    }
}

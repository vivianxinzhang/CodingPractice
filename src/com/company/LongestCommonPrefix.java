package com.company;

public class LongestCommonPrefix {
    public static void main(String[] args) {
        LongestCommonPrefix s = new LongestCommonPrefix();
        String[] strs = {""};
        System.out.println(s.longestCommonPrefix(strs));

        strs = new String[]{"abcd","abcdr","abcdr","abcdrf","abc","a"};
        System.out.println(s.longestCommonPrefix(strs));
    }

    // Time O(mn)
    // Space O(1)
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs[0].length(); i++) {
            if (!existInAllRows(strs, i, strs[0].charAt(i))) {
                break;
            }
            sb.append(strs[0].charAt(i));
        }
        return sb.toString();
    }

    private boolean existInAllRows(String[] strs, int col, char c) {
        for (int i = 1; i < strs.length; i++) {
            if (col == strs[i].length() || strs[i].charAt(col) != c) {
                return false;
            }
        }
        return true;
    }
}

package com.company;

public class InterleaveStrings {
    public static void main(String[] args) {
        InterleaveStrings s = new InterleaveStrings();
        System.out.println(s.canMerge("acd", "be", "abcde"));

        System.out.println(s.canMerge("abgcd", "bebgf", "abgcdbebg"));

        System.out.println(s.canMerge("adc", "be", "abcde"));
    }

    // Time O(n)
    // Space O(1)
    public boolean canMerge(String a, String b, String c) {
        if (a.length() + b.length() != c.length()) {
            return false;
        }
        boolean[] canMerge = new boolean[1];
        dfs(a, 0, b, 0, c, 0, canMerge);
        return canMerge[0];
    }

    private void dfs(String a, int i, String b, int j, String c, int k, boolean[] canMerge) {
        if (k == c.length()) {
            canMerge[0] = true;
            return;
        }
        if (i < a.length() && a.charAt(i) == c.charAt(k)) {
            i++;
            k++;
            dfs(a, i, b, j, c, k, canMerge);
            i--;
            k--;
        }
        if (j < b.length() && b.charAt(j) == c.charAt(k)) {
            j++;
            k++;
            dfs(a, i, b, j, c, k, canMerge);
            j--;
            k--;
        }
    }


    // Time O(n)
    // Space O(1)
    public boolean canMergeI(String a, String b, String c) {
        // Write your solution here
        if (a.length() + b.length() != c.length()) {
            return false;
        }
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < a.length() && j < b.length()) {
            if (c.charAt(k) != a.charAt(i) && c.charAt(k) != b.charAt(j)) {
                return false;
            } else if (c.charAt(k) == a.charAt(i)) {
                i++;
            } else if (c.charAt(k) == b.charAt(j)) {
                j++;
            }
            k++;
        }
        while (i < a.length()) {
            if (a.charAt(i) != c.charAt(k)) {
                return false;
            }
            i++;
            k++;
        }
        while (j < b.length()) {
            if (b.charAt(j) != c.charAt(k)) {
                return false;
            }
            j++;
            k++;
        }
        return true;
    }
}

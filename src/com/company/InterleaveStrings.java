package com.company;

public class InterleaveStrings {
    public static void main(String[] args) {
        InterleaveStrings s = new InterleaveStrings();

        System.out.println(s.canMerge("", "", ""));     // true

        System.out.println(s.canMerge("acd", "be", "abcde"));   // true

        System.out.println(s.canMerge("abgcd", "bebgf", "abgcdbebg"));  // false

        System.out.println(s.canMerge("adc", "be", "abcde"));   // false
    }

    // Assumptions: none of A, B, C is null
    // DP
    // M[i][j] represents if the first i characters [0, i-1] of str1
    // and the first j characters [0, j-1] of str2 can
    // form the first (i + j) characters [0, i+j-1] in str3
    // Base case:
    // M[0][0] = true
    // M[i][0] = true  if M[i-1][0] == true && str1[i-1] == str3[i-1]
    //           false otherwise
    // M[0][j] = true  if M[0][j-1] == true && str2[j-1] == str3[j-1]
    //           false otherwise
    // Induction rule:
    // M[i][j]
    // Case 1: match str1[i-1] == str3[i+j-1] && M[i-1][j] == true
    // Case 2: match str2[j-1] == str3[i+j-1] && M[i][j-1] == true
    // M[i][j] = case 1 || case 2
    // Time O(mn)
    // Space O(mn)
    public boolean canMerge(String str1, String str2, String str3) {
        if (str1.length() + str2.length() != str3.length()) {
            return false;
        }
        boolean[][] M = new boolean[str1.length() + 1][str2.length() + 1];
        for (int idx1 = 0; idx1 <= str1.length(); idx1++) {
            for (int idx2 = 0; idx2 <= str2.length(); idx2++) {
                int idx3 = idx1 + idx2;
                if (idx1 == 0 && idx2 == 0) {
                    M[idx1][idx2] = true;
                } else if (idx1 == 0) {
                    if (str3.charAt(idx3 - 1) == str2.charAt(idx2 - 1)) {
                        M[idx1][idx2] = M[idx1][idx2 - 1];
                    }
                } else if (idx2 == 0) {
                    if (str3.charAt(idx3 - 1) == str1.charAt(idx1 - 1)) {
                        M[idx1][idx2] = M[idx1 - 1][idx2];
                    }
                } else {
                    M[idx1][idx2] = (str1.charAt(idx1 - 1) == str3.charAt(idx3 - 1) ? M[idx1 - 1][idx2] : false)
                            || (str2.charAt(idx2 - 1) == str3.charAt(idx3 - 1) ? M[idx1][idx2 - 1] : false);
                }
            }
        }
        return M[str1.length()][str2.length()];
    }

    // two pointers
    // Time O(n)
    // Space O(1)
    public boolean canMergeIII(String a, String b, String c) {
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

    // Recursion:
    // Time: O(2^n)
    // Space: O(n)
    public boolean canMergeII(String a, String b, String c) {
        return helper(a, b, c, 0, 0, 0);
    }

    private boolean helper(String str1, String str2, String str3, int idx1, int idx2, int idx3) {
        if (idx1 == str1.length() && idx2 == str2.length() && idx3 == str3.length()) {
            return true;
        }
        if (idx3 == str3.length()) {
            return false;
        }

        return (idx1 < str1.length() && str1.charAt(idx1) == str3.charAt(idx3) && helper(str1, str2, str3, idx1 + 1, idx2, idx3 + 1))
                || (idx2 < str2.length() && str2.charAt(idx2) == str3.charAt(idx3) && helper(str1, str2, str3, idx1, idx2 + 1, idx3 + 1));
    }

    // DFS: each level match one character in c
    // branching factor at most 2    levels - n
    // Time O(2^n)
    // Space O(n)
    public boolean canMergeI(String a, String b, String c) {
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
        // match with a[i]
        if (i < a.length() && a.charAt(i) == c.charAt(k)) {
            i++;
            k++;
            dfs(a, i, b, j, c, k, canMerge);
            i--;
            k--;
        }
        // match with b[j]
        if (j < b.length() && b.charAt(j) == c.charAt(k)) {
            j++;
            k++;
            dfs(a, i, b, j, c, k, canMerge);
            j--;
            k--;
        }
    }
}

package com.company;

import java.util.*;

public class DictionaryWordI {
    public static void main(String[] args) {
        DictionaryWordI s = new DictionaryWordI();
        String input = "bcdef";
        String[] dict = new String[]{"abc", "bcd", "def"};
        System.out.println(s.canBreak(input, dict));    // false

        input = "abccdde";
        dict = new String[]{"abc", "ab", "cd", "de", "def"};
        System.out.println(s.canBreak(input, dict));    // true
    }

    // Assumption:
    // 1. input is not null or empty
    // 2. dict is not null and dict is not empty
    // 3. all the strings in dict are not null or empty
    // Time O(n^3)
    // Space O(n)
    public boolean canBreak(String input, String[] dict) {
        boolean[] M = new boolean[input.length()];
        Set<String> set = toSet(dict);
        for (int i = 0; i < input.length(); i++) {
            if (set.contains(input.substring(0, i + 1))) {
                M[i] = true;
                continue;
            }
            for (int j = 0; j < i; j++) {
                if (M[j] && set.contains(input.substring(j + 1, i + 1))) {
                    M[i] = true;
                    break;
                }
            }
        }
        return M[input.length() - 1];
    }

    private Set<String> toSet(String[] dict) {
        Set<String> set = new HashSet<>();
        for (String s : dict) {
            set.add(s);
        }
        return set;
    }

    // M[i] represents 前i个字母是否能切成单词的concatenation: true or false
    //      whether index 0 to i-1, substring(0, i) can break to words in the dictionary
    // M[i] represents if the first i chars can be composed by concatenating words from the given dictionary
    // [0, ... , i-1]
    // Base case:
    // M[0] = true	不用切 就是0个单词的concatenation i = 0 表示空串   i = 1 前1个字母
    // Induction rule:
    // M[i] = true if for any possible j(0 < j < i): M[j] == true && substring[j, i-1] is in the dict
    //        j is the starting index of right part    [0,j-1]                [j, i-1]
    // return M[input.length]
    // Time O(n^3) ? worst case n*n*(m+n)? dict.contains O(m)? substring O(n)?
    // Space O(n)
    public boolean canBreakII(String input, String[] dict) {
        // Assumption:
        // input is not null or empty
        // dict is not null and dict is not empty,
        // all the strings in dict are not null or empty
        Set<String> dictSet = toSet(dict);
        // Notice: sometimes it will be handy to have such index matching,
        // canBreak[i] represents index (i-1) in input, also
        // can represent the substring [0, i)
        // M[i] represents if the first i characters in s can break into words in dict
        // i.e. substring [0, i) not including i,
        // can break into words in the dict (index 0 to i - 1 from input)
        boolean[] canBreak = new boolean[input.length() + 1];
        canBreak[0] = true;
        for (int i = 1; i < canBreak.length; i++) {
            for (int j = 0; j < i; j++) {
                // j == 0 canBreak[j] represents empty string
                // input.substring(0, i) represents substring [0, i-1]  which is the first i characters
                if (canBreak[j] && dictSet.contains(input.substring(j, i))) {
                    canBreak[i] = true;
                    break;
                }
            }
        }
        return canBreak[canBreak.length - 1];
    }

    // Time O(n^3)
    // Space O(n)
    public boolean canBreakI(String input, String[] dict) {
        // Assumption:
        // input is not null or empty
        // dict is not null and dict is not empty,
        // all the strings in dict are not null or empty
        Set<String> dictSet = toSet(dict);
        // Notice: sometimes it will be handy to have such index matching,
        // canBreak[i] represents index (i-1) in input, also
        // can represent the substring [0, i)
        // M[i] represents if the first i characters in s can break into words in dict
        // i.e. substring [0, i) not including i,
        // can break into words in the dict (index 0 to i - 1 from input)
        boolean[] canBreak = new boolean[input.length() + 1];
        for (int i = 1; i < canBreak.length; i++) {
            for (int j = 0; j < i; j++) {
                // j == 0 canBreak[j] represents empty string
                // input.substring(j, i) represents substring [0, i-1]  which is the first i characters
                if (canBreak[j] && dictSet.contains(input.substring(j, i))) {
                    canBreak[i] = true;
                    break;
                }
            }
        }
        return canBreak[canBreak.length - 1];
    }
}

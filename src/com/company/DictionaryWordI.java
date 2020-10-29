package com.company;
import java.util.*;


// Assumption:
// 1. input is not null or empty
// 2. dict is not null and dict is not empty
// 3. all the strings in dict are not null or empty
// M[i] represents 前i个字母是否能切成单词的concatenation: true or false
//      whether index 0 to i-1, substring(0, i) can break to words in the dictionary
// Base case:
// M[0] = true	不用切 就是0个单词的concatenation i = 0 表示空串   i = 1 前1个字母
// Induction rule:
// M[1] = whether “b” is in the dictionary = false
// M[2] = whether “bo” can be cut into words
// 左边是左大段 需要查表得到
// Option 1: “bo” , then whether “bo” is a word	=> false
// Option 2: “b | o”, then M[i] and “o” is a word concatenation	=> false
// M[2] = Option 1 or Option 2 => false
// M[3] = whether “bob” can be cut into words
// Option 1: “bob”, then whether  “bob” is a word => true
// Option 2: “bo | b”, then M[2] and “b” is a word => false
// bo之间可能切也可能不切 都包括了 bo通过查表拿到
// Option 3: “b | ob”, then M[1] and “ob” is a word => false
// M[3] = Option 1 or Option 2 or Option 3 => true
// Time O(n^3) ? worst case n*n*(m+n)? dict.contains O(m)? substring O(n)?
// Space O(n)
public class DictionaryWordI {
    // Method 1:
    // Time O(n^3)
    // Space O(n)
    public boolean canBreak(String input, String[] dict) {
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
                if (canBreak[j] && dictSet.contains(input.substring(j, i))) {
                    canBreak[i] = true;
                    break;
                }
            }
        }
        return canBreak[canBreak.length - 1];
    }

    private Set<String> toSet(String[] dict) {
        Set<String> set = new HashSet<>();
        for (String s : dict) {
            set.add(s);
        }
        return set;
    }
}

package com.company;
import java.util.*;

public class DictionaryWordI {
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

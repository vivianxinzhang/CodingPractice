package com.company;

import java.util.ArrayList;
import java.util.List;

public class ShortestWordDistance {
    public static void main(String[] args) {
        ShortestWordDistance s = new ShortestWordDistance();

        String[] words = new String[] {"practice", "makes", "perfect", "coding", "makes"};
        System.out.println(s.shortestDistance(words, "coding", "practice"));    // 3
        System.out.println(s.shortestDistance(words, "makes", "coding"));       // 1
    }

    // Note:
    // You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
    // O(m*n)  word length m  String[] length n
    // O(1)
    public int shortestDistance(String[] words, String word1, String word2) {
        int minDist = Integer.MAX_VALUE;
        int idx1 = -1;
        int idx2 = -1;
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word == word1) {
                idx1 = i;
            } else if (word == word2) {
                idx2 = i;
            }
            if (idx1 != -1 && idx2 != -1) {
                int curDist = Math.abs(idx1 - idx2);
                minDist = Math.min(minDist, curDist);
            }
        }
        return minDist;
    }

    // Method 1:
    // O(mn + n^2)  word length m  String[] length n
    // O(n)
    public int shortestDistanceI(String[] words, String word1, String word2) {
        // Write your solution here
        List<Integer> s1 = new ArrayList<>();
        List<Integer> s2 = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if (equals(words[i], word1)) {
                s1.add(i);
            } else if (equals(words[i], word2)) {
                s2.add(i);
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < s1.size(); i++) {
            for (int j = 0; j < s2.size(); j++) {
                min = Math.min(min, Math.abs(s1.get(i) - s2.get(j)));
            }
        }
        return min;
    }

    // Time O(m) Space O(1)
    public boolean equals(String s1, String s2) {
        int i = 0, j = 0;
        while (i < s1.length() && j < s2.length()) {
            if (s1.charAt(i) != s2.charAt(j)) {
                return false;
            }
            i++;
            j++;
        }
        return i == s1.length() && j == s2.length();
    }
}

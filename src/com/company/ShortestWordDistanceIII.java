package com.company;

public class ShortestWordDistanceIII {
    public static void main(String[] args) {
        ShortestWordDistanceIII s = new ShortestWordDistanceIII();

        String[] words = new String[] {"practice", "makes", "perfect", "coding", "makes"};
        System.out.println(s.shortestWordDistance(words, "coding", "practice"));    // 3
        System.out.println(s.shortestWordDistance(words, "makes", "coding"));       // 1

        words = new String[] {"the","quick","brown","quick","fox","quick"};
        System.out.println(s.shortestWordDistance(words, "quick", "quick"));    // 2
    }

    // Note: You may assume word1 and word2 are both in the list.
    // O(m*n)  word length m  String[] length n
    // O(1)
    public int shortestWordDistance(String[] words, String word1, String word2) {
        if (word1 == word2) {
            return shortestSameWordDistance(words, word1);
        }
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

    private int shortestSameWordDistance(String[] words, String word1) {
        int preIdx = -1;
        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (words[i] == word1) {
                if (preIdx != -1) {
                    int curDist = i - preIdx;
                    minDist = Math.min(minDist, curDist);
                }
                preIdx = i;
            }
        }
        return minDist;
    }
}

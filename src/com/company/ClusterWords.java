package com.company;
import java.util.*;

public class ClusterWords {
    public char[] chars = getCharArray();
    public void clusterWords(List<String> list) {
        int[] idx = new int[26];
        Arrays.fill(idx, 0);
        idx[25] = list.size() - 1;
        rainbowSort(list, idx);
        return;
    }

    // Method 1:
    // Time O(n)
    // Space O(1)
    private void rainbowSort(List<String> list, int[] idx) {
        if (list == null || list.size() == 0) {
            return;
        }
        while (idx[24] <= idx[25]) {
            if (list.get(idx[24]).charAt(0) == chars[25]) {
                swap(list, idx[24], idx[25]);
                idx[25]--;
            } else {
                int distance = 'y' - list.get(idx[24]).charAt(0);
                for (int i = 0; i < distance; i++) {
                    swap(list, idx[24 - i - 1], idx[24 - i]);
                    idx[24 - i]++;
                }
                idx[24 - distance]++;
            }
        }
    }

    public void swap(List<String> list, int i, int j) {
        String tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }

    private char[] getCharArray() {
        char[] chars = new char[26];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char)('a' + i);
        }
        return chars;
    }
}

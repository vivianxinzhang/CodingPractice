package com.company;
import java.util.*;

// Implementation 2:
// Time O(n)
// Space O(1)
public class ClusterWords2 {
    int[] idx = new int[26];
    private char[] chars = getCharArray();

    public void clusterWords(List<String> list) {
        if (list == null) {
            return;
        }
        rainbowSort(list, idx);
        return;
    }

    private void rainbowSort(List<String> list, int[] idx) {
        if (list == null || list.size() == 0) {
            return;
        }
        while (idx[25] < list.size()) {
            int distance = 'z' - list.get(idx[25]).charAt(0);
            for (int i = 0; i < distance; i++) {
                swap(list, idx[25 - i - 1], idx[25 - i]);
                idx[25 - i]++;
            }
            idx[25 - distance]++;
        }
    }

    private void swap(List<String> list, int i, int j) {
        String tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }

    private char[] getCharArray() {
        char[] chars = new char[26];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) ('a' + i);
        }
        return chars;
    }

    public static void main(String[] args) {
        ClusterWords2 s2 = new ClusterWords2();
        List<String> list = new ArrayList<>();
        list.add("w");
        list.add("f");
        list.add("h");
        list.add("e");
        System.out.println(list);
        s2.clusterWords(list);
        System.out.println(list);
        list.add("a");
        System.out.println(list);
        s2.clusterWords(list);
        System.out.println(list);
    }
}

package com.company;
import java.util.*;

// Implementation 1:
// Time O(n)
// Space O(1)
public class ClusterWords1 {
    private char[] chars = getCharArray();

    public void clusterWords(List<String> list) {
        if (list == null) {
            return;
        }
        int[] idx = initializeIndex(list);
        rainbowSort(list, idx);
        return;
    }

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

    private int[] initializeIndex(List<String> list) {
        int[] idx = new int[26];
        Arrays.fill(idx, 0);
        idx[25] = list.size() - 1;
        return idx;
    }

    public static void main(String[] args) {
        ClusterWords1 s1 = new ClusterWords1();
        List<String> list = new ArrayList<>();
        list.add("w");
        list.add("f");
        list.add("h");
        list.add("e");
        System.out.println(list);
        s1.clusterWords(list);
        System.out.println(list);
        list.add("a");
        System.out.println(list);
        s1.clusterWords(list);
        System.out.println(list);
    }
}

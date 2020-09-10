package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
//        Solution s = new Solution();
        ClusterWords s = new ClusterWords();
        System.out.println(Arrays.toString(s.chars));
        Main m = new Main();
        char a = 'a' + 1;
        List<String> str = new ArrayList<>();
        str.add("w");
        str.add("f");
        str.add("h");
        str.add("e");
        str.add("d");
        str.add("www");
        str.add("aaa");
        str.add("bbb");
        str.add("g");
        str.add("zzz");
        str.add("nnn");
        str.add("ccc");
        System.out.println(str);
        s.clusterWords(str);
        System.out.println(str);
        int[] array = {1, 2, 3, 5, 2};
        int[][] matrix = new int[][] { { 1, 2, 3, 4 }, { 4, 5, 6, 7 }, { 2, 3, 4, 8 } };
//        System.out.println(Arrays.toString(s.quickSort(array)));
    }

    public void swap(List<String> str, int i, int j) {
        String tmp = str.get(i);
        str.set(i, str.get(j));
        str.set(j, tmp);
    }
}

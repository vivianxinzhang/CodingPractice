package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
//        Solution s = new Solution();
        ClusterWords s = new ClusterWords();
        Main m = new Main();
        char a = 'a' + 1;
        List<String> list = new ArrayList<>();
        list.add("w");
        list.add("f");
        list.add("h");
        list.add("e");
        System.out.println(list);
        s.clusterWords(list);
        System.out.println(list);

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

package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
//        Solution s = new Solution();
        ClusterWords1 s1 = new ClusterWords1();
        ClusterWords2 s2 = new ClusterWords2();
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
//        int[] array = {1, 2, 3, 5, 2};
//        int[][] matrix = new int[][] { { 1, 2, 3, 4 }, { 4, 5, 6, 7 }, { 2, 3, 4, 8 } };
//        System.out.println(Arrays.toString(s.quickSort(array)));
    }
}

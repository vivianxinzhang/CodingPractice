package com.company;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        SmallestRange r = new SmallestRange();
        MergeSort m = new MergeSort();
        int[] array = {};
        int[][] matrix = new int[][] {{1, 2}, {3, 4}};
        System.out.println(Arrays.toString(m.mergeSort1(array)));
        System.out.println(Arrays.toString(m.mergeSort2(array)));

    }
}

package com.company;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        MergeKSortedArray m = new MergeKSortedArray();
        SmallestRange s = new SmallestRange();
        MergeSort m = new MergeSort();
        int[] array = {5, 4};
        int[][] matrix = new int[][] {{1, 2}, {3, 4}};
        System.out.println(Arrays.toString(m.mergeSort1(array)));
    }
}

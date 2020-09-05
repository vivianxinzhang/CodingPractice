package com.company;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
	// write your code here
        MergeKSortedArray m = new MergeKSortedArray();
        int[][] matrix = new int[][] {{1, 3, 5}, {2, 4}};
        System.out.println(Arrays.toString(m.merge(matrix)));
    }
}

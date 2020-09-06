package com.company;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
	// write your code here
        MergeKSortedArray m = new MergeKSortedArray();
        SmallestRange s = new SmallestRange();
        int[][] matrix = new int[][] {{1, 2}, {3, 4}};
        System.out.println(Arrays.toString(s.smallestRange(matrix)));
    }
}

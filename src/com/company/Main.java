package com.company;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] array = {1, 2, 3, 5, 2};
        int[][] matrix = new int[][] { { 1, 2, 3, 4 }, { 4, 5, 6, 7 }, { 2, 3, 4, 8 } };
        System.out.println(Arrays.toString(s.quickSort(array)));
    }
}

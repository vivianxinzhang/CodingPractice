package com.company;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] matrix = new int[][]{{1,3,5,7},{2,4,8,9},{3,5,11,15},{6,8,13,18}};
        System.out.println(s.kthSmallest(matrix, 6));
    }
}

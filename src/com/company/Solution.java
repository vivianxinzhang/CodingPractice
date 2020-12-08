package com.company;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] array = new int[] {1, 2, 1, 2, 1};
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        System.out.println(s.majority(array));
        System.out.println();
    }
    public int majority(int[] array) {
        int majorityNum = array[0];
        int count = 1;
        for (int i = 1; i < array.length; i++) {
            if (count == 0) {
                majorityNum = array[i];
                count = 1;
            } else if (array[i] == majorityNum) {
                count++;
            } else {
                count--;
            }
        }
        return majorityNum;
    }
}

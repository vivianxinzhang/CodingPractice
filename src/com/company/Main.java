package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] array = new int[] {1, 2, 5, 6, 10};
        System.out.println(Arrays.toString(s.kClosest(array, 0, 2)));
    }
}
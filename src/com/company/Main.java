package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] array = new int[] {1, 3, 5};
        System.out.println(Arrays.toString(s.kClosest(array, 10, 3)));
    }
}
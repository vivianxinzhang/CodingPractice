package com.company;
import java.util.*;

//
public class Solution {
    public int[] rainbowSortIII(int[] array, int k) {
        // Write your solution here
        if (array == null || array.length == 0) {
            return array;
        }
        int left = 0, right = array.length - 1;
        int leftColor = 1, rightColor = k;
        while (leftColor < rightColor) {
            for (int i = left; i <= right; i++) {
                if (array[i] == leftColor) {
                    swap(array, left, i);
                    left++;
                } else if (array[i] == rightColor) {
                    swap(array, i, right);
                    right--;
                    i--;
                }
            }
            leftColor++;
            rightColor--;
        }
        return array;
    }

    public static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}

package com.company;
import java.util.*;

// Time = O(nk/2) = O(nk)
// Space = O(1)
public class Solution {
    public int[] rainbowSortIII(int[] array, int k) {
        // Write your solution here
        if (array == null || array.length == 0) {
            return array;
        }
        int[] idx = new int[k];
        idx[k - 1] = array.length - 1;
        // need to check if k - 2 is out of bound
        // or can treat k == 1 as corner case
        while (k >= 2 && idx[k - 2] <= idx[k - 1]) {
            if (array[idx[k - 2]] == k) {
                swap(array, idx[k - 2], idx[k - 1]);
                idx[k - 1]--;
            } else {
                int distance = k - 1 - array[idx[k - 2]];
                for (int i = 0; i < distance; i++) {
                    swap(array, idx[k - 3 - i], idx[k - 2 - i]);
                    idx[k - 2 - i]++;
                }
                idx[k - 2 - distance]++;
            }
        }
        return array;
    }

    public static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}

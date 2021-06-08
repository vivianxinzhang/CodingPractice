package com.company;

import java.util.Arrays;

public class RainbowSortII {
    public static void main(String[] args) {
        RainbowSortII s = new RainbowSortII();
        int[] array = new int[] {1, 3, 1, 2, 0};
        System.out.println(Arrays.toString(s.rainbowSortII(array)));
    }

    // Assumptions:
    // The input array is not null.
    // 0  ...  0 |   1 ... 1  |  2 ... 2  |  ... unexplored ...  | 3 ... 3
    //               i           j           k                 t
    // [0, i)  0 |  [i, j) 1  |  [j, k) 2 |  [k, t] unexplored   | (t, n-1]  3
    // Time O(n)
    // Space O(1)
    public int[] rainbowSortII(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        int i = 0;
        int j = 0;
        int k = 0;
        int t = array.length - 1;
        while (k <= t) {
            if (array[k] == 0) {
                swap(array, j, k);
                swap(array, i, j);
                i++;
                j++;
                k++;
            } else if (array[k] == 1) {
                swap(array, j, k);
                j++;
                k++;
            } else if (array[k] == 2) {
                k++;
            } else {
                swap(array, k, t);
                t--;
            }
        }
        return array;
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}

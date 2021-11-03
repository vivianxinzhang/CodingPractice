package com.company;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class MultiplicationWithoutSelf {
    public static void main(String[] args) {
        MultiplicationWithoutSelf s = new MultiplicationWithoutSelf();

        int[] array = new int[] {0, 1, 2, 3, 4};
        System.out.println(Arrays.toString(s.multiply(array)));
        // [24, 0, 0, 0, 0]
        array = new int[] {0, 1, 2, 0, 4};
        System.out.println(Arrays.toString(s.multiply(array)));
        // [0, 0, 0, 0, 0]
        array = new int[] {1, 2, 3, 4};
        System.out.println(Arrays.toString(s.multiply(array)));
        // [24, 12, 8, 6]
    }

    public int[] multiply(int[] array) {
        int countZero = 0;
        int prod = 1;
        for (int num : array) {
            if (num == 0) {
                countZero++;
            } else {
                prod *= num;
            }
        }
        int[] res = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            if (countZero >= 2) {
                res[i] = 0;
            } else if (countZero == 1) {
                if (array[i] == 0) {
                    res[i] = prod;
                } else {
                    res[i] = 0;
                }
            } else {
                res[i] = prod / array[i];
            }
        }
        return res;
    }
}

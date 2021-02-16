package com.company;

import java.util.Arrays;

public class ArrayDeduplicationI {
    public static void main(String[] args) {
        ArrayDeduplicationI s = new ArrayDeduplicationI();
        int[] array = new int[] {};
        System.out.println(Arrays.toString(s.dedup(array)));

        array = new int[] {1, 2, 2, 3, 3, 3};
        System.out.println(Arrays.toString(s.dedup(array)));
    }

    // 1 2 2 3 3 3
    // [0, slow) elements to keep
    // [0, fast) elements have already been checked
    // fast points to the next element going to be processed
    // Assumptions: The array is not null
    // Time O(n)
    // Space O(1)
    public int[] dedup(int[] array) {
        if (array == null || array.length == 0) {
            return array;
        }
        int slow = 0;
        int fast = 0;
        while (fast < array.length) {
            if (slow == 0 || array[fast] != array[slow - 1]) {
                array[slow++] = array[fast++];
            } else {
                while (fast < array.length && array[fast] == array[slow - 1]) {
                    fast++;
                }
            }
        }
        return Arrays.copyOf(array, slow);
    }
}

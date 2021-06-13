package com.company;

import java.util.Arrays;

public class ArrayDeduplicationII {
    public static void main(String[] args) {
        ArrayDeduplicationII s = new ArrayDeduplicationII();
        int[] array = new int[] {};
        System.out.println(Arrays.toString(s.dedup(array)));    // []

        array = new int[] {1, 2, 2, 3, 3, 3};
        System.out.println(Arrays.toString(s.dedup(array)));    // [1, 2, 2, 3, 3]
    }

    // 1, 2, 2, 3, 3, 3
    // 1, 2, 2, 3, 3
    // Assumptions: The given array is not null
    // [0, slow - 1] numbers to keep amazon the numbers that have already been checked
    // [0, fast - 1] numbers have been checked
    // fast points to the next number to check
    // Time O(n)
    // Space O(1)
    public int[] dedup(int[] array) {
        if (array == null || array.length <= 2) {
            return array;
        }
        int slow = 2;
        int fast = 2;
        while (fast < array.length) {
            if (array[fast] != array[slow - 2]) {
                array[slow++] = array[fast++];
            } else {
                fast++;
            }
        }
        return Arrays.copyOf(array, slow);
    }
}

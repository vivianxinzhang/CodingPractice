package com.company;

import java.util.Arrays;

public class MoveZeros {
    public static void main(String[] args) {
        MoveZeros s = new MoveZeros();

        int[] array = new int[] {0, 1, 2, 0, 3};
        s.moveZeroes(array);
        System.out.println(Arrays.toString(array));
        // [1, 2, 3, 0, 0]
    }

    // Traverse the given array ‘arr’ from left to right. While traversing, maintain count of non-zero elements in array.
    // Let the count be ‘count’. For every non-zero element arr[i], put the element at ‘arr[count]’ and increment ‘count’.
    // After complete traversal, all non-zero elements have already been shifted to front end and ‘count’ is set as index of first 0.
    // Now all we need to do is that run a loop which makes all elements zero from ‘count’ till end of the array.
    // Time O(n)
    // Space O(1)
    public int[] moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int count = 0;  // count of non-zero elements
        // Traverse the array. If element encountered is
        // non-zero, then replace the element at index 'count'
        // with this element
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[count] = nums[i];
                count++;    // increment count for every non-zero element
            }
        }
        // Now all non-zero elements have been shifted to
        // front and 'count' is set as index of first 0.
        // Make all elements 0 from count to end.
        while (count < nums.length) {
            nums[count] = 0;
            count++;
        }
        return nums;
    }
}

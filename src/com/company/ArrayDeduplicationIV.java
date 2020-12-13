package com.company;
import java.util.Arrays;

public class ArrayDeduplicationIV {
    public static void main(String[] args) {
        ArrayDeduplicationIV s = new ArrayDeduplicationIV();
        int[] array = new int[] {1, 2, 3, 3, 3, 2, 2};  // 1
        System.out.println(Arrays.toString(s.dedup(array)));
    }

    // Assumptions
    // The given array is not null
    // Data Structure
    // slow: all elements to the left side of the slow (including slow) pointer
    // are the results to keep so far for the elements that have been processed.
    // fast: the current index being processed.
    // (all elements to the right side of the fast pointer have not been processed)
    // Algorithm
    // Initialize: slow = 1, fast = 1
    // Case 1: a[fast] != a[slow], copy, slow++, array[slow] = array[fast], fast++
    // Case 2: fast++ until a[fast] != a[slow], slow--
    // Time O(n)
    // Space O(n)
    public int[] dedup(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        int slow = -1;
        int fast = 0;
        while (fast < array.length) {
            // we are using the left part of the original array as a stack.
            // and the top element's index is end. if the stack is empty (end == -1),
            // we just push the element into the stack, or if the element is not the same
            // as the top element of the stack, we can push the element into the stack as well
            if (slow == -1 || array[fast] != array[slow]) {
                array[++slow] = array[fast++];
            } else {
                // otherwise, we ignore all consecutive duplicates and remove the
                // top element of the stack.
                while (fast < array.length && array[fast] == array[slow]) {
                    fast++;
                }
                slow--;
            }
        }
        return Arrays.copyOf(array, slow + 1);
    }
}

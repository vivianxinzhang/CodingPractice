package com.company;
import java.util.Arrays;

public class ArrayDeduplicationIII {
    public static void main(String[] args) {
        ArrayDeduplicationIII s = new ArrayDeduplicationIII();
        int[] array = new int[] {};
        System.out.println(Arrays.toString(s.dedup(array)));

        array = new int[] {1, 2, 2, 3, 3, 3};
        System.out.println(Arrays.toString(s.dedup(array)));
    }

    // Data structure:
    // slow: all elements to the left side of the slow (excluding slow) pointer are the results for the elements that have been processed. a[0 … s-1] 是已经处理过并且应该被保留的
    // fast: the current index being processed. (all elements to the right side of the fast pointer have not been processed) 正在处理元素的index
    // fNext: the index of the first element that’s different from input[fast]
    // Algorithm:
    // Initialization:
    // s = 0, f = 0
    // For each step
    // fNext = f+1, fNext++ until a[fNext] != a[f]
    // a[f] … a[fNext-1] must be a continuous and duplicated segment
    // (length of the segment: fNext - 1 - f + 1 = fNext - f)
    // Case 1: fNext - f == 1
    //	       a[f] is not duplicated, so the segment should be keeped
    //	       then a[s] = a[f]; s++; f = fNext
    // Case 2: fNext - f > 1
    //         a[f] is duplicated, so the segment should be ignored
    //         s 不动; f = fNext
    // Time O(n)
    // Space O(n)
    public int[] dedup(int[] array) {
        // Write your solution here
        if (array == null || array.length <= 2) {
            return array;
        }
        int slow = 0;
        int fast = 0;
        while (fast < array.length) {
            int fNext = fast + 1;
            // after the inner while loop, "fNext" points to the first character
            // after input[fast] that doesn't equal to input[fast]
            while (fNext < array.length && array[fNext] == array[fast]) {
                fNext++;
            }
            // a[fast] ... a[fastNext-1] must be a continuous and duplicated segment
            int length = fNext - fast;
            if (length == 1) {
                array[slow++] = array[fast++];
            } else {
                fast = fNext;
            }
        }
        // slow must be equal to the final length of the result array.
        return Arrays.copyOf(array, slow);
    }
}

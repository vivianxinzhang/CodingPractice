package com.company;
import java.util.Arrays;

public class MoveZerosToTheEndII {
    public static void main(String[] args) {
        MoveZerosToTheEndII s = new MoveZerosToTheEndII();
        int[] array = new int[] {1};
        System.out.println(Arrays.toString(s.moveZeroes(array)));

        array = new int[] {1, 0, 3, 0, 1};
        System.out.println(Arrays.toString(s.moveZeroes(array)));
    }

    // Data structure: two pointers
    // slow: [0 … slow-1] is the remainder of the elements that have been processed with all zeros removed
    // fast: the current element being processed, [0, fast - 1] are processed elements
    // Algorithm:
    // Step 1: copy all non-zeros in sequence using slow and fast pointers
    // Initialize:
    // s = 0, f = 0
    // For each step:
    // Case 1: a[f] != 0, then a[s] = a[f]; s++; f++
    // Case 2: a[f] == 0, then f++
    // Termination condition: f == a.length
    // Step 2: Post-processing: append 0 for [slow, n-1]
    // Time O(n)
    // Space O(n)
    public int[] moveZeroes(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        int slow = 0;
        int fast = 0;
        while (fast < nums.length) {
            if (nums[fast] == 0) {
                fast++;
            } else {
                nums[slow++] = nums[fast++];
            }
        }
        while (slow < nums.length) {
            nums[slow++] = 0;
        }
        return nums;
    }
}

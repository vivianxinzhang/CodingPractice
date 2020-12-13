package com.company;
import java.util.Arrays;

public class LargestAndSmallest {
    public static void main(String[] args) {
        LargestAndSmallest s = new LargestAndSmallest();
        int[] array = new int[] {1, 2, 3, 3, 3, 2, 2};
        System.out.println(Arrays.toString(s.largestAndSmallest(array)));   // [3, 1]
    }

    // Assumptions: array is not null & array.length >= 1
    // Step 1: split the numbers into winners and losers -- n/2
    // Step 2; compute the min from the losers -- n/2
    // Step 3: compute the max from the winners -- n/2
    // Total # of comparisons = 3 * n/2 = 1.5n
    // 1 2 | 4	3 |	6 5	| 8	7  winner: 2 4 6 8  loser: 1 3 5 7
    // Time O(n)
    // Space O(1)
    public int[] largestAndSmallest(int[] array) {
        int n = array.length;
        // indices (x, n - 1 - x) will be paired up, the larger numbers of
        // each pair will be put on the left side, so after the comparisons,
        // The left half of the array are the larger values for each pairs,
        // the right half of the array are the smaller values for each pairs
        // pair 前面的 和 后面的 做比较
        for (int i = 0; i < n / 2; i++) {
            if (array[i] < array[n - 1 - i]) {
                swap(array, i, n - 1 - i);
            }
        }
        // the largest value should be the largest of the left half
        // the smallest value should be the smallest of the right half
        return new int[] {largest(array, 0, (n - 1) / 2), smallest(array, n / 2, n - 1)};
    }

    private int largest(int[] array, int left, int right) {
        int largest = array[left];
        for (int i = left + 1; i <= right; i++) {
            largest = Math.max(largest, array[i]);
        }
        return largest;
    }

    private int smallest(int[] array, int left, int right) {
        int smallest = array[left];
        for (int i = left + 1; i <= right; i++) {
            smallest = Math.min(smallest, array[i]);
        }
        return smallest;
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}

package com.company;

import java.util.Arrays;

public class Heapify {
    public static void main(String[] args) {
        Heapify s = new Heapify();
        System.out.println();
        int[] array = new int[] {3, 5, 2, 1};
        System.out.println(Arrays.toString(array));
        s.heapify(array);
        System.out.println(Arrays.toString(array));
    }

    // Time O(n)
    // Space O(1)
    public int[] heapify(int[] array) {
        // Write your solution here
        int pIdx = (array.length - 2) / 2;
        for (int i = pIdx; i >= 0; i--) {
            percolateDown(array, i);
        }
        return array;
    }

    private void percolateDown(int[] array, int i) {
        while (2 * i + 1 < array.length) {
            int leftIdx = 2 * i + 1;
            int rightIdx = 2 * i + 2;
            // smallest one among left child and right child
            int swapCandidate = leftIdx;
            // update swapCandidate if right child exist and it is smaller than left child
            if (rightIdx <= array.length - 1 && array[leftIdx] >= array[rightIdx]) {
                swapCandidate = rightIdx;
            }
            // swap if necessary;
            if (array[i] > array[swapCandidate]) {
                swap(array, i, swapCandidate);
                i = swapCandidate;
            } else {
                break;
            }
        }
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}

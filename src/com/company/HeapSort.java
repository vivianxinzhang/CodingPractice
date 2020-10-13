package com.company;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        HeapSort s = new HeapSort();
        int[] array = {4, 6, 1, 3};
        System.out.println(Arrays.toString(s.heapsort(array)));
    }

    // Time O(nlogn)
    // Space O(1)
    public int[] heapsort(int[] array) {
        // Step 1: build heap
        // the last non-leaf node will be at size / 2
        // so we will start from this location to heapify the whole array
        heapify(array, array.length - 1);
        for (int endIdx = array.length - 1; endIdx > 0; endIdx--) {
            swap(array, 0, endIdx);
            heapify(array, endIdx - 1);
        }
        return array;
    }

    public void heapify(int[] array, int endIdx) {
        // Write your solution here
        int pIdx = (endIdx - 1) / 2;
        for (int i = pIdx; i >= 0; i--) {
            percolateDown(array, i, endIdx);
        }
    }

    private void percolateUp(int[] array, int i) {
        while (i > 0) {
            int parentIdx = (i - 1) / 2;
            if (array[parentIdx] < array[i]) {
                swap(array, i, parentIdx);
                i = parentIdx;
            } else {
                break;
            }
        }
    }

    private void percolateDown(int[] array, int i, int endIdx) {
        while (2 * i + 1 <= endIdx) {
            int leftIdx = 2 * i + 1;
            int rightIdx = 2 * i + 2;
            // smallest one among left child and right child
            int swapCandidate = leftIdx;
            // update swapCandidate if right child exist and it is smaller than left child
            if (rightIdx <= endIdx && array[leftIdx] <= array[rightIdx]) {
                swapCandidate = rightIdx;
            }
            // swap if necessary;
            if (array[i] < array[swapCandidate]) {
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

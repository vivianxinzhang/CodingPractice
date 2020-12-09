package com.company;
import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        HeapSort s = new HeapSort();
        int[] array = {4, 6, 1, 3};
        System.out.println(Arrays.toString(s.heapsort(array))); // [1, 3, 4, 6]

        array = new int[]{1, 2, 1, 2, 1};
        System.out.println(Arrays.toString(s.heapsort(array))); // [1, 1, 1, 2, 2]
    }

    // Time O(nlogn)
    // Space O(1)
    public int[] heapsort(int[] array) {
        // Step 1: heapify the input array to maxHeap.      - O(n)
        heapify(array, array.length - 1);
        // Step 2: n times poll and percolateDown operation - O(nlogn)
        //         swap the polled element with end element in the heap
        //         (last element is sorted, heapSize--)
        //         percolateDown first element to maintain heap property for the unsorted part in heap [0, endIdx]
        for (int endIdx = array.length - 1; endIdx > 0; endIdx--) {
            swap(array, 0, endIdx);
            percolateDown(array, 0, endIdx - 1);
        }
        return array;
    }

    // the last non-leaf node will be at size / 2
    // so we will start from this location to heapify the whole array
    public void heapify(int[] array, int endIdx) {
        int pIdx = (endIdx - 1) / 2;
        for (int i = pIdx; i >= 0; i--) {
            percolateDown(array, i, endIdx);
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
            // swap if necessary
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

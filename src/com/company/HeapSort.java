package com.company;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        HeapSort s = new HeapSort();
        int[] array = {4, 4, 1, 1};
        System.out.println(Arrays.toString(s.heapsort(array)));
    }

    // Time O(nlogn)
    // Space O(1)
    public int[] heapsort(int[] array) {
        int size = array.length - 1;
        // Step 1: build heap
        // the last non-leaf node will be at size / 2
        // so we will start from this location to heapify the whole array
        for (int i = size / 2; i >= 0; i--) {
            heapify(array, i, size);
        }
        // array[0] is a root of the heap and is the max element in heap
        for (int i = size; i > 0; i--) {
            int tmp = array[0];
            array[0] = array[i];
            array[i] = tmp;
            size = size - 1;
            heapify(array, 0, size);
        }
        return array;
    }

    private void heapify(int[] array, int i, int size) {
        if (size == 0) {
            return;
        }
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int max = i;
        // if left child is larger than root
        if (left <= size && array[left] > array[i]) {
            max = left;
        }
        // if right child is larger than largest so far
        if (right <= size && array[right] > array[max]) {
            max = right;
        }
        // if max is not current node, exchange it with max of left or right
        if (max != i) {
            int tmp = array[i];
            array[i] = array[max];
            array[max] = tmp;
        }
        // recursive call
        heapify(array, max, size - 1);
    }
}

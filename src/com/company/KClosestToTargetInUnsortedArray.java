package com.company;
import java.util.Arrays;

public class KClosestToTargetInUnsortedArray {
    public static void main(String[] args) {
        KClosestToTargetInUnsortedArray s = new KClosestToTargetInUnsortedArray();
        int[] array = new int[] {1, 3, 5, 2, 7};
        System.out.println(Arrays.toString(s.kClothest(array, 3, 2)));

        array = new int[] {1, 3, 5, 2, 7};
        System.out.println(Arrays.toString(s.kClothest(array, 100, 2)));
    }

    // Method 1: quick-select
    // Time O(n) worst case O(n^2)
    // Space O(logn) worst case O(n)
    public int[] kClothest(int[] array, int target, int k) {
        if (array == null || array.length == 0) {
            return new int[0];
        }
        quickSelect(array, target, k - 1, 0, array.length - 1);
        return Arrays.copyOf(array, k);
    }

    private void quickSelect(int[] array, int target, int targetIdx, int left, int right) {
        if (left > right) {
            return;
        }
        int pivotIdx = partition(array, target, left, right);
        if (pivotIdx == targetIdx) {
            return;
        } else if (pivotIdx > targetIdx) {
            quickSelect(array, target, targetIdx, left, pivotIdx - 1);
        } else {
            quickSelect(array, target, targetIdx, pivotIdx + 1, right);
        }
    }

    private int partition(int[] array, int target, int left, int right) {
        // right is pivotIdx
        int pivot = Math.abs(array[right] - target);    // must have Math.abs()
        int i = 0;
        int j = right - 1;
        while (i <= j) {
            if (Math.abs(array[i] - target) < pivot) {
                i++;
            } else {
                swap(array, i, j);
                j--;
            }
        }
        swap(array, i, right);
        // all values left of i are closer to target than pivot
        // all values right of i have bigger diff to target than array[i] to target
        return i;
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}

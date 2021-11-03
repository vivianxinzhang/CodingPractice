package com.company;

public class DetermineIfArrayIsMinHeap {
    public static void main(String[] args) {
        DetermineIfArrayIsMinHeap s = new DetermineIfArrayIsMinHeap();

        int[] array = new int[] {1, 2};
        System.out.println(s.isMinHeap(array));     // true
        array = new int[] {2, 2, 3, 3, 3};
        System.out.println(s.isMinHeap(array));     // true
        array = new int[] {2, 2, 3, 3, 3, 4, 5};
        System.out.println(s.isMinHeap(array));     // true
        array = new int[] {2, 2, 3, 3, 3, 4, 1};
        System.out.println(s.isMinHeap(array));     // false
    }

    // Time O(n)
    // Space O(1)
    public boolean isMinHeap(int[] array) {
        int i = array.length - 1;
        while (i > 0) {
            int pIdx = (i - 1) / 2;
            if (array[i] < array[pIdx]) {
                return false;
            }
            i--;
        }
        return true;
    }
}

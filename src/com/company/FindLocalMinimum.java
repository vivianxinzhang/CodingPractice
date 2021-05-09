package com.company;

public class FindLocalMinimum {
    public static void main(String[] args) {
        FindLocalMinimum s = new FindLocalMinimum();
        int[] array = new int[0];           // -1
        System.out.println(s.localMinimum(array));

        array = new int[] {1, 2, 3};        // 0
        System.out.println(s.localMinimum(array));

        array = new int[] {3, 1};           // 1
        System.out.println(s.localMinimum(array));

        array = new int[] {2, 1, 3, 5};     // 1
        System.out.println(s.localMinimum(array));
    }

    // Assumptions:
    // 1. The given array is not null or empty.
    // 2. There are no duplicate elements in the array.
    // 3. There is always one and only one result for each case.
    // Time O(logn)
    // Space O(1)
    public int localMinimum(int[] array) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid] < array[mid - 1] && array[mid] < array[mid + 1]) {
                return mid;
            } else if (array[mid] < array[mid - 1]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return array[left] > array[right] ? right : left;
    }
}

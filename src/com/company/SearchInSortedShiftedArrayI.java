package com.company;

public class SearchInSortedShiftedArrayI {
    public static void main(String[] args) {
        SearchInSortedShiftedArrayI s = new SearchInSortedShiftedArrayI();
        int[] array = new int[] {3, 4, 5, 1, 2};
        System.out.println(s.search(array, 4));     // 1

        array = new int[] {1, 2, 3, 4, 5};
        System.out.println(s.search(array, 4));     // 3

        array = new int[] {3, 5, 6, 1, 2};
        System.out.println(s.search(array, 4));     // -1
    }

    // Assumptions:
    // There are no duplicate elements in the array.
    // Time O(logn)
    // Space O(1)
    public int search(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                return mid;
            } else if (array[left] < array[mid] && within(array, target, left, mid)
                    || array[mid] < array[right] && !within(array, target, mid, right)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    // Assumptions
    // There are no duplicate elements in the array
    // 左升序 并且target在左边 || 右升序 并且target不在右边 --> 往左找
    // otherwise 往右找
    // 右升序 并且target在右边 || 左升序 并且target不在左边 --> 往右找
    // otherwise 往左找
    // Time O(logn)
    // Space O(1)
    public int searchI(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0, right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[left] < array[mid] && within(array, target, left, mid)
                    || array[mid] < array[right] && !within(array, target, mid, right)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (array[left] == target) {
            return left;
        }
        if (array[right] == target) {
            return right;
        }
        return -1;
    }

    private boolean within(int[] array, int target, int left, int right) {
        return target >= array[left] && target <= array[right];
    }
}

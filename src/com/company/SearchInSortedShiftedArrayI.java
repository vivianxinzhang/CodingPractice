package com.company;

public class SearchInSortedShiftedArrayI {
    // Assumptions
    // There are no duplicate elements in the array
    // 左升序 并且target在左边 || 右升序 并且target不在右边 --> 往左找
    // otherwise 往右找
    // 右升序 并且target在右边 || 左升序 并且target不在左边 --> 往右找
    // otherwise 往左找
    // Time O(logn)
    // Space O(1)
    public int search(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0, right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[left] < array[mid] && within(array, left, target, mid)
                    || array[mid] < array[right] && !within(array, mid, target, right)) {
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

    private boolean within(int[] array, int left, int target, int right) {
        return target >= array[left] && target <= array[right];
    }
}

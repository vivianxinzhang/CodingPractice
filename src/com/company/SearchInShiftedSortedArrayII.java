package com.company;

public class SearchInShiftedSortedArrayII {
    public static void main(String[] args) {
        SearchInShiftedSortedArrayII s = new SearchInShiftedSortedArrayII();

        int[] array = new int[]{};
        System.out.println(s.search(array, 3));     // -1
        array = new int[] {3, 1, 1};
        System.out.println(s.search(array, 3));     // 0
        array = new int[] {1, 1, 3, 3, 3};
        System.out.println(s.search(array, 3));     // 2
        array = new int[] {1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1};
        System.out.println(s.search(array, 2));     // 6
        array = new int[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1};
        System.out.println(s.search(array, 2));     // 12
    }

    // Assumptions:
    // 1. There could be duplicate elements in the array.
    // 2. Return the smallest index if target has multiple occurrence.
    // Time O(n)
    // Space O(1)
    public int search(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        // need to make sure search space decrease in each iteration
        // → to avoid 死循环
        // either move left, move right, or return
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            // 左边sorted
            if (array[mid] > array[left] && withInRange(array, target, left, mid)) {
                right = mid;
            } else if (array[mid] < array[right] && !withInRange(array, target, mid, right)) {
                // 右边sorted
                right = mid;
            } else if (array[left] == target) {
                return left;
            } else {
                left++;
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

    private boolean withInRange(int[] array, int target, int left, int right) {
        return target >= array[left] && target <= array[right];
    }


    public int searchII(int[] array, int target) {
        if (array.length==0){
            return -1;
        }
        int left = 0;
        int right = array.length-1;
        while (left < right - 1){
            int mid = left + (right-left)/2;
            if (array[left]==array[right]){
                right--;
                continue;
            }
            if (array[left]<=array[mid]){
                if (array[left]<=target && target<=array[mid]){
                    right = mid;
                }else{
                    left = mid+1;
                }
            }else{
                if (array[mid]<=target && target<=array[right]){
                    left = mid;
                }else{
                    right = mid-1;
                }
            }
        }
        if (array[left]==target){
            return left;
        }else if (array[right]==target){
            return right;
        }
        return -1;
    }

    // 如果無法確定array[middle]到底在左半段還是右半段:
    // 比如1,1,1,1,1,1,2,1,1,1,1,1 然後array[middle]等於1，
    // 不知道target在左半段還是右半段，那麽就讓begin ++, end --縮小搜索範圍即可，
    // 最糟糕時間複雜度O(n)
    // Time O(n)
    // Space O(1)
    public int searchI(int[] input, int target) {
        if (input == null || input.length == 0) {
            return -1;
        }
        int left = 0, right = input.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (input[left] == target) return left;
            if (input[mid] == target) return mid;
            if (input[right] == target) return right;
            if (input[left] < input[mid] && within(input, target, left, mid)
                    || input[mid] < input[right] && !within(input, target, mid, right)) {
                // [left, mid] is increasing and target is in [left, mid]
                // [mid, right] is increasing and target is in [mid, right]
                // 一定在左边
                right = mid - 1;
            } else if (input[left] < input[mid] && !within(input, target, left, mid)
                    || input[mid] < input[right] && within(input, target, mid, right)){
                // 一定在右边
                left = mid + 1;
            } else {
                left++;
                right--;
            }
        }
        if (input[left] == target) return left;
        if (input[right] == target) return right;
        return -1;
    }

    private boolean within(int[] array, int target, int left, int right) {
        return target >= array[left] && target <= array[right];
    }
}

package com.company;

public class TotalOccurrence {
    public static void main(String[] args) {
        TotalOccurrence s = new TotalOccurrence();
        int[] array = new int[0];
        System.out.println(s.totalOccurrence(array, 0));

        array = new int[] {1, 2};
        System.out.println(s.totalOccurrence(array, 0));
    }

    // Assumptions:
    // There are no duplicate elements in the array
    // Time O(logn)
    // Space O(1)
    public int totalOccurrence(int[] array, int target) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int firstOccurIdx = findFirstOccur(array, target);
        int lastOccurIdx = findLastOccur(array, target);
        return firstOccurIdx == -1 ? 0 : lastOccurIdx - firstOccurIdx + 1;
    }

    private int findFirstOccur(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid] >= target) {
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

    private int findLastOccur(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid] <= target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (array[right] == target) {
            return right;
        }
        if (array[left] == target) {
            return left;
        }
        return -1;
    }
}

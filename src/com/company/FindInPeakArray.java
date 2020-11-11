package com.company;
import java.util.Arrays;

public class FindInPeakArray {
    public static void main(String[] args) {
        FindInPeakArray s = new FindInPeakArray();
        int[] array = new int[] {1, 2, 3, 6, 5, 1};
        PeakArray packArray = new PeakArrayImplementation(array);
        System.out.println(s.findInPeakArray(packArray, 6));
    }

    // Time O(logn)
    // Space O(1)
    public int findInPeakArray(PeakArray peakArr, int target) {
        if (peakArr == null || peakArr.length() == 0) {
            return -1;
        }
        int peakIdx = findPeakElement(peakArr);
        int idx = incBinarySearch(peakArr, target, 0, peakIdx);
        if (idx != -1) {
            return idx;
        }
        return decBinarySearch(peakArr, target, peakIdx + 1, peakArr.length() - 1);
    }

    private int decBinarySearch(PeakArray peakArr, int target, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (peakArr.get(mid) == target) {
                return mid;
            } else if (peakArr.get(mid) > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    private int incBinarySearch(PeakArray peakArr, int target, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (peakArr.get(mid) == target) {
                return mid;
            } else if (peakArr.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    private int findPeakElement(PeakArray array) {
        if (array == null || array.length() == 0) {
            return -1;
        }
        int left = 0, right = array.length() - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array.get(mid) < array.get(mid + 1)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return array.get(left) > array.get(right) ? left : right;
    }
}

interface PeakArray {
    public int get(int index);
    public int length();
}

class PeakArrayImplementation implements PeakArray {
    private int[] array;

    public PeakArrayImplementation(int[] array) {
        this.array = array;
    }

    @Override
    public int get(int index) {
        return array[index];
    }

    @Override
    public int length() {
        return array.length;
    }
}

package com.company;

public class FindPeak {
    public int findPeakElement(int[] array) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0, right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid] < array[mid + 1]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return array[left] > array[right] ? left : right;
    }

    public static void main(String[] args) {
        FindPeak s = new FindPeak();

        int[] array = new int[]{};
        System.out.println(s.findPeakElement(array));

        array = new int[]{1, 2, 3};
        System.out.println(s.findPeakElement(array));

        array = new int[]{3, 2, 1};
        System.out.println(s.findPeakElement(array));

        array = new int[]{1, 2, 4, 3};
        System.out.println(s.findPeakElement(array));
    }
}

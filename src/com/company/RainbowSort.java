package com.company;

public class RainbowSort {
    // rainbow sort 3 colors: Time O(n) Space O(1)
    public int[] rainbowSort1(int[] array) {
        // Write your solution here
        if (array == null || array.length == 0) {
            return array;
        }
        int i = 0, j = 0, k = array.length - 1;
        while (j <= k) {
            if (array[j] == -1) {
                swap(array, i, j);
                i++;
                j++;
            } else if (array[j] == 0) {
                j++;
            } else {
                swap(array, j, k);
                k--;
            }
        }
        return array;
    }

    // rainbow sort 4 colors: Time O(n) Space O(1)
    public int[] rainbowSortII(int[] array) {
        // Write your solution here
        if (array == null || array.length == 0) {
            return array;
        }
        int i = 0, j = 0, k = 0, l = array.length - 1;
        while (k <= l) {
            if (array[k] == 0) {
                swap(array, j, k);
                swap(array, i, j);
                i++;
                j++;
                k++;
            } else if (array[k] == 1) {
                swap(array, j, k);
                j++;
                k++;
            } else if (array[k] == 2) {
                k++;
            } else {
                swap(array, k, l);
                l--;
            }
        }
        return array;
    }

    // rainbow sort k colors: Time O(nk) Space O(1)
    public int[] rainbowSortIII(int[] array, int k) {
        // Write your solution here
        if (array == null || array.length == 0) {
            return array;
        }
        int left = 0, right = array.length - 1;
        int leftColor = 1, rightColor = k;
        while (leftColor < rightColor) {
            for (int i = left; i <= right; i++) {
                if (array[i] == leftColor) {
                    swap(array, left, i);
                    left++;
                } else if (array[i] == rightColor) {
                    swap(array, i, right);
                    right--;
                    i--;
                }
            }
            leftColor++;
            rightColor--;
        }
        return array;
    }

    public static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}

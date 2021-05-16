package com.company;

import java.util.Arrays;

public class ReOrderStringI {
    public static void main(String[] args) {
        ReOrderStringI s = new ReOrderStringI();

        System.out.println(s.reorderStringI("A1B2C3D4"));
        // ABCD1234
        System.out.println(s.reorderStringI("A1B2C3"));
        // ABC123
        System.out.println(s.reorderStringI("A1B2"));
        // AB12
        System.out.println(s.reorderStringI("A1"));
        // A1
    }

    /**
     * Given a string “A1B2C3D4”, convert it to another string “ABCD1234”
     *                  A1B2C3D4
     *           A1B2      |       C3D4
     *       A1   |   B2   |    C3    |   D4
     *     A | 1    B  | 2    C | 3     D | 4
     * =========================================
     *       A1       B2        C3        D4
     *           AB12              CD34
     *                  ABCD1234
     * */
    public String reorderStringI(String input) {
        if (input == null || input.length() <= 2) {
            return input;
        }
        char[] array = input.toCharArray();
        char[] helper = new char[input.length()];
        mergeSort(array, helper, 0, array.length - 1);
        return new String(array);
    }

    private void mergeSort(char[] array, char[] helper, int left, int right) {
        int length = right - left + 1;
        if (length <= 2) {
            return;
        }
        int halfLength = length / 2;
        halfLength = halfLength % 2 == 0 ? halfLength : halfLength + 1;
        int mid = left + halfLength - 1;
        mergeSort(array, helper, left, mid);
        mergeSort(array, helper, mid + 1, right);
        merge(array, helper, left, mid, right);
    }

    private void merge(char[] array, char[] helper, int left, int mid, int right) {
        for (int i = left; i <= right; i++) {
            helper[i] = array[i];
        }
        int i = left;
        int j = mid + 1;
        int k = left;
        while (Character.isAlphabetic(helper[i])) {
            array[k++] = helper[i++];
        }
        while (Character.isAlphabetic(helper[j])) {
            array[k++] = helper[j++];
        }
        while (i <= mid) {
            array[k++] = helper[i++];
        }
        while (j <= right) {
            array[k++] = helper[j++];
        }
    }
}

package com.company;

import java.util.ArrayList;
import java.util.List;

public class MissingRanges {
    public static void main(String[] args) {
        MissingRanges s = new MissingRanges();
        int[] array = new int[] {3, 4, 5, 6, 7};
        System.out.println(s.findMissingRanges(array, 0, 99));
        // [0->2, 8->99]

        array = new int[] {0, 1, 3, 50, 75};
        System.out.println(s.findMissingRanges(array, 0, 99));
        // [2, 4->49, 51->74, 76->98]
    }

    // For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99,
    // return ["2", "4->49", "51->74", "76->99"].
    // Time O(n)
    // Space O(1)
    public List<String> findMissingRanges(int[] array, int lower, int upper) {
        List<String> res = new ArrayList<>();
        if (array == null || array.length == 0) {
            return res;
        }
        if (lower < array[0]) {
            res.add(getRange(lower - 1, array[0]));
        }
        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[i - 1] + 1) {
                res.add(getRange(array[i - 1], array[i]));
            }
        }
        if (upper > array[array.length - 1]) {
            res.add(getRange(array[array.length - 1], upper + 1));
        }
        return res;
    }

    // get range between num1 and num2
    private String getRange(int num1, int num2) {
        StringBuilder sb = new StringBuilder();
        if (num1 == num2 - 2) {
            sb.append(num1 + 1);
        } else {
            sb.append(num1 + 1);
            sb.append("->");
            sb.append(num2 - 1);
        }
        return sb.toString();
    }
}

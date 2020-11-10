package com.company;
import java.util.*;

public class FormLargestNumber {
    public static void main(String[] args) {
        FormLargestNumber s = new FormLargestNumber();
        String[] input = new String[]{"54", "546", "648", "88"};
        System.out.println(Integer.valueOf("88"));
        System.out.println(Integer.valueOf("648"));
        System.out.println();
        System.out.println(s.largestNumber(input));
    }

    // Time O(nlogn)
    // Space O(n)
    public String largestNumber(String[] input) {
        // Sort strings according to custom comparator.
        Arrays.sort(input, new MyComparator());
        // If, after being sorted, the largest number is `0`, the entire number
        // is zero.
        if (input[0].equals("0")) {
            return "0";
        }
        // Build largest number from sorted array.
        String largestNumberStr = new String();
        for (String numAsStr : input) {
            largestNumberStr += numAsStr;
        }
        return largestNumberStr;
    }

    class MyComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            String o12 = o1 + o2;
            String o21 = o2 + o1;
            return o21.compareTo(o12);
        }
    }
}

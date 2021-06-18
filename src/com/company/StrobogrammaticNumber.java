package com.company;

import java.util.HashMap;
import java.util.Map;

public class StrobogrammaticNumber {
    public static void main(String[] args) {
        StrobogrammaticNumber s = new StrobogrammaticNumber();

        /**
         * A strobogrammatic number is a number that looks the same
         * when rotated 180 degrees (looked at upside down).
         * */
        System.out.println(s.isStrobogrammatic("69"));      // true
        System.out.println(s.isStrobogrammatic("88"));      // true
        System.out.println(s.isStrobogrammatic("818"));     // true
        System.out.println(s.isStrobogrammatic("186100020001981"));     // false
    }

    // Time O(n)
    // Space O(1)
    public boolean isStrobogrammatic(String input) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        map.put(1, 1);
        map.put(8, 8);
        map.put(6, 9);
        map.put(9, 6);
        int i = 0;
        int j = input.length() - 1;
        while (i < j) {
            int leftDigit = input.charAt(i) - '0';
            int rightDigit = input.charAt(j) - '0';
            if (!map.containsKey(leftDigit) || map.get(leftDigit) != rightDigit) {
                return false;
            }
            i++;
            j--;
        }
        if (i == j) {
            int midNum = input.charAt(i) - '0';
            return midNum == 0 || midNum == 1 || midNum == 8;
        }
        return true;
    }
}

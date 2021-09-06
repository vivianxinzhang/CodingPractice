package com.company;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
    public static void main(String[] args) {
        RomanToInteger s = new RomanToInteger();

        System.out.println(s.romanToInt("III"));         // 3
        System.out.println(s.romanToInt("IV"));          // 4
        System.out.println(s.romanToInt("IX"));          // 9
        System.out.println(s.romanToInt("LVIII"));       // 58
        System.out.println(s.romanToInt("MCMXCIV"));     // 1994
        System.out.println(s.romanToInt("MMMCMXCIX"));   // 3999
    }

    // Solution:
    // iterate through the input string
    // sum all values represented by each char
    // for case a < b, need to -2*a from sum
    // I    1
    // II   2
    // III  3
    // IV   4       a < b     value = -a + b
    // V    5
    // VI   6
    // VII  7
    // VIII 8
    // IX   9       a < b     value = -a + b
    // X    10
    // XI   11
    // XII  12
    // "ab" pattern, if a is smaller than b, the value changes from a + b to b - a
    // Sum all the symbols up, when encounter "ab pattern", minus 2 * a
    // "IV"
    // I: 1     V: 5
    // IV = 1 + 5 - 2 * 1 = 6 - 2 = 4
    // "VX"
    // V: 5         X: 10
    // VX = 5 + 10 - 2 * 5 = 5 = v
    // Time O(n)
    // Space O(1)
    public int romanToInt(String input) {
        Map<Character, Integer> map = new HashMap<>();
        /**         Symbol Value
         I 1
         V 5
         X 10
         L 50
         C 100
         D 500
         M 1000
         */
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int sum = 0;
        Integer pre = null;
        for (int i = 0; i < input.length(); i++) {
            int cur = map.get(input.charAt(i));
            sum += cur;
            if (pre != null && cur > pre) {
                sum -= 2 * pre;
            }
            pre = cur;
        }
        return sum;
    }
}

package com.company;

public class IntegerToRoman {
    public static void main(String[] args) {
        IntegerToRoman s = new IntegerToRoman();
        /**         Symbol Value
                     I 1
                     V 5
                     X 10
                     L 50
                     C 100
                     D 500
                     M 1000
         Roman numerals are usually written largest to smallest from left to right.
         However, the numeral for four is not IIII. Instead, the number four is written as IV.
         Because the one is before the five we subtract it making four.
         The same principle applies to the number nine, which is written as IX.
         There are six instances where subtraction is used:
         I can be placed before V (5) and X (10) to make 4 and 9.
         X can be placed before L (50) and C (100) to make 40 and 90.
         C can be placed before D (500) and M (1000) to make 400 and 900.
         * */

        System.out.println(s.intToRoman(3));        // III
        System.out.println(s.intToRoman(4));        // IV
        System.out.println(s.intToRoman(9));        // IX
        System.out.println(s.intToRoman(58));       // LVIII
        System.out.println(s.intToRoman(1994));     // MCMXCIV
    }

    // Method 2:
    // Step 1: pre-processing: mapping numbers to Roman:
    //         M    D   C   L   X   V   I
    //         1000 500 100 50  10  5   1
    //         also include six instances where subtraction is used:
    //         M    CM  D   CD  C   XC  L   XL  X   IX  V   IV  I
    //         1000 900 500 400 100 90  50  40  10  9   5   4   1
    // Step 2: iterate through values, and subtract the current largest possible value from original number
    //         append corresponding Roman Char to result
    // Time O(1)
    // Space O(1)
    public String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] strs = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < values.length; i++) {
            while(num >= values[i]) {
                num -= values[i];
                sb.append(strs[i]);
            }
        }
        return sb.toString();
    }

    // Method 1:
    // Time O(1)
    // Space O(1)
    public String intToRomanI(int num) {
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[num/1000] + C[(num%1000)/100] + X[(num%100)/10] + I[num%10];
    }
}

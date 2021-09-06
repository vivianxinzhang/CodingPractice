package com.company;
import java.util.*;

public class MultiplyStrings {
    public static void main(String[] args) {
        MultiplyStrings s = new MultiplyStrings();

        System.out.println(s.multiply("12", "12"));     // 144
        System.out.println(s.multiply("123", "0"));     // 144
        System.out.println(s.multiply("123", "45"));     // 144
        /**           1   2   3       index i
         *                4   5       index j
         *  -------------------
         *                1   5       index[i + j, i + j + 1]
         *            1   0
         *        0   5
         *            1   2
         *        0   8
         *    0   4
         *  -------------------
         *    0   1   2   3   4     index
         *    0   5   5   3   5
         * */
        System.out.println(s.multiply("9", "99"));     // 891
    }

    // The product of two numbers cannot exceed the sum of the two lengths.
    // (e.g. 99 * 99 cannot be five digit)
    // int d1 = num1.charAt(i) - '0';
    // int d2 = num2.charAt(j) - '0';
    // products[i + j + 1] += d1 * d2;
    // Time O(mn)
    // Space O(m + n)
    public String multiply(String num1, String num2) {
        int[] num = new int[num1.length() + num2.length()];
        for (int i = 0; i < num1.length(); i++) {
            for (int j = 0; j < num2.length(); j++) {
                int digit1 = num1.charAt(i) - '0';
                int digit2 = num2.charAt(j) - '0';
                int prod =  digit1 * digit2;
                num[i + j + 1] += prod;
            }
        }
        int carry = 0;
        for (int i = num.length - 1; i >= 0; i--) {
            carry += num[i];
            num[i] = carry % 10;
            carry = carry / 10;
        }
        StringBuilder sb = new StringBuilder();
        for (int digit : num) {
            if (!(sb.length() == 0 && digit == 0)) {
                sb.append(digit);
            }
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
}

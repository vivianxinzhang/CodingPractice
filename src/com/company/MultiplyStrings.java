package com.company;
import java.util.*;

public class MultiplyStrings {
    public static void main(String[] args) {
        MultiplyStrings s = new MultiplyStrings();

        String num1 = "12";
        String num2 = "12";
        System.out.println(s.multiply(num1, num2));     // 144

        num1 = "123";
        num2 = "0";
        System.out.println(s.multiply(num1, num2));     // 0

        num1 = "123";
        num2 = "45";
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
        System.out.println(s.multiply(num1, num2));     // 891

        num1 = "9";
        num2 = "99";
        System.out.println(s.multiply(num1, num2));     // 0
    }

    // The product of two numbers cannot exceed the sum of the two lengths.
    // (e.g. 99 * 99 cannot be five digit)
    // int d1 = num1.charAt(i) - '0';
    // int d2 = num2.charAt(j) - '0';
    // products[i + j + 1] += d1 * d2;
    // Time O(mn)
    // Space O(m + n)
    public String multiplyI(String num1, String num2) {
        if (num1 == null || num2 == null) {
            return "0";
        }
        int[] products = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            for (int j = num2.length() - 1; j >= 0; j--) {
                int d1 = num1.charAt(i) - '0';
                int d2 = num2.charAt(j) - '0';
                products[i + j + 1] += d1 * d2;
            }
        }
        int num = 0;
        for (int i = products.length - 1; i >= 0; i--) {
            num += products[i];
            products[i] = num % 10;
            num /= 10;
        }
        StringBuilder sb = new StringBuilder();
        for (int digit : products) {
            sb.append(digit);
        }
        if (sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    // Time O(mn)
    // Space O(m + n)
    public String multiply(String num1, String num2) {
        int[] res = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            for (int j = num2.length() - 1; j >= 0; j--) {
                int x = num1.charAt(i) - '0';
                int y = num2.charAt(j) - '0';
                int pos1 = i + j;
                int pos2 = i + j + 1;
                int sum = x * y + res[pos1] * 10 + res[pos2];
                res[pos2] = sum % 10;
                res[pos1] = sum / 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int digit : res) {
            if (digit == 0 && sb.length() == 0) {
                continue;
            }
            sb.append(digit);
        }
        return sb.toString();
    }
}

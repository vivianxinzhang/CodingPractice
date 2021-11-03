package com.company;

import java.util.HashMap;
import java.util.Map;

public class CompactDivide {
    public static void main(String[] args) {
        CompactDivide s = new CompactDivide();

        System.out.println(s.divide(1, 0));       // NaN
        System.out.println(s.divide(1, 2));       // 0.5
        System.out.println(s.divide(2, 3));       // 0.(6)
        System.out.println(s.divide(22, -7));     // -3.(142857)
        System.out.println(s.divide(32, 666));    // 0.(048)
        System.out.println(s.divide(2, 100100));  // 0.00(001998)
    }

    // Time O(1) 最长是O(10)
    // Space O(1)
    public String divide(int a, int b) {
        if (b == 0) {
            return "NaN";
        }
        if (a == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        if ((a > 0 && b < 0) || (a < 0 && b > 0)) {
            sb.append("-");
        }
        int numerator = Math.abs(a);
        int denominator = Math.abs(b);
        sb.append(numerator / denominator);
        numerator %= denominator;
        // 可以整除
        if (numerator == 0) {
            return sb.toString();
        }
        sb.append(".");
        // key: digit  value: index
        Map<Integer, Integer> map = new HashMap<>();
        map.put(numerator, sb.length());
        // 不能整除
        while (numerator != 0) {
            numerator *= 10;
            sb.append(numerator / denominator);
            numerator %= denominator;
            if (map.containsKey(numerator)) {
                int leftIdx = map.get(numerator);
                sb.insert(leftIdx, "(");
                sb.append(")");
                break;
            }
            map.put(numerator, sb.length());
        }
        return sb.toString();
    }
}

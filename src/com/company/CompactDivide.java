package com.company;

import java.util.HashMap;
import java.util.Map;

public class CompactDivide {
    public static void main(String[] args) {
        CompactDivide s = new CompactDivide();

        System.out.println(s.divide(1, 0));     // NaN
        System.out.println(s.divide(1, 2));     // 0.5
        System.out.println(s.divide(2, 3));     // 0.(6)
        System.out.println(s.divide(32, 666));  // 0.(048)

    }

    // Time O(1) 最长是O(10)
    // Space O(1)
    public String divide(int numerator, int denominator) {
        if (denominator == 0) {
            return "NaN";
        }
        if (numerator == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        if (numerator > 0 && denominator < 0 || numerator < 0 && denominator > 0) {
            sb.append("-");
        }
        // avoid Integer overflow
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);
        sb.append(num / den);
        num %= den;
        // 可以整除
        if (num == 0) {
            return sb.toString();
        }
        sb.append(".");
        // key: digit  value: index
        Map<Long, Integer> map = new HashMap<>();
        map.put(num, sb.length());
        // 不能整除
        while (num != 0) {
            num *= 10;
            sb.append(num / den);
            num %= den;
            if (map.containsKey(num)) {
                int index = map.get(num);
                sb.insert(index, "(");
                sb.append(")");
                break;
            } else {
                map.put(num, sb.length());
            }
        }
        return sb.toString();
    }
}

package com.company;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class DecodeWays {
    public static void main(String[] args) {
        DecodeWays s = new DecodeWays();

        System.out.println(s.numDecodeWay("0"));    // 0
        System.out.println(s.numDecodeWay("212"));  // 3
        // 2,1,2("BAB") or 2,12("BL") or 21,2("UB")
        System.out.println(s.numDecodeWay("106"));  // 1
        System.out.println(s.numDecodeWay("624212641113981521649688221891834112776717328126106"));
        // 54000
    }

    // Method 1: DP
    // M[i] represents total number of ways to decode the first number of i chars, [0, i - 1]
    // Time O(n)
    // Space O(n)
    public int numDecodeWayI(String input) {
        int[] M = new int[input.length() + 1];
        M[0] = 1;
        M[1] = input.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i < M.length; i++) {
            // case 1: decode the curr digit
            int currDigit = input.charAt(i - 1) - '0';
            if (currDigit != 0) {
                M[i] += M[i - 1];
            }
            // case 2: decode curr and pre digit together
            int twoDigits = Integer.valueOf(input.substring(i - 2, i));  // [i-2, i-1]
            if (twoDigits >= 10 && twoDigits <= 26) {
                M[i] += M[i - 2];
            }
        }
        return M[M.length - 1];
    }

    private int toNumber(String input, int i, int j) {
        return 10 * (input.charAt(i) - '0') + (input.charAt(j) - '0');
    }

    // optimize: use index instead of substring
    // Time O(n)
    // Space O(n)
    public int numDecodeWay(String input) {
        if (input.length() == 0) {
            return 0;
        }
        Map<Pair, Integer> map = new HashMap<>();
        return numOfWays(input, map, 0, input.length() - 1);
    }

    private int numOfWays(String input, Map<Pair, Integer> map, int left, int right) {
        Pair cur = new Pair(left, right);
        if (map.containsKey(cur)) {
            return map.get(cur);
        }
        if (left > right) {
            map.put(cur, 1);
            return 1;
        }
        // process 1 digit
        if (input.charAt(left) == '0') {
            map.put(cur, 0);
            return 0;
        }
        // Single digit or empty.
        if (left >= right) {
            map.put(cur, 1);
            return 1;
        }
        int count = numOfWays(input, map, left + 1, right);
        // process 2 digits
        int prefix = Integer.valueOf(input.substring(left, left + 2));
        if (prefix <= 26) {
            count += numOfWays(input, map, left + 2, right);
        }
        map.put(cur, count);
        return count;
    }


    class Pair {
        int left;
        int right;

        Pair(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Pair)) {
                return false;
            }
            Pair p = (Pair) o;
            return p.left == left && p.right == right;
        }
    }

    // Method 0: Recursion with memoization
    // W("") = 1
    // W(s) = 0 if s[0] == '0'
    // W(s[0 .. n-1]) = W(s[1 .. n-1])
    //                  + W(s[2 .. n-1]) if '10' <= s[0][1] <= '26'
    // Time O(n^2)
    // Space O(n^2)
    public int numDecodeWayII(String input) {
        if (input.length() == 0) {
            return 0;
        }
        Map<String, Integer> map = new HashMap<>();
        map.put("", 1);
        return ways(input, map);
    }

    private int ways(String input, Map<String, Integer> map) {
        if (map.containsKey(input)) {
            return map.get(input);
        }
        // process 1 digit
        if (input.charAt(0) == '0') {
            return 0;
        }
        if (input.length() == 1) {
            return 1;
        }
        int count = ways(input.substring(1), map);
        // process 2 digits
        int prefix = Integer.valueOf(input.substring(0, 2));
        if (prefix <= 26) {
            count += ways(input.substring(2), map);
        }
        map.put(input, count);
        return count;
    }
}

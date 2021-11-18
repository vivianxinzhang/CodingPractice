package com.company;

import java.util.HashMap;
import java.util.Map;

public class StrobogrammaticNumberIII {
    public static void main(String[] args) {
        StrobogrammaticNumberIII s = new StrobogrammaticNumberIII();

        /**
         * A strobogrammatic number is a number that looks the same
         * when rotated 180 degrees (looked at upside down).
         * */
        System.out.println(s.strobogrammaticInRange("50", "100"));      // 3
        System.out.println(s.strobogrammaticInRange("123456", "1000000000000000"));      // 312393
    }

    char[][] pairs = {{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}};
    int count = 0;
    // Tips:
    // Construct char array from lenLow to lenHigh and increase count when s is between low and high.
    // Add the stro pairs from outside to inside until left > right.
    public int strobogrammaticInRange(String low, String high) {
        for(int len = low.length(); len <= high.length(); len++) {
            dfs(low, high, new char[len], 0, len - 1);
        }
        return count;
    }

    public void dfs(String low, String high, char[] c, int left, int right) {
        if(left > right) {
            String s = new String(c);
            if((s.length() == low.length() && s.compareTo(low) < 0) ||
                    (s.length() == high.length() && s.compareTo(high) > 0)) {
                return;
            }
            count++;
            return;
        }

        for(char[] p : pairs) {
            c[left] = p[0];
            c[right] = p[1];
            if(c.length != 1 && c[0] == '0') continue;
            if(left < right || left == right && p[0] == p[1]) {
                dfs(low, high, c, left + 1, right - 1);
            }
        }
    }
}

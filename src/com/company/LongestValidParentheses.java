package com.company;

import java.util.*;

public class LongestValidParentheses {
    public static void main(String[] args) {
        System.out.println("main");
        LongestValidParentheses s = new LongestValidParentheses();
        String input = "(()()(()";
        System.out.println(s.longestValidParentheses(input));

        input = ")()())";
        System.out.println(s.longestValidParentheses(input));

        input = ")))((";
        System.out.println(s.longestValidParentheses(input));
    }

    // Time O(n)
    // Space O(1)
    // chars between [left, right] is a valid substring of Parentheses
    public int longestValidParentheses(String input) {

        int max = 0;
        int i = 0;
        int j = 0;
        int leftNum = 0;
        int rightNum = 0;
        while (j < input.length()) {
            if (input.charAt(j) == '(') {
                j++;
                leftNum++;
            } else if (input.charAt(j) == ')' && leftNum > rightNum) {
                j++;
                rightNum++;
            } else {
                if (input.charAt(i) == '(') {
                    leftNum--;
                } else {
                    rightNum--;
                }
                i++;
            }
            if (leftNum == rightNum) {
                max = Math.max(max, j - i);
            }
        }
        return max;
    }
}

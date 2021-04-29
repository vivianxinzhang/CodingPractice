package com.company;

import java.util.*;

public class LongestValidParentheses {
    public static void main(String[] args) {
        LongestValidParentheses s = new LongestValidParentheses();
        String input = ")()())";
        System.out.println(s.longestValidParentheses(input));   // 4  ()()

        input = ")))((";
        System.out.println(s.longestValidParentheses(input));   // 0

        input = "((())()))";
        System.out.println(s.longestValidParentheses(input));   // 8  ((())())
    }

    // Time O(n)
    // Space O(n)
    public int longestValidParentheses(String input) {
        // index of parenthesis
        Deque<Integer> stack = new ArrayDeque<>();
        int longest = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {   // left parenthesis
                stack.offerFirst(i);
            } else {    // right parenthesis
                // previous section of match + the current section of match
                // ( (**)  ((()))
                // ( (**)  ((**))
                // ( (**)  (****)
                //         \--6-/
                // how to get length of previous section of match?
                // the matched section is not in the stack, but know the nearest unmatched left bracket
                // 中间的空隙代表之前对消掉了合法的左右括号substring
                if (!stack.isEmpty() && input.charAt(stack.peekFirst()) == '(') {
                    stack.pollFirst();
                    int leftIdx = stack.isEmpty() ? 0 : stack.peekFirst() + 1;
                    longest = Math.max(longest, i - leftIdx + 1);
                } else {
                    stack.offerFirst(i);
                }
            }
        }
        return longest;
    }

    // Method 1: brute force
    // Time: O(n^3)
    // Space: O(n)
    public int longestValidParenthesesI(String s) {
        int maxlen = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 2; j <= s.length(); j+=2) {
                if (isValid(s.substring(i, j))) {
                    maxlen = Math.max(maxlen, j - i);
                }
            }
        }
        return maxlen;
    }

    public boolean isValid(String input) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                stack.push('(');
            } else if (!stack.isEmpty() && stack.peek() == '(') {
                stack.pollFirst();
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }
}

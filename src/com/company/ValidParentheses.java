package com.company;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class ValidParentheses {
    public static void main(String[] args) {
        ValidParentheses s = new ValidParentheses();
        System.out.println(s.isValid(""));
        System.out.println(s.isValid(")("));
        System.out.println(s.isValid("()"));
    }

    public boolean isValid(String input) {
        // Write your solution here
        if (input == null || input.length() == 0) {
            return true;
        }
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (i == 0 || ch == '(' || ch == '[' || ch == '{') {
                // left parenthesis
                stack.offerFirst(ch);
            } else {
                // right parenthesis
                if (stack.isEmpty() || !match(stack.peekFirst(), ch)) {
                    return false;
                } else {
                    stack.pollFirst();
                }
            }
        }
        return stack.isEmpty();
    }

    private boolean match(Character left, Character right) {
        return (left == '(' && right == ')') || (left == '[' && right == ']') || (left == '{' && right == '}');
    }
}

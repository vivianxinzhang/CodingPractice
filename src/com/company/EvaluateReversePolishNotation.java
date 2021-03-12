package com.company;
import java.util.*;

public class EvaluateReversePolishNotation {
    public static void main(String[] args) {
        EvaluateReversePolishNotation s = new EvaluateReversePolishNotation();
        String[] tokens = new String[] {"2", "1", "+", "3", "*"};
        System.out.println(s.evalRPN(tokens));  // (2 + 1) * 3 = 9

        tokens = new String[] {"4", "13", "5", "/", "+"};
        System.out.println(s.evalRPN(tokens));  // (4 + (13 / 5)) -> 6
    }

    // Time O(n)
    // Space O(n)
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return -1;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        int result = 0;
        for (int i = 0; i < tokens.length; i++) {
            if (isOperator(tokens[i])) {
                int tmp = compute(stack, tokens[i]);
                stack.offerFirst(tmp);
            } else {
                stack.offerFirst(Integer.valueOf(tokens[i]));
            }
        }
        return stack.peekFirst();
    }

    private int compute(Deque<Integer> stack, String token) {
        int right = stack.pollFirst();
        int left = stack.pollFirst();
        if (token == "+") {
            return left + right;
        } else if (token == "-") {
            return left - right;
        } else if (token == "*") {
            return left * right;
        } else {
            return left / right;
        }
    }

    private boolean isOperator(String token) {
        return token == "+" || token == "-" || token == "*" || token == "/";
    }

}

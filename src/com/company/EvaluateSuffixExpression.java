package com.company;
import java.util.ArrayDeque;
import java.util.Deque;

public class EvaluateSuffixExpression {
    public static void main(String[] args) {
        EvaluateSuffixExpression s = new EvaluateSuffixExpression();
        String[] suffix = new String[]{"2","12","4","+","*"};
        System.out.println(s.evaluate(suffix));
    }

    public int evaluate(String[] suffix) {
        // Write your solution here
        int result = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (String token : suffix) {
            if ("+-*/".indexOf(token) >= 0) {
                int nRight = stack.pollFirst();
                int nLeft = stack.pollFirst();
                int tmp = calculate(nLeft, nRight, token);
                stack.push(tmp);
            } else {
                stack.offerFirst(Integer.valueOf(token));
            }
        }
        return stack.peekFirst();
    }

    private int calculate(int nLeft, int nRight, String operator) {
        if (operator == "+") {
            return nLeft + nRight;
        } else if (operator == "-") {
            return nLeft - nRight;
        } else if (operator == "*") {
            return nLeft * nRight;
        } else {
            return nLeft / nRight;
        }
    }
}

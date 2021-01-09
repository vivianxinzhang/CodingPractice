package com.company;
import java.util.*;

public class ValidParentheses {
    public static void main(String[] args) {
        ValidParentheses s = new ValidParentheses();
        System.out.println(s.isValid(""));
        System.out.println(s.isValid("["));
        System.out.println(s.isValid("()"));
    }

    // iterate through the input string and judge if every character in the input will be appropriate for a valid sequence
    // case 1: left parenthesis, will match with future right parenthesis
    //         push to stack
    // case 2: right parenthesis, match with nearest unmatched left parenthesis
    //         2.1 no left parenthesis to match with  return false
    //         2.2 cannot match     return false
    //         2.3 matched  remove top element from stack
    // Time O(n)
    // Space O(n)
    public boolean isValid(String input) {
        if (input == null || input.length() == 0) {
            return true;
        }
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < input.length(); i++) {
            Character curr = input.charAt(i);
            // left parenthesis
            if (curr == '(' || curr == '[' || curr == '{') {
                stack.offerFirst(curr);
            } else {
                // right parenthesis
                if (stack.isEmpty() || !match(stack.peekFirst(), curr)) {
                    return false;
                }
                stack.pollFirst();
            }
        }
        return stack.isEmpty();
    }

    private boolean match(Character left, Character right) {
        return (left == '(' && right == ')') || (left == '[' && right == ']') || (left == '{' && right == '}');
    }
}

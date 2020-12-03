package com.company;
import java.util.*;

public class AllValidPermutationsOfParenthesesI {
    public static void main(String[] args) {
        AllValidPermutationsOfParenthesesI s = new AllValidPermutationsOfParenthesesI();
        System.out.println(s.validParentheses(0)); // []
        System.out.println(s.validParentheses(1)); // [()]
        System.out.println(s.validParentheses(2)); // [(()), ()()]
        System.out.println(s.validParentheses(3)); // [((())), (()()), (())(), ()(()), ()()()]
    }

    // O(2^(2n)*n)  <--  O(2^(2n)*2n)
    // Space O(n) <-- recursion 2n层 + char[] ch takes 2n
    // 最后一层2^n个node  总共2^2n个node 最后一层需要打印 dominate时间复杂度
    // 打印 for loop里面套着 printSpace O(n) 所以print的时间复杂度为 O(2n)
    public List<String> validParentheses(int n) {
        // Write your solution here
        List<String> result = new ArrayList<>();
        // the final string contains 2k characters
        char[] cur = new char[n * 2];
        helper(cur, n, n, 0, result);
        return result;
    }

    // left: how many '(' we still have
    // right: how many ')' we still have
    // index: the current position in cur we want to fill in with either '(' or ')'
    private void helper(char[] cur, int left, int right, int index, List<String> result) {
        // terminate condition:
        // when we do not have any parentheses left
        if (index == cur.length) {
            result.add(new String(cur));
            return;
        }
        // when we can add a '(' ?
        // when there is some '(' we can still use
        if (left > 0) {
            cur[index] = '(';
            helper(cur, left - 1, right, index + 1, result);
            // Notice: it looks like we do not do anything when back tracking
            // to the previous level, the code is still right because:
            // 1. we are setting the character at index and when back tracking,
            // what we just need to do is 1) remove the character at index and
            // 2) add a different character at index
            // 2. only when we fill in all the positions in cur, we have a complete solution
            // the code itself actually already suffices the above two points and it already
            // does the correct removing operation when back tracked to previous level
        }
        // when we can add a ')' ?
        // when there is more '(' than ') used
        // because each ')' should be associated with a previous '('
        if (right > left) {
            cur[index] = ')';
            helper(cur, left, right - 1, index + 1, result);
        }
    }
}

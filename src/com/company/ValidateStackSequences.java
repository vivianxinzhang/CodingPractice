package com.company;
import java.util.*;

public class ValidateStackSequences {
    public static void main(String[] args) {
        ValidateStackSequences s = new ValidateStackSequences();
        int[] pushed = new int[]{1, 2, 3, 4, 5};
        int[] popped = new int[]{4, 5, 3, 2, 1};
        System.out.println(s.validateStackSequences(pushed, popped));   // true

        pushed = new int[]{1, 2, 3, 4, 5};
        popped = new int[]{4, 3, 5, 1, 2};
        System.out.println(s.validateStackSequences(pushed, popped));   // false
    }

    // Time O(n)
    // Space O(n)
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stack = new ArrayDeque<>();
        int i = 0;
        for (int num : pushed) {
            stack.offerFirst(num);
            while (!stack.isEmpty() && stack.peekFirst() == popped[i]) {
                stack.pop();
                i++;
            }
        }
        return stack.isEmpty();
    }
}
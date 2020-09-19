package com.company;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;

// Time O(m + n)
// Space O(m)
public class NextGreaterElement {
    public static void main(String[] args) {
        NextGreaterElement s = new NextGreaterElement();
        int[] all = new int[]{1, 5};
        int[] partial = new int[]{1};
        System.out.println(Arrays.toString(s.nextGreaterElement(partial, all)));
    }

    public int[] nextGreaterElement(int[] partial, int[] all) {
        // Write your solution here
        int[] result = new int[partial.length];
        HashMap<Integer, Integer> nextGreater = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int ele : all) {
            while (!stack.isEmpty() && ele > stack.peekFirst()) {
                nextGreater.put(stack.pop(), ele);
            }
            stack.push(ele);
        }
        for (int i = 0; i < partial.length; i++) {
            result[i] = nextGreater.getOrDefault(partial[i], -1);
        }
        return result;
    }
}

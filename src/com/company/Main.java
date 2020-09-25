package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] array = new int[] {1,3,3,6,9,9,12,15};
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(2);
        stack.push(1);
        stack.push(4);
        stack.push(3);
        System.out.println(stack);
        s.sort(stack);
        System.out.println(stack);
    }
}
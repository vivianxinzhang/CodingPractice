package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        Deque<Integer> stack = new ArrayDeque<>();
        stack.offerFirst(3);
        stack.offerFirst(1);
        stack.offerFirst(2);
        System.out.println(stack);
        s.sort(stack);
        System.out.println(stack);
    }
}
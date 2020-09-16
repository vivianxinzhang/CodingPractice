package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        Deque<Integer> s1 = new ArrayDeque<>();
        s1.offerFirst(4);
        s1.offerFirst(2);
        s1.offerFirst(1);
        s1.offerFirst(1);
        s1.offerFirst(3);
        s1.offerFirst(2);
        System.out.println(s1);
        s.sort(s1);
        System.out.println(s1);
    }
}
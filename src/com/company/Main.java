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
        int[] array = new int[] {1, 5};
        DictImpl dict = new DictImpl(new int[] {3, 2, 5});
    }
}
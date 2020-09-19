package com.company;
import java.util.*;

class Solution {
    public void shuffle(Deque<Integer> stack1, Deque<Integer> stack2) {
        while (!stack1.isEmpty()) {
            stack2.offerFirst(stack1.pollFirst());
        }
    }
}

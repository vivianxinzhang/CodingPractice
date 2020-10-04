package com.company;
import java.util.*;

// Time O(2^n)
// Space O(n)
public class SortTwoStacks {
    public static void main(String[] args) {
        SortTwoStacks s = new SortTwoStacks();
        Deque<Integer> s1 = new ArrayDeque<>();
        s1.offerFirst(1);
        s1.offerFirst(2);
        s1.offerFirst(3);
        s1.offerFirst(4);
        System.out.println(s1);
        s.sortI(s1);
        System.out.println(s1);
    }
    // sort in descending order and store result in the bottom part of input
    public void sort(LinkedList<Integer> s1) {
        if (s1 == null || s1.size() <= 1) {
            return;
        }
        // Initialize buffer stack and preMax
        LinkedList<Integer> s2 = new LinkedList<Integer>();
        // Initialize preMax, which helpers differ sorted area and unsorted area
        int preMax = Integer.MAX_VALUE;
        while (s1.peekFirst() < preMax) {
            // Initialize currMax, compare with stack1 unsorted elements to find currMax
            int curMax = Integer.MIN_VALUE;
            int count = 0;
            while (!s1.isEmpty() && s1.peekFirst() < preMax) {
                int curr = s1.pollFirst();
                if (curr == curMax) {
                    count++;
                } else if (curr > curMax) {
                    curMax = curr;
                    count = 1;
                }
                s2.offerFirst(curr);
            }
            // push currMax back to s1
            while (count > 0) {
                s1.offerFirst(curMax);
                count--;
            }
            // push other elements back to s1
            while (!s2.isEmpty()) {
                int tmp = s2.pollFirst();
                s1.offerFirst(tmp);
            }
            preMax = curMax;
        }
    }

    // sort in descending order and store result in the bottom part of input
    // input: result | unsorted elements
    // buffer: used to store elements temporarily when finding max each round
    public void sort(Deque<Integer> s1) {
        Deque<Integer> s2 = new ArrayDeque<>();
        // Write your solution here.
        int preMax = Integer.MAX_VALUE;
        while (!s1.isEmpty() && s1.peekFirst() < preMax) {
            Integer currMax = Integer.MIN_VALUE;
            int count = 0;
            while (!s1.isEmpty() && s1.peekFirst() < preMax) {
                int tmp = s1.pollFirst();
                if (tmp == currMax) {
                    count++;
                } else if (tmp > currMax) {
                    count = 1;
                    currMax = tmp;
                }
                s2.offerFirst(tmp);
            }
            while (count > 0) {
                s1.offerFirst(currMax);
                count--;
            }
            while (!s2.isEmpty()) {
                int tmp = s2.pollFirst();
                if (tmp != currMax) {
                    s1.offerFirst(tmp);
                }
            }
            preMax = currMax;
        }
    }

    // no duplicates
    public void sortI(Deque<Integer> s1) {
        Deque<Integer> s2 = new ArrayDeque<>();
        // Write your solution here.
        int preMax = Integer.MAX_VALUE;
        while (!s1.isEmpty() && s1.peekFirst() < preMax) {
            int currMax = Integer.MIN_VALUE;
            while (!s1.isEmpty() && s1.peekFirst() < preMax) {
                int tmp = s1.pollFirst();
                if (tmp >= currMax) {
                    currMax = tmp;
                }
                s2.offerFirst(tmp);
            }
            s1.offerFirst(currMax);
            while (!s2.isEmpty()) {
                int tmp = s2.pollFirst();
                if (tmp != currMax) {
                    s1.offerFirst(tmp);
                }
            }
            preMax = currMax;
        }
    }
}

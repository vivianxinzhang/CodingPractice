package com.company;
import java.util.ArrayDeque;
import java.util.Deque;

public class SortWithTwoStacks {
    public static void main(String[] args) {
        SortWithTwoStacks s = new SortWithTwoStacks();
        Deque<Integer> s1 = new ArrayDeque<>();
        s1.offerFirst(1);
        s1.offerFirst(2);
        s1.offerFirst(3);
        s1.offerFirst(4);
        System.out.println(s1);     // [1, 2, 3, 4
        s.sortI(s1);
        System.out.println(s1);     // [4, 3, 2, 1
    }

    // sort in descending order and store result in the bottom part of input
    // Time O(n^2)
    // Space O(n)
    public void sort(Deque<Integer> s1) {
        if (s1 == null || s1.size() <= 1) {
            return;
        }
        // Initialize buffer stack and preMax
        // preMax helpers differ sorted area and unsorted area
        Deque<Integer> s2 = new ArrayDeque<>();
        int preMax = Integer.MAX_VALUE;
        while (s1.peekFirst() < preMax) {
            // Initialize currMax, compare each stack1 unsorted element with currMax
            // to find real currMax
            int currMax = Integer.MIN_VALUE;
            int count = 0;
            while (!s1.isEmpty() && s1.peekFirst() < preMax) {
                int curr = s1.pollFirst();
                if (curr == currMax) {
                    count++;
                } else if (curr > currMax) {
                    currMax = curr;
                    count = 1;
                }
                s2.offerFirst(curr);
            }
            // push currMax back to s1
            while (count > 0) {
                s1.offerFirst(currMax);
                count--;
            }
            // push elements other than currMax back to s1
            while (!s2.isEmpty()) {
                int tmp = s2.pollFirst();
                if (tmp != currMax) {
                    s1.offerFirst(tmp);
                }
            }
            preMax = currMax;
        }
    }

    // sort in descending order and store result in the bottom part of input
    // input: result | unsorted elements
    // buffer: used to store elements temporarily when finding max each round
    public void sortII(Deque<Integer> s1) {
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

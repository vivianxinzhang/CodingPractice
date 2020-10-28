package com.company;
import java.util.*;

public class NumberCrashes {
    public static void main(String[] args) {
        NumberCrashes s = new NumberCrashes();

        int[] array = new int[] {-3, 3, -5, 5};   // [-3, -5, 5]
        System.out.println(Arrays.toString(s.numberCrash(array)));

        array = new int[] {-3, 5, 3, -5}; // [-3]
        System.out.println(Arrays.toString(s.numberCrash(array)));

        array = new int[] {7, 1, -2, -4, 4, -5, -5, 3};   // [7, 3]
        System.out.println(Arrays.toString(s.numberCrash(array)));

        array = new int[] {3, -5, -3, 5};
        System.out.println(Arrays.toString(s.numberCrash(array)));
    }

    // Time O(n)
    // Space O(n)
    public int[] numberCrash(int[] nums) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int num : nums) {
            if (stack.isEmpty() || num >= 0) {  // num is positive
                stack.offerFirst(num);
            } else if (stack.peekFirst() <= 0) {    // num is negative and pre is also negative
                stack.offerFirst(num);
            } else {    // num is negative and pre is positive
                int tmp = stack.peekFirst();
                while (!stack.isEmpty() && stack.peekFirst() > 0) {
                    if (stack.peekFirst() > -num) {
                        break;
                    } else if (stack.peekFirst() == -num) {
                        stack.pollFirst();
                        break;
                    } else {
                        stack.pollFirst();
                    }
                }
            }
        }
        return toArray(stack);
    }

    private int[] toArray(Deque<Integer> stack) {
        int[] array = new int[stack.size()];
        for (int i = array.length - 1; i >= 0; i--) {
            array[i] = stack.pollFirst();
        }
        return array;
    }
}

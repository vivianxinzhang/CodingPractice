package com.company;
import java.util.*;

public class NumberCrashes {
    public static void main(String[] args) {
        NumberCrashes s = new NumberCrashes();

        int[] array = new int[] {-3, 3, 5, -5}; // [-3, 3]
        System.out.println(Arrays.toString(s.numberCrash(array)));

        array = new int[] {-3, 3, -5, 5};   // [-3, -5, 5]
        System.out.println(Arrays.toString(s.numberCrash(array)));

        array = new int[] {-3, 5, 3, -5}; // [-3]
        System.out.println(Arrays.toString(s.numberCrash(array)));

        array = new int[] {3, -5, -3, 5};   // [-5, -3, 5]
        System.out.println(Arrays.toString(s.numberCrash(array)));
    }

    // Time O(n)
    // Space O(n)
    public int[] numberCrash(int[] nums) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {  // pos -->
                stack.offerFirst(nums[i]);
            } else {    // neg  <--
                if (stack.isEmpty() || stack.peekFirst() < 0) {
                    stack.offerFirst(nums[i]);
                } else {
                    while (!stack.isEmpty() && stack.peekFirst() > 0 && stack.peekFirst() < -nums[i]) {
                        stack.pollFirst();
                    }
                    if (stack.isEmpty() || stack.peekFirst() < 0) {
                        stack.offerFirst(nums[i]);
                    } else {
                        int top = stack.peekFirst();    // pos
                        if (top == -nums[i]) {
                            stack.pollFirst();
                        } else if (top < -nums[i]) {
                            stack.pollFirst();
                            stack.offerFirst(nums[i]);
                        }
                    }
                }
            }
        }
        return toIntArray(stack);
    }

    private int[] toIntArray(Deque<Integer> stack) {
        int[] array = new int[stack.size()];
        for (int i = array.length - 1; i >= 0; i--) {
            array[i] = stack.pollFirst();
        }
        return array;
    }

    // need polish
    // Time O(n)
    // Space O(n)
    public int[] numberCrashI(int[] nums) {
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

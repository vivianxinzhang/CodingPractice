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

    // Condition:
    // if stack is empty or the top element >= 0 && current value <= 0, Keep popping the previous elements whose abs value < current value,
    // when the stack is empty or the previous number < 0, stop and put the current value onto the stack
    // if the abs value of current number and top element is the same, pop the stack and break out of the loop.
    // if the current abs value < top element in the stack, just break out of the loop.
    // otherwise, push the current element on to the stack
    // Time O(n)
    // Space O(n)
    public int[] numberCrash(int[] nums) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {  // pos --> move right
                stack.offerFirst(nums[i]);  // waiting to be matched
            } else if (stack.isEmpty() || stack.peekFirst() < 0) {  // neg  <-- move left
                    stack.offerFirst(nums[i]);
            } else {  // neg  <-- move left
                // remove all pos elements on the left with smaller abs value
                while (!stack.isEmpty() && stack.peekFirst() > 0 && stack.peekFirst() < -nums[i]) {
                    stack.pollFirst();
                }
                if (stack.isEmpty() || stack.peekFirst() < 0) {
                    stack.offerFirst(nums[i]);
                } else if (stack.peekFirst() == -nums[i]) {
                    stack.pollFirst();
                } else if (stack.peekFirst() < -nums[i]) {
                        stack.pollFirst();
                        stack.offerFirst(nums[i]);
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

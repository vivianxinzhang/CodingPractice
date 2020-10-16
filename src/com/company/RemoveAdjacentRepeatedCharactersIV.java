package com.company;
import java.util.*;

public class RemoveAdjacentRepeatedCharactersIV {
    public static void main(String[] args) {
        System.out.println("main");
        RemoveAdjacentRepeatedCharactersIV s = new RemoveAdjacentRepeatedCharactersIV();
        String input = "abbbaaccz";
        System.out.println(s.deDup(input));

        input = "aabccdc";
        System.out.println(s.deDup(input));
    }

    // Time O(n)
    // Space O(n)
    public String deDup(String input) {
        // Write your solution here
        if (input == null || input.length() <= 1) {
            return input;
        }
        // try to convert the String to char[], and do it in-place
        char[] array = input.toCharArray();
        // instead of using a extra stack explicitly, we can actually reuse the left side
        // of the original char[] as the "stack".
        // end: is where the top of the stack is
        int end = 0;
        for (int i = 1; i < array.length; i++) {
            // if the stack is empty (when end == -1) or there is no duplicate chars,
            // we are able to push the character into the stack
            if (end == -1 || array[i] != array[end]) {
                array[++end] = array[i];
            } else {
                // otherwise, we need to pop the top element by end--,
                // and ignore all the consecutive duplicate chars
                end--;
                while (i + 1 < array.length && array[i] == array[i + 1])
                    i++;
            }
        }
        return new String(array, 0, end + 1);
    }

    // Time O(n)
    // Space O(n)
    public String deDupII(String input) {
        // Write your solution here
        if (input == null || input.length() <= 1) {
            return input;
        }
        // try to convert the String to char[], and do it in-place
        char[] array = input.toCharArray();
        // instead of using a extra stack explicitly, we can actually reuse the left side
        // of the original char[] as the "stack".
        // end: is where the top of the stack is
        int slow = -1;
        int fast = 0;
        while (fast < array.length) {
            // if the stack is empty (when end == -1) or there is no duplicate chars,
            // we are able to push the character into the stack
            if (slow == -1 || array[fast] != array[slow]) {
                array[++slow] = array[fast];
                fast++;
            } else {
                // otherwise, we need to pop the top element by end--,
                // and ignore all the consecutive duplicate chars
                while (fast < array.length && array[fast] == array[slow]) {
                    fast++;
                }
                slow--;
            }
        }
        return new String(array, 0, slow + 1);
    }

    // Time O(n)
    // Space O(n)
    public String deDupI(String input) {
        if (input == null || input.length() == 0) {
            return input;
        }
        char[] array = input.toCharArray();
        // f = 0: (fast) the letter being processed. In other words,
        // all letters to the left side of j (not including j) are processed letters.
        // stack: all the processed letters that should be kept
        Deque<Character> stack = new ArrayDeque<>();
        int i = 0;
        // Case 1: a[f] != stack.peekFirst(), then stack.push(a[f]), f++
        // Case 2: a[f] == stack.peekFirst()
        //         keep moving f until a[f] != stack.top()
        //         stack.pop()
        while (i < array.length) {
            if (stack.isEmpty() || array[i] != stack.peek()) {
                stack.push(array[i]);
                i++;
            } else {
                while (i < array.length && array[i] == stack.peek()) {
                    i++;
                }
                stack.pop();
            }
        }
        int size = stack.size();
        for (i = size - 1; i >= 0; i--) {
            array[i] = stack.pop();
        }
        return new String(array, 0, size);
    }
}

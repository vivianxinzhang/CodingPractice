package com.company;

public class RemoveAdjacentRepeatedCharactersII {
    public static void main(String[] args) {
        RemoveAdjacentRepeatedCharactersII s = new RemoveAdjacentRepeatedCharactersII();
        System.out.println("main");
        String input = "aaaabbbc";
        System.out.println(s.deDup(input));
    }

    // convert the input to char[], and do it in place
    // repeated characters will be ignored except for the first character in the sequence
    // Time O(n)
    // Space O(n)
    public String deDup(String input) {
        // Write your solution here
        if (input == null || input.length() <= 2) {
            return input;
        }
        char[] array = input.toCharArray();
        int slow = 2;
        int fast = 2;
        while (fast < array.length) {
            if (array[fast] == array[slow - 2] && array[fast] == array[slow - 1]) {
                while (fast < array.length && array[fast] == array[slow - 2]) {
                    fast++;
                }
            } else {
                array[slow++] = array[fast++];
            }
        }
        return new String(array, 0, slow);
    }
}

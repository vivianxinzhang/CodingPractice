package com.company;

public class RemoveAdjacentRepeatedCharactersI {
    public static void main(String[] args) {
        RemoveAdjacentRepeatedCharactersI s = new RemoveAdjacentRepeatedCharactersI();
        System.out.println("main");
        String input = "aa";
        System.out.println(s.deDup(input));
    }

    // convert the input to char[], and do it in place
    // repeated characters will be ignored except for the first character in the sequence
    // Time O(n)
    // Space O(n)
    public String deDup(String input) {
        // Write your solution here
        if (input == null || input.length() <= 1) {
            return input;
        }
        char[] array = input.toCharArray();
        int slow = 1;
        int fast = 1;
        while (fast < input.length()) {
            if (array[fast] == array[slow - 1]) {
                while (fast < array.length && array[fast] == array[slow - 1]) {
                    fast++;
                }
            } else {
                array[slow++] = array[fast++];
            }
        }
        return new String(array, 0, slow);
    }
}

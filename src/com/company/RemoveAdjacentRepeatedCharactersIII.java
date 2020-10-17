package com.company;

public class RemoveAdjacentRepeatedCharactersIII {
    public static void main(String[] args) {
        RemoveAdjacentRepeatedCharactersIII s = new RemoveAdjacentRepeatedCharactersIII();
        System.out.println("main");
        String input = "aa";
        System.out.println(s.deDup(input));

        input = "aaaabbbc";
        System.out.println(s.deDup(input));
    }

    // Time O(n)
    // Space O(n)
    public String deDup(String input) {
        // Write your solution here
        if (input == null || input.length() <= 1) {
            return input;
        }
        char[] array = input.toCharArray();
        int slow = -1;
        int fast = 0;
        while (fast < array.length) {
            if (slow == -1 || array[fast] != array[slow]) {
                array[++slow] = array[fast];
                fast++;
            } else {
                while (fast < array.length && array[fast] == array[slow]) {
                    fast++;
                }
                slow--;
            }
        }
        return new String(array, 0, slow + 1);
    }
}

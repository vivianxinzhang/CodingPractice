package com.company;

public class RemoveAdjacentRepeatedCharactersIII {
    public static void main(String[] args) {
        RemoveAdjacentRepeatedCharactersIII s = new RemoveAdjacentRepeatedCharactersIII();

        String input = "aa";
        System.out.println(s.deDup(input));     //

        input = "aaaabbbc";
        System.out.println(s.deDup(input));     // c

        input = "abbccde";
        System.out.println(s.deDup(input));     // ade
    }

    // Implementation 3:
    // Assumptions: Try to do it in place.
    // Time O(n)
    // Space O(n)
    public String deDup(String input) {
        if (input == null || input.length() <= 1) {
            return input;
        }
        char[] array = input.toCharArray();
        int slow = 0;
        int fast = 0;
        while (fast < array.length) {
            int fastNext = fast + 1;
            if (fastNext < array.length && array[fastNext] == array[fast]) {
                while (fastNext < array.length && array[fastNext] == array[fast]) {
                    fastNext++;
                }
                fast = fastNext;
            } else {
                array[slow++] = array[fast++];
            }
        }
        return new String(array, 0, slow);
    }

    // Implementation 2:
    // Time O(n)
    // Space O(n)
    public String deDupII(String input) {
        if (input == null || input.length() <= 1) {
            return input;
        }
        char[] array = input.toCharArray();
        int slow = 0;
        int fast = 1;
        while (fast < array.length) {
            if (slow >= 0 && array[fast] == array[slow]) {
                while (fast < array.length && array[fast] == array[slow]) {
                    fast++;
                }
                slow--;
            } else {
                slow++;
                array[slow] = array[fast];
                fast++;
            }
        }
        return new String(array, 0, slow + 1);
    }

    // Implementation 1:
    // Time O(n)
    // Space O(n)
    public String deDupI(String input) {
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

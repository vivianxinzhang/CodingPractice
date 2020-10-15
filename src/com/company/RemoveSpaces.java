package com.company;

public class RemoveSpaces {
    public static void main(String[] args) {
        RemoveSpaces s = new RemoveSpaces();
        System.out.println("main");
        String input = " a";
        System.out.println(s.removeSpaces(input));

        input = "   I      love MTV   ";
        System.out.println(s.removeSpaces(input));
    }

    // Assumption: input is not null
    // Time O(n)
    // Space O(n)
    public String removeSpaces(String input) {
        // Write your solution here
        char[] array = input.toCharArray();
        int slow = 0;
        int fast = 0;
        while (fast < array.length) {
            if ((fast == 0 || array[fast - 1] == ' ') && array[fast] == ' ') {
                fast++;
            } else {
                array[slow++] = array[fast++];
            }
        }
        // post-processing
        if (slow > 0 && array[slow - 1] == ' ') {
            slow--;
        }
        return new String(array, 0, slow);
    }

    // Assumption: input is not null
    // Time O(n)
    // Space O(n)
    public String removeSpacesI(String input) {
        // Write your solution here
        if (input.isEmpty()) {
            return input;
        }
        char[] array = input.toCharArray();
        int end = 0;
        for (int i = 0; i < array.length; i++) {
            // when we would ignore the current space character:
            // 1. we ignore all the space characters followed by another space character, so that
            // if there are several consecutive space characters, only the first one will be remained
            // 2. we ignore also the space character if it is the first character of the input string
            // post-processing: it is possible we still have one trailing
            // space character at the end
            if (array[i] == ' ' && (i == 0 || array[i - 1] == ' ')) {
                continue;
            }
            array[end++] = array[i];
        }
        // post-processing
        if (end > 0 && array[end - 1] == ' ') {
            end--;
        }
        return new String(array, 0, end);
    }
}

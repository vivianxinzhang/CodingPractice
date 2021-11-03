package com.company;

public class ReverseWordsInASentenceII {
    public static void main(String[] args) {
        ReverseWordsInASentenceII s = new ReverseWordsInASentenceII();

        System.out.println(s.reverseWords("   I love    Google"));
        // Google love I
        System.out.println(s.reverseWords("  Life    is Happy   "));
        // Happy is Life
    }

    // Corner Cases:
    // If the given string is null, we do not need to do anything.
    // Time O(n)
    // Space O(n)
    public String reverseWords(String input) {
        if (input == null || input.length() == 0) {
            return input;
        }
        char[] array = input.toCharArray();
        int start = 0;
        int end = removeSpaces(array);
        reverse(array, 0, end);
        for (int i = 0; i <= end; i++) {
            if (i == 0 || array[i - 1] == ' ') {
                start = i;
            }
            if (i == array.length - 1 || array[i + 1] == ' ') {
                reverse(array, start, i);
            }
        }
        return new String(array, 0, end + 1);
    }

    private int removeSpaces(char[] array) {
        int slow = 0;
        int fast = 0;
        while (fast < array.length) {
            if (array[fast] == ' ' && (fast == 0 || array[fast - 1] == ' ')) {
                fast++;
            } else {
                array[slow++] = array[fast++];
            }
        }
        if (slow > 0 && array[slow - 1] == ' ') {
            slow--;
        }
        return slow - 1;
    }

    private void reverse(char[] array, int i, int j) {
        while (i < j) {
            char tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
            i++;
            j--;
        }
    }
}

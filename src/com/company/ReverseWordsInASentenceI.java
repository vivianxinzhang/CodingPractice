package com.company;

public class ReverseWordsInASentenceI {
    public static void main(String[] args) {
        ReverseWordsInASentenceI s = new ReverseWordsInASentenceI();
        System.out.println("main");
    }

    // Assumptions:
    // 1) The words are separated by one space character
    // 2) There are no leadning or trailing spaces
    // 3) input is not null
    // Time O(n)
    // Space O(n)
    public String reverseWords(String input) {
        // Write your solution here
        if (input == null || input.length() == 0) {
            return input;
        }
        // try to convert it to char array and solve the problem in place
        char[] array = input.toCharArray();
        // 1. reverse the whole char array
        reverse(array, 0, array.length - 1);
        // 2. reverse each word of the array
        int start = 0;
        for (int i = 0; i < array.length; i++) {
            // find the start index of a word and the end index of each word
            if (array[i] != ' ' && (i == 0 || array[i - 1] == ' ')) {
                start = i;
            }
            if (array[i] != ' ' && (i == array.length - 1 || array[i + 1] == ' ')) {
                reverse(array, start, i);
            }
        }
        return new String(array);
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

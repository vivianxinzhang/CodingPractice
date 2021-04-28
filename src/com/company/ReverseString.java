package com.company;

public class ReverseString {
    public static void main(String[] args) {
        ReverseString s = new ReverseString();
        System.out.println(s.reverse(""));      // ""
        System.out.println(s.reverse("ab"));    // ba
        System.out.println(s.reverse("hello")); // olleh
    }

    // Assumptions:
    // The given string is not null.
    // Time O(n)
    // Space O(n)
    public String reverse(String input) {
        if (input == null || input.length() <= 1) {
            return input;
        }
        char[] array = input.toCharArray();
        int i = 0;
        int j = array.length - 1;
        while (i < j) {
            char tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
            i++;
            j--;
        }
        return new String(array);
    }
}
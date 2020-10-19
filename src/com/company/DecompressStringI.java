package com.company;
import java.util.*;

public class DecompressStringI {
    public static void main(String[] args) {
        System.out.println("main");
        DecompressStringI s = new DecompressStringI();
        String input = "ac2d3ef";
        System.out.println(s.decompress(input));
    }

    // Time O(n)
    // Space O(n)
    public String decompress(String input) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if (isLetter(input.charAt(i))){
                sb.append(input.charAt(i));
            } else {
                int count = input.charAt(i) - '0';
                while (count > 1) {
                    sb.append(input.charAt(i - 1));
                    count--;
                }
            }
        }
        return sb.toString();
    }


    private boolean isLetter(char input) {
        if (input >= 'a' && input <= 'z') {
            return true;
        }
        return false;
    }
}

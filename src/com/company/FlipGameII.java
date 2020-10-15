package com.company;
import java.util.*;

public class FlipGameII {
    public static void main(String[] args) {
        FlipGameII s = new FlipGameII();
        System.out.println("main");

        String input = "++++";
        System.out.println("++++ should return true");
        System.out.println(s.canWin(input));
        System.out.println(s.canWinI(input));
        System.out.println(s.canWinII(input));

        input = "+++----+++";
        System.out.println("+++----+++ should return false");
        System.out.println(s.canWin(input));
        System.out.println(s.canWinI(input));
        System.out.println(s.canWinII(input));

//        input = "+++++++++";
//        System.out.println("+++++++++ should return false");
//        System.out.println(s.canWin(input));

//        input = "+-+";
//        System.out.println("+-+ should return false");
//        System.out.println(s.canWin(input));
    }

    // Time O((n/2)!)
    // Space O(n)


    // Assume input != null && input.length() >= 2
    // Time O((n/2)!)
    // Space O(n)
    public boolean canWin(String input) {
        if (input == null || input.length() < 2) {
            return false;
        }
        char[] array = input.toCharArray();
        return canWin(array);
    }

    private boolean canWin(char[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] == '+' && array[i + 1] == '+') {
                array[i] = '-';
                array[i + 1] = '-';
                boolean otherCanWin = canWin(array);
                if (!otherCanWin) {
                    return true;
                }
                array[i] = '+';
                array[i + 1] = '+';
            }
        }
        return false;
    }

    public boolean canWinII(String input) {
        if (input == null || input.length() < 2) {
            return false;
        }
        char[] array = input.toCharArray();
        return canWinII(array);
    }

    private boolean canWinII(char[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] == '+' && array[i + 1] == '+') {
                char[] newState = Arrays.copyOf(array, array.length);
                newState[i] = '-';
                newState[i + 1] = '-';
                if (!canWinII(newState)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Time O((n^2)
    // Space O(n^2)
    public boolean canWinI(String input) {
        if (input == null || input.length() < 2) {
            return false;
        }
        for (int i = 0; i < input.length() - 1; i++) {
            if (input.charAt(i) == '+' && input.charAt(i + 1) == '+') {
                char[] array = input.toCharArray();
                array[i] = '-';
                array[i + 1] = '-';
                String nextState = new String(array);
                if (!canWin(nextState)) {
                    return true;
                }
            }
        }
        return false;
    }
}

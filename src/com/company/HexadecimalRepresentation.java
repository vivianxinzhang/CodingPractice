package com.company;

public class HexadecimalRepresentation {
    // Assumption: number > 0
    // Time O(1)
    // Space O(1)
    public String hex(int number) {
        String prefix = "0x";
        // handle the special case of 0 first
        if (number == 0) {
            return prefix + "0";
        }
        // using StringBuilder so append operation is more efficient
        StringBuilder sb = new StringBuilder();
        while (number > 0) {
            int rem = number % 16;
            if (rem < 10) {
                sb.append((char) ('0' + rem));
            } else {
                sb.append((char) (rem - 10 + 'A'));
            }
            number >>>= 4;
        }
        // reverse it at last so in all complexity is O(n)
        return prefix + sb.reverse().toString();
    }

    public String hexI(int number) {
        // Write your solution here
        // Assumption: number >= 0
        String prefix = "0x";
        // handle the special case of 0 first
        if (number == 0) {
            return prefix + "0";
        }
        // use StringBuilder so append operation is more efficient
        char[] base = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        boolean isLeading = true;
        StringBuilder sb = new StringBuilder();
        for (int i = 28; i >= 0; i -= 4) {
            char cur = base[(number >> i) & 0xF];
            if (cur != '0' || !isLeading) {
                sb.append(cur);
                isLeading = false;
            }
        }
        return prefix + sb.toString();
    }
}

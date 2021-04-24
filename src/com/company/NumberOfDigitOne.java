package com.company;

public class NumberOfDigitOne {
    public static void main(String[] args) {
        NumberOfDigitOne s = new NumberOfDigitOne();
        System.out.println(s.countDigitOne(13));    // 6
        // [1, 10, 11, 12, 13]

        System.out.println(s.countDigitOne(3374172));    // 3090311
    }

    //               345 [5]  78
    // Case 1: left part < 345
    //         combo left * combo right
    //               000      00
    //               ...      ...
    //               344      99
    //         num left + 1 * 10^(#ofdigits)
    //         total = 345 * 100
    // Case 2: left part is 345, need to compare digit with 1
    //     2.1: if digit > 1
    //               345 [6]  78
    //                        00
    //                        ...
    //                        99
    //                1  *  combo right
    //                1  *  100
    //     2.2: if digit = 1
    //               345 [1]  78
    //                        00
    //                        ...
    //                        78
    //                1  *  combo right
    //                1  *  (num right + 1）
    //     2.3: if digit = 0, cannot choose 1 when left part is 345
    //               345 [0]  78
    public int countDigitOne(int n) {
        if (n  < 0) {
            return 0;
        }
        int count = 0;
        String str = Integer.toString(n);
        // 345 [] 78
        for (int i = 0; i < str.length(); i++) {
            String left = str.substring(0, i);
            int leftNum = left.isEmpty() ? 0 : Integer.valueOf(left);
            int rightMax = (int) Math.pow(10, str.length() - 1 - i);
            // case 1:
            count += leftNum * rightMax;
            // case 2:
            int digit = str.charAt(i) - '0';
            // 2.1:
            if (digit > 1)
                count += rightMax;
                // 2.2:
            else if (digit == 1)
                count += n % rightMax + 1;
        }
        return count;
    }

    private String reverse(String input) {
        StringBuilder sb = new StringBuilder();
        for (int i = input.length() - 1; i >= 0; i--) {
            sb.append(input.charAt(i));
        }
        return sb.toString();
    }

    public int countDigitOneI(int n) {
        if (n  < 0) {
            return 0;
        }
        int count = 0;
        String str = Integer.toString(n);
        str = reverse(str);
        for (int i = 1; i <= str.length(); i++) {
            int leftNum = n / (int) Math.pow(10, i);
            // case 1:
            count += leftNum * (int) Math.pow(10, i - 1);
            // case 2:
            int digit = str.charAt(i - 1) - '0';
            // 2.1:
            if (digit > 1)
                count += Math.pow(10, i - 1);
            // 2.2:
            else if (digit == 1)
                count += n % (int) Math.pow(10,i - 1) + 1;
        }
        return count;
    }
}

package com.company;

public class NumberOfDigitOne {
    public static void main(String[] args) {
        NumberOfDigitOne s = new NumberOfDigitOne();

        System.out.println(s.countDigitOne(-1));        // 0
        System.out.println(s.countDigitOne(13));        // 6
        // [1, 10, 11, 12, 13]
        System.out.println(s.countDigitOne(3374172));   // 3090311
    }

    // for each digit, count how many numbers are there with this digit to be "1"
    // xxxx  1  xxxx ?  how many different numbers with this digit to be 1?
    // xxxxxxxxxx  1 ?  how many different numbers with this digit to be 1?
    //         combo left * combo right
    // e.g           345 [5]  78
    // Case 1: left part < 345
    //               000       00
    //               001       01
    //               ...       ...
    //               344       99
    //       total = 345    *  100
    //         (num left+1) * 10^(#ofdigits)
    //         total = 345  * 100
    // Case 2: left part is 345, need to compare digit with 1
    //    2.1: if digit > 1
    //               345 [6]  78
    //                        00
    //                        ...
    //                        99
    //                1  *  combo right
    //                1  *  100
    //    2.2: if digit = 1
    //               345 [1]  78
    //                        00
    //                        ...
    //                        78
    //                1  *  combo right
    //                1  *  (num right + 1）
    //     2.3: if digit = 0, cannot choose 1 when left part is 345
    //               345 [0]  78
    //               0
    // Time O(n)
    // Space O(1)
    public int countDigitOne(int n) {
        if (n  < 0) {
            return 0;
        }
        int count = 0;
        String str = Integer.toString(n);
        // 345 [x] 78
        for (int i = 0; i < str.length(); i++) {
            // String left = str.substring(0, i);
            // int leftNum = left.isEmpty() ? 0 : Integer.valueOf(left);
            // optimize calculate leftNum
            int leftNum = n / ((int) Math.pow(10, str.length() - i));
            int rightMax = (int) Math.pow(10, str.length() - 1 - i);
            // int leftNum = n / (10 * rightMax);
            // Case 1: left part < 345
            //               000      00
            //               001      01
            //               ...      ...
            //               344      99
            //       total = 345   *  100
            //         num left + 1 * 10^(#ofdigits)
            //         total = 345 * 100
            count += leftNum * rightMax;
            // Case 2: left part is 345, need to compare digit with 1
            int digit = str.charAt(i) - '0';
            if (digit > 1) {
                //    2.1: if digit > 1
                //               345 [6]  78
                //                        00
                //                        ...
                //                        99
                //                1  *  combo right
                //                1  *  100
                count += rightMax;
            } else if (digit == 1)
                //    2.2: if digit = 1
                //               345 [1]  78
                //                        00
                //                        ...
                //                        78
                //                1  *  combo right
                //                1  *  (num right + 1）
                // 2.2:
                count += n % rightMax + 1;
                //     2.3: if digit = 0, cannot choose 1 when left part is 345
                //               345 [0]  78
                //               0
        }
        return count;
    }
}

package com.company;

public class ZigZagConversion {
    public static void main(String[] args) {
        ZigZagConversion s = new ZigZagConversion();
        System.out.println(s.convert("PAYPALISHIRING", 1)); // PAHNAPLSIIGYIR

        System.out.println(s.convert("PAYPALISHIRING", 3)); // PAHNAPLSIIGYIR
        /*
                  P       A       H       N
                  A   P   L   S   I   I   G
                  Y       I       R
        **/

        System.out.println(s.convert("PAYPALISHIRINGNOW", 5)); // PHWASIOYIRNPLIGAN
        /*
                  P            H                        W
                  A         S     I                  O
                  Y      I           R            N
                  P   L                 I      G
                  A                         N
        **/


    }

    // (2*nRows - 2) in a group, need to translate index from original input to zigzag output
    // index = i % (2*nRows - 2)
    // Case 1: index < nRow, use index to map
    // Case 2: otherwise
    //         index = (nRows - 1) - (index - (nRows - 1)) = (2*nRows - 2) - index
    // Time O(n)
    //    // Space O(n)
    public String convert(String input, int nRows) {
        if (input == null || input.length() <= 1 || nRows == 1) {
            return input;
        }
        StringBuilder[] sbs = new StringBuilder[nRows];
        for (int i = 0; i < sbs.length; i++) {
            sbs[i] = new StringBuilder();
        }
        for (int i = 0; i < input.length(); i++) {
            int index = i % (2 * nRows - 2);
            if (index < nRows) {
                sbs[index].append(input.charAt(i));
            } else {
                index = (nRows - 1) - (index - (nRows - 1));
                sbs[index].append(input.charAt(i));
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sbs.length; i++) {
            sb.append(sbs[i]);
        }
        return sb.toString();
    }
}

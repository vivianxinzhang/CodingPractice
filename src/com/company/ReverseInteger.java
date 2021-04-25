package com.company;

public class ReverseInteger {
    public static void main(String[] args) {
        ReverseInteger s = new ReverseInteger();
        System.out.println(s.reverse(-12)); // -21

        System.out.println(s.reverse(123)); // 321

        System.out.println(s.reverse(100)); // 1

        System.out.println(s.reverse(1000000003));  // overflow

    }

    // Assumptions:
    // 1. If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.
    // 2. Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer, then the reverse of 1000000003 overflows. How should you handle such cases?
    // 3. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
    // Time O(1)
    // Space O(1)
    public int reverse(int x) {
        String str = Integer.toString(x);
        char[] array = str.toCharArray();
        boolean isNegative = array[0] == '-';
        if (isNegative) {
            reverse(array, 1, array.length - 1);
        } else {
            reverse(array, 0, array.length - 1);
        }
        return toInt(array, isNegative);
    }

    private int toInt(char[] array, boolean isNegative) {
        long sum = 0;
        int i = isNegative ? 1 : 0;
        while (i < array.length) {
            sum = sum * 10 + (array[i] - '0');
            if (!isNegative && sum > Integer.MAX_VALUE) {
                return 0;
            }
            if (isNegative && -sum < Integer.MIN_VALUE) {
                return 0;
            }
            i++;
        }
        return isNegative ? -(int)sum : (int)sum;
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

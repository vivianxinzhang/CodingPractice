package com.company;

public class AdditiveNumber {
    public static void main(String[] args) {
        AdditiveNumber s = new AdditiveNumber();

        String str = "112";
        System.out.println(s.isAdditiveNumber(str));

        str = "112358";
        System.out.println(s.isAdditiveNumber(str));

        str = "12436160";
        System.out.println(s.isAdditiveNumber(str));

        str = "100100200";
        System.out.println(s.isAdditiveNumber(str));
    }

    // Time O(n^3)
    // Space O(n)
    public boolean isAdditiveNumber(String num) {
        // Write your solution here
        int[] array = toIntArray(num);
        for (int i = 1; i < num.length(); i++){
            for (int j = i + 1; j < num.length(); j++) {
                int num1 = getNumber(array, 0, i);
                int num2 = getNumber(array, i, j);
                if (dfs(array, i, j, num1, num2)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int[] array, int i, int j, int num1, int num2) {
        if (j == array.length) {
            return true;
        }
        int nextNumber = num1 + num2;
        int nextIdx = getDigits(nextNumber) + j;
        if (nextIdx <= array.length && getNumber(array, j, nextIdx) == nextNumber) {
            return dfs(array, j, nextIdx, num2, nextNumber);
        }
        return false;
    }

    private int getDigits(int nextNumber) {
        int count = 0;
        while (nextNumber > 0) {
            nextNumber /= 10;
            count++;
        }
        return count;
    }


    //digits in range [left, right) is the number
    private int getNumber(int[] array, int left, int right) {
        int result = 0;
        for (int i = left; i <= right - 1; i++) {
            result *= 10;
            result += array[i];
        }
        return result;
    }

    private int[] toIntArray(String num) {
        int[] result = new int[num.length()];
        for (int i = 0; i < num.length(); i++) {
            result[i] = num.charAt(i) - '0';
        }
        return result;
    }
}

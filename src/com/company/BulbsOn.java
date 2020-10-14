package com.company;
import java.util.Arrays;

public class BulbsOn {
    public static void main(String[] args) {
        BulbsOn s = new BulbsOn();
        System.out.println(s.bulbSwitch(3));
        System.out.println(s.bulbSwitch(38));
    }

    // Times O(n^2)
    // Space O(1)
    public int bulbSwitch(int n) {
        // Write your solution here
        int[] bulbs = new int[n];
        for (int i = 1; i <= n; i++) {
            switchEveryI(bulbs, i);
        }
        return count(bulbs);
    }

    private void switchEveryI(int[] bulbs, int distance) {
        int i = distance - 1;
        while (i < bulbs.length) {
            bulbs[i] = bulbs[i] == 1 ? 0 : 1;
            i += distance;
        }
    }


    private int count(int[] bulbs) {
        int result = 0;
        for (int i = 0; i <bulbs.length; i++) {
            result += bulbs[i];
        }
        return result;
    }
}

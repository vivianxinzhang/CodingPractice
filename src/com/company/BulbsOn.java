package com.company;
import java.util.Arrays;

public class BulbsOn {
    public static void main(String[] args) {
        BulbsOn s = new BulbsOn();
        System.out.println(s.bulbSwitch(3));    // 1
        System.out.println(s.bulbSwitch(38));   // 6
    }

    // Method 2:
    // Times O(n^2)
    // Space O(1)
    public int bulbSwitch(int n) {
        int[] M = new int[n + 1];
        int count = 0;
        for (int i = 1; i <= n; i++) {
            //
            for (int j = 1; j <= i; j++) {
                if (i % j == 0) {
                    M[i] = M[i] == 1 ? 0 : 1;
                }
            }
            if (M[i] == 1) {
                count++;
            }
        }
        return count;
    }

    // Method 1:
    // Times O(n^2)
    // Space O(1)
    public int bulbSwitchI(int n) {
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

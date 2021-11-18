package com.company;
import java.util.Arrays;

public class BulbsOn {
    public static void main(String[] args) {
        BulbsOn s = new BulbsOn();

        System.out.println(s.bulbSwitch(3));    // 1
        System.out.println(s.bulbSwitch(38));   // 6
        System.out.println(s.bulbSwitch(169));   // 13
    }

    // 对于每个position n，find number of factors of n，因子数就是被switch的次数，如果次数是奇数就是on，偶数就是off
    // 处于n位置的灯泡，如果有偶数个因子，则最后会灭，如果有奇数个，则最后会亮。
    // 对于n号灯泡，如果它有因子k，则会有对应的因子n/k，所以因子总是成对出现的，除了n是一个平方数的时候，会有平方根是一个单个的因子。所以只有完全平方数最后会亮。
    // 那么有多少个完全平方数呢？根号n向下取整个。
    // n =   28              16
    //    1     28        1      16
    //    2     14        2      8
    //    4     7         4       4
    // Method 2:
    // Times O(1)
    // Space O(1)
    public int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }

    // Method 2:
    // Times O(n^2)
    // Space O(1)
    public int bulbSwitchII(int n) {
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
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            int index = i;
            while (index < array.length) {
                array[index] = 1 - array[i];
                index += (i + 1);
            }
        }
        return count(array);
    }

    private int count(int[] bulbs) {
        int result = 0;
        for (int i = 0; i <bulbs.length; i++) {
            result += bulbs[i];
        }
        return result;
    }
}

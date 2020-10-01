package com.company;
import java.util.*;

public class Main{
    public static void main(String[] args) {
//        Solution s = new Solution();
        RecentCounter obj = new RecentCounter();
        int p1 = obj.ping(1);
        System.out.println(p1);

        int p2 = obj.ping(3001);
        System.out.println(p2);
    }
}

package com.company;
import java.util.*;

public class RecentCounter {
    public static void main(String[] args) {
        RecentCounter s = new RecentCounter();
        RecentCounter obj = new RecentCounter();
        int p1 = obj.ping(1);
        System.out.println(p1);

        int p2 = obj.ping(3001);
        System.out.println(p2);
    }

    int counter;
    List<Integer> requests;

    public RecentCounter() {
        counter = 0;
        requests = new ArrayList<>();
    }

    public int ping(int t) {
        requests.add(t);
        counter++;
        int result = 0;
        int index = counter - 1;
        while (index >= 0 && requests.get(index) >= t - 3000) {
            result++;
            index--;
        }
        return result;
    }
}

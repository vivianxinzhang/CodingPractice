package com.company;
import java.util.*;

public class RecentCounter {
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

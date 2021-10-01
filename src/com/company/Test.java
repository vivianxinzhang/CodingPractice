package com.company;
import java.util.*;

public class Test {
    public static void main(String[] args) {
        Queue<Integer> pq = new PriorityQueue<>(new ReverseComparator());
        pq.offer(1500 * 1000 * 1000);
        pq.offer(-1000 * 1000 * 1000);
        pq.offer(2000 * 1000 * 1000);
        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }
    }
}

class ReverseComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
        if (o1.equals(o2)) {
            return 0;
        }
        return o1 < o2 ? 1 : -1;
    }
}

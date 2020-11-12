package com.company.Practice;
import java.util.*;

public class Test {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        for (ListIterator<Integer> it = list.listIterator(); it.hasNext(); ) {
            System.out.println("One step forward: " + it.next());
            if (it.hasNext()) {
                System.out.println("Another step forward: " + it.next());
                System.out.println("Another step backward: " + it.previous());
            }
        }
    }
}

package Generics;
import java.util.*;

public class WildCard {
    public static void main (String args[]) {

    }

    // Generic method
    public static <T extends Comparable<? super T>> void sort(List<T> list) {
        // need to use compareTo()
    }

    static class Fruit implements Comparable<Fruit> {
        @Override
        public int compareTo(Fruit o) {
            return 0;
        }

        static class Apple extends Fruit {

        }
    }
}

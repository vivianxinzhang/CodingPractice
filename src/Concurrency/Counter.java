package Concurrency;

public class Counter {
    private static int value = 0;
    public static void increment() {
        synchronized (Counter.class) {
            value++;
        }
    }

    public static void decrease() {
        synchronized (Counter.class) {
            value--;
        }
    }

    public static int getCount() {
        synchronized (Counter.class) {
            return value;
        }
    }
}

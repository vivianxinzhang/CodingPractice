package Concurrency;

public class VolatileTest {
    public static volatile boolean flag = false;
    public static class MyRunnable implements Runnable {
        @Override
        public void run() {
            int i = 0;
            while (!flag) {
                System.out.println("The thread is running ...");
                // Imagine what will happen if I comment this line out!
                i++;
            }
            System.out.println("The thread is finished ...");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread newThread = new Thread(new MyRunnable());
        newThread.start();
        Thread.sleep(1);
        flag = true;
        System.out.println("Main thread is finished ...");
    }
}

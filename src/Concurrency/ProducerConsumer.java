package Concurrency;
import java.util.*;

public class ProducerConsumer {
    public static void main(String[] args) {

        System.out.println();
    }

    class Producer implements Runnable {
        Q q;
        public Producer(Q q) {
            super();
            this.q = q;
        }

        @Override
        public void run() {
            q.put(0);
        }
    }

    class Consumer implements Runnable {
        Q q;
        public Consumer(Q q) {
            super();
            this.q = q;
        }

        @Override
        public void run() {
            System.out.println(q.take());
        }
    }

    class Q {   // blocking queue
        private Queue<Integer> q;
        private final int limit;
        public Q(int limit) {
            this.q = new ArrayDeque<>();
            this.limit = limit;
        }

        public synchronized void put(Integer ele) { // synchronized(this)
            // Use while because after I woke up, q may still be full.
            while (q.size() == limit) {
                try {
                    wait(); // this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // q.size != limit
            if (q.size() == 0) {
                notifyAll();
            }
            q.offer(ele);
        }

        public synchronized Integer take() {
            while (q.size() == 0) {
                try {
                    wait(); // wait() release the lock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (q.size() == limit) {
                notifyAll();
            }
            return q.poll();
        }
    }
}

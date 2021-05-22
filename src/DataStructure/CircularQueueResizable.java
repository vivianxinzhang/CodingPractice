package DataStructure;

import java.util.Arrays;

public class CircularQueueResizable {
    int head;
    int tail;
    int size;
    int[] array;
    private int initCap = 3;

    public CircularQueueResizable() {
        array = new int[initCap];
        head = 0;
        tail = 0;
        size = 0;
    }

    public static void main(String[] args) {
        CircularQueueResizable queue = new CircularQueueResizable();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);

        System.out.println(queue.size());       // 5

        System.out.println(queue.poll());       // 1
        System.out.println(queue.poll());       // 2
        System.out.println(queue.poll());       // 3
        System.out.println(queue.poll());       // 4
        System.out.println(queue.poll());       // 5
        System.out.println(queue.poll());       // null
    }

    public boolean offer(Integer ele) {
        if (size == array.length) {
            int[] newArray = Arrays.copyOf(array, 2 * array.length);
            array = newArray;
        }
        array[tail] = ele;
        tail = tail + 1 == array.length ? 0 : tail + 1;
        size++;
        return true;
    }

    public Integer peek() {
        if (size == 0) {
            return null;
        }
        return array[head];
    }

    public Integer poll() {
        if (size == 0) {
            return null;
        }
        int result = array[head];
        head = head + 1 == array.length ? 0 : head + 1;
        // head + 1 % array.length
        size--;
        return result;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }
}

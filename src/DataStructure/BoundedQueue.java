package DataStructure;

public class BoundedQueue {
    int head;
    int tail;
    int size;
    Integer[] array;

    public BoundedQueue(int cap) {
        array = new Integer[cap];
        head = tail = 0;
        size = 0;
    }

    public boolean offer(Integer ele) {
        // 1.
        if (size == array.length - 1) {
            return false;
        }
        // 2.
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
        Integer result = array[head];
        head = head + 1 == array.length ? 0: head + 1;
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

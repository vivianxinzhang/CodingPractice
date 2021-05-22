package DataStructure;

public class BoundedDeque {
    int head;
    int tail;
    int size;
    int[] array;

    public BoundedDeque(int cap) {
        // check cap
        array = new int[cap];
        head = tail = 0;
        size = 0;
    }

    public static void main(String[] args) {
        BoundedDeque deque = new BoundedDeque(10);
        deque.offerFirst(1);
        deque.offerLast(2);

        System.out.println(deque.size());       // 2

        System.out.println(deque.pollFirst());  // 1
        System.out.println(deque.pollLast());   // 2

        System.out.println(deque.pollFirst());  // null
        System.out.println(deque.pollLast());   // null
    }

    public void offerFirst(int val) {
        if (size == array.length - 1) {
            return;
        }
        array[head] = val;
        head = head - 1 == -1 ? array.length - 1 : head - 1;
        size++;
    }

    public void offerLast(int val) {
        if (size == array.length - 1) {
            return;
        }
        int index = tail + 1 == array.length ? 0 : tail + 1;
        array[index] = val;
        tail = index;
        size++;
    }

    public Integer peekFirst() {
        return head == tail ? null : array[head + 1];
    }

    public Integer peekLast() {
        return head == tail ? null : array[tail - 1];
    }

    public Integer pollFirst() {
        if (head == tail) {
            return null;
        } else {
            int index = head + 1 == array.length ? 0 : head + 1;
            int val = array[index];
            head = index;
            size--;
            return val;
        }
    }

    public Integer pollLast() {
        if (head == tail) {
            return null;
        } else {
            int val = array[tail];
            tail = tail - 1 == -1 ? array.length - 1 : tail - 1;
            size--;
            return val;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}

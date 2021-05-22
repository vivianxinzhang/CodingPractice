package DataStructure;

public class BoundedStack {
    int[] array;
    int head;

    public BoundedStack(int cap) {
        // check cap
        array = new int[cap];
        head = -1;
    }

    public boolean push(int ele) {
        if (head == array.length - 1) {
            return false;
        }
        array[++head] = ele;
        return true;
    }

    public Integer pop() {
        return head == -1 ? null : array[head--];
    }

    public Integer top() {
        return head == -1 ? null : array[head];
    }
}

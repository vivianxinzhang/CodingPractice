package DataStructure;

import java.util.Arrays;

public class CircularStackResizable {
    int[] array;
    int head;
    private int initCap = 5;

    public CircularStackResizable() {
        // check cap
        array = new int[initCap];
        head = -1;
    }

    public static void main(String[] args) {
        CircularStackResizable stack = new CircularStackResizable();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);

        System.out.println(stack.top());       // 6
        System.out.println(stack.pop());       // 6
        System.out.println(stack.pop());       // 5
        System.out.println(stack.pop());       // 4
        System.out.println(stack.pop());       // 3
        System.out.println(stack.pop());       // 2
        System.out.println(stack.pop());       // 1
        System.out.println(stack.pop());       // null
    }

    public boolean push(int ele) {
        if (head == array.length - 1) {
            int[] newArray = Arrays.copyOf(array, 2 * array.length);
            array = newArray;
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

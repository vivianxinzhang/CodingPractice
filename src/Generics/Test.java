package Generics;
import java.util.*;

public class Test {
    // generic method printArray
    public static void printArray(Comparable[] inputArray) {
        // Display array elements
        for (Comparable element : inputArray) {
            System.out.printf("$s ", element);
        }
        System.out.println();
    }
    public static void main (String args[]) {
        Node<Integer> node = new Node<>(1);
        System.out.println(node.toString());
        node.setValue(2);
        System.out.println(node.toString());
    }
}

class Node<T> {
    private T value;
    public Node(T v) {
        value = v;
    }

    public String toString() {
        return value.toString();
    }

    public T getValue() {
        T result = value;
        return result;
    }

    public void setValue(T v) {
        value = v;
    }
}
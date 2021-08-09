package com.company;
import java.util.*;

public class LargestAndSecondLargest {
    public static void main(String[] args) {
        LargestAndSecondLargest s = new LargestAndSecondLargest();
        int[] array = new int[] {2, 1, 5, 4, 3};
        System.out.println(Arrays.toString(s.largestAndSecond(array)));    // [5, 4]

        array = new int[] {5, 4, 2, 1, 3, 6};
        System.out.println(Arrays.toString(s.largestAndSecond(array)));    // [6, 5]

        array = new int[] {3, 5, 2, 2, 1, 6, 3, 4, 5};
        System.out.println(Arrays.toString(s.largestAndSecond(array)));    // [6, 5]
    }

    // Assumptions: array is not null, array.length >= 2
    // The Element class will be used to store the original value
    // in the array and all the values compared to it
    static class Element {
        int value;
        List<Integer> compredValues;

        Element(int value) {
            this.value = value;
            this.compredValues = new ArrayList<>();
        }
    }

    // Time O(n + logn)
    // Space O(logn)
    public int[] largestAndSecond(int[] array) {
        // Convert the original array to Element array
        Element[] helper = convert(array);
        // largerLength is the left partition's length containing the
        // larger values after each round of comparison
        // For each round, the comparison is still doing for each of
        // the indices pairs(i, largerLength - 1 - i)
        // So that the larger elements are always on the left side,
        // and the largerLength will be cut in half each round.
        // largerLength is obviously initiated by the array's length.
        int largerLength = array.length;
        // we will terminate when there is only one element left on
        // the larger partition, and it has to be the largest value.
        // the second largest value is the largest value in its compared values
        while (largerLength > 1) {
            compareAndSwap(helper, largerLength);
            // 5  =>  (5 + 1) / 2 = 3
            // 4  =>  (4 + 1) / 2 = 2
            largerLength = (largerLength + 1) / 2;
        }
        return new int[] {helper[0].value, largest(helper[0].compredValues)};
    }

    // compare each of the indices pairs (i, largerLength - 1 -i),
    // swap the larger values on the left side if necessary,
    // and put the smaller value into the larger value's compare values list
    private void compareAndSwap(Element[] helper, int largerLength) {
        // largerLength = 5, i [0, 1]
        // largerLength = 4, i [0, 1]
        // pair 前面的 和 后面的 做比较
        for (int i = 0; i < largerLength / 2; i++) {
            if (helper[i].value < helper[largerLength -1 -i].value) {
                swap(helper, i, largerLength - 1 - i);
            }
            helper[i].compredValues.add(helper[largerLength - 1 - i].value);
        }
    }

    private void swap(Element[] helper, int left, int right) {
        Element tmp = helper[left];
        helper[left] = helper[right];
        helper[right] = tmp;
    }

    // Time O(logn)
    private int largest(List<Integer> list) {
        // this is guaranteed to be O(1)
        int max = list.get(0);
        // using iterator is guaranteed to be O(1) traversing each of the
        // elements in the list
        for (int num : list) {
            max = Math.max(max, num);
        }
        return max;
    }

    private Element[] convert(int[] array) {
        Element[] helper = new Element[array.length];
        for (int i = 0; i < array.length; i++) {
            helper[i] = new Element(array[i]);
        }
        return helper;
    }
}

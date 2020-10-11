package com.company;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianTracker {
    // we care about the smallest number of the larger half and the largest number of the smaller half
    // so for the smaller half we use a max heap and for the larger half we use a min heap
    // we maintain the property:
    // size(smallerHalf) == size(largerHalf) when there are even number of values read,
    // or size(smallerHalf) = size(largerHalf) + 1, when there are odd number of values read
    PriorityQueue<Integer> smallerHalf;
    PriorityQueue<Integer> largerHalf;

    public MedianTracker() {
        // add new fields and complete the constructor if necessary.
        smallerHalf = new PriorityQueue<>(Collections.reverseOrder());
        largerHalf = new PriorityQueue<>();
    }

    // Time O(logn)
    public void read(int value) {
        // write your implementation here.
        if (smallerHalf.isEmpty() || value < smallerHalf.peek()) {
            smallerHalf.offer(value);
        } else {
            largerHalf.offer(value);
        }
        // After we insert the value only when size(smallerHalf) == size(largerHalf) + 2 or
        // size(smallerHalf) == size(largerHalf) - 1 will break the balance, and we need to do the adjustment accordingly
        if (smallerHalf.size() - 1 > largerHalf.size()) {
            largerHalf.offer(smallerHalf.poll());
        } else if (largerHalf.size() > smallerHalf.size()) {
            smallerHalf.offer(largerHalf.poll());
        }
    }

    // Time O(logn)
    public Double median() {
        int size = smallerHalf.size() + largerHalf.size();
        // By the property we maintained, it is easy to know that if the number of values read is odd,
        // the largest one in the smaller half is the median
        if (size == 0) {
            return null;
        } else if (size % 2 == 1) {
            return (double)smallerHalf.peek();
        } else {
            return (smallerHalf.peek() + largerHalf.peek()) / 2.0;
        }
    }
}

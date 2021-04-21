package com.company;
import java.util.*;

public class CommonElementsInKSortedLists {
    public static void main(String[] args) {
        CommonElementsInKSortedLists s = new CommonElementsInKSortedLists();
        List<Integer> zero = Arrays.asList(0, 3, 7);
        List<Integer> one = Arrays.asList(1, 3, 5, 7);
        List<Integer> two = Arrays.asList(1, 5, 7, 9);
        List<List<Integer>> input = new ArrayList<>();
        input.add(zero);
        input.add(one);
        input.add(two);
        System.out.println(s.commonElementsInKSortedArrays(input)); // 7
        System.out.println(s.commonElementsInKSortedListsI(input));
    }

    // Assumptions:
    // 1. The input and its elements are not null, and support fast random access.
    // 2. Each List<Integer> sorted in ascending order.
    // 3. There could be duplicate elements in each of the lists.
    // Method 1: iterative reduction
    // Time O(kn)
    // Space O(n)
    public List<Integer> commonElementsInKSortedListsI(List<List<Integer>> input) {
        List<Integer> result = input.get(0);
        for (int i = 1; i < input.size(); i++) {
            result = helper(result, input.get(i));
        }
        return result;
    }

    // Assumption: a and b are not null, sorted in ascending order.
    private List<Integer> helper(List<Integer> a, List<Integer> b) {
        List<Integer> result = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < a.size() && j < b.size()) {
            int compare = a.get(i).compareTo(b.get(j));
            if (compare == 0) {
                result.add(a.get(i));
                i++;
                j++;
            } else if (compare < 0) {
                i++;
            } else {
                j++;
            }
        }
        return result;
    }

    // Method 2: binary reduction
    // Time O(kn)  = kn + kn/2 + kn/4 + .... 2n
    // Space O(kn)
    public List<Integer> commonElementsInKSortedArraysII(List<List<Integer>> input) {
        if (input == null || input.size() == 0) {
            return new ArrayList<>();
        }
        while (input.size() > 1) {
            List<List<Integer>> tmpRes = new ArrayList<>();
            int i = 0;
            int j = input.size() - 1;
            while (i < j) {
                tmpRes.add(commonInTwo(input.get(i), input.get(j)));
                i++;
                j--;
            }
            if (i == j) {
                tmpRes.add(input.get(i));
            }
//            for (int i = 0, j = input.size() - 1; i < j; i++, j--) {
//            tmpRes.add(commonInTwo(input.get(i), input.get(j)));
//            }
            input = tmpRes;
        }
        return input.get(0);
    }

    private List<Integer> commonInTwo(List<Integer> one, List<Integer> two) {
        List<Integer> result = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < one.size() && j < two.size()) {
            if (one.get(i) == two.get(j)) {
                result.add(one.get(i));
                i++;
                j++;
            } else if (one.get(i) < two.get(j)) {
                i++;
            } else {
                j++;
            }
        }
        return result;
    }

    // Method 3: play together (heap)
    // step 1:
    // get the initial min and max
    // step2:
    // case 1: if min == max, poll each element from number PQ, update the index, once done, offer the new index into PQ
    // case 2: if min < max, poll smallest number from number PQ, and update the index for that one, and update max with new one, and update min
    // Time O(klogk * n)
    // Space O(k)
    public List<Integer> commonElementsInKSortedArrays(List<List<Integer>> input) {
        List<Integer> res = new ArrayList<>();
        PriorityQueue<Element> minHeap = new PriorityQueue<>(new Comparator<Element>() {
            @Override
            public int compare(Element o1, Element o2) {
                if (o1.value == o2.value) {
                    return 0;
                }
                return o1.value < o2.value ? -1 : 1;
            }
        });
        int max = Integer.MIN_VALUE;
        for (int row = 0; row < input.size(); row++) {
            if (input.get(row).size() > 0) {
                int value = input.get(row).get(0);
                minHeap.offer(new Element(row, 0, value));
                max = Math.max(max, value);
            }
        }
        while (minHeap.size() >= input.size()) {
            if (minHeap.peek().value == max) {
                res.add(max);
                for (int i = 0; i < input.size(); i++) {
                    Element cur = minHeap.poll();
                    if (cur.col + 1 < input.get(cur.row).size()) {
                        int value = input.get(cur.row).get(cur.col + 1);
                        minHeap.offer(new Element(cur.row, cur.col + 1, value));
                        max = Math.max(max, value);
                    }
                }
            } else {
                Element cur = minHeap.poll();
                if (cur.col + 1 < input.get(cur.row).size()) {
                    int value = input.get(cur.row).get(cur.col + 1);
                    minHeap.offer(new Element(cur.row, cur.col + 1, value));
                    max = Math.max(max, value);
                }
            }
        }
        return res;
    }

    class Element {
        int row;
        int col;
        int value;

        public Element(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }
}

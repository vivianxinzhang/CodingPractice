package com.company;
import java.util.*;

// Time average O(nlogn) worst case O(n^2)
// Space average O(logn) worst case O(n)

// the left subtree should be traversed before the right subtree,
// since stack is LIFO, we should push right into the stack first,
// so for the next step the top element of the stack is the left subtree;
// Time O(n)
// Space O(h) worst O(n)

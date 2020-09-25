package com.company;
import java.util.*;

// Time O(n^2) <-- assume the input tree is balanced
// Space O(logn) <-- assume the input tree is balanced
// if input tree is balanced, it has log_2(n) levels, recursion quadral tree also has log_2(n) levels
// the recursion tree(4 branches) is different from the input tree(2 branches)
// total number of nodes in the quadral tree = 4^(log_2(n)) = 2^(2log_2(n)) = 2^(log_2(n^2)) = O(n^2)
// if the input tree is not balanced, then time complexity <<< O(n^2)
// e.g. two linkedlist O(n)
package com.company;
import java.util.*;

public class PrimesLessThanOrEqualToTarget {
    public static void main(String[] args) {
        PrimesLessThanOrEqualToTarget s = new PrimesLessThanOrEqualToTarget();
        System.out.println(s.primes(10));
    }

    // best solution
    // https://www.geeksforgeeks.org/sieve-of-eratosthenes/

    // Time O(N3/2)  <-- O(n^2)
    // Space O(1)
    public List<Integer> primes(int target) {
        List<Integer> result = new ArrayList<>();
        for (int i = 2; i <= target; i++) {
            if (isPrime(i)) {
                result.add(i);
            }
        }
        return result;
    }

    private boolean isPrime(int num) {
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}

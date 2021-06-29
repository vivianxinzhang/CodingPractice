package com.company;

public class CountPrimes {
    public static void main(String[] args) {
        CountPrimes s = new CountPrimes();

        System.out.println(s.countPrimes(2));         // 0
        System.out.println(s.countPrimes(3));         // 1
        System.out.println(s.countPrimes(10));        // 4
        System.out.println(s.countPrimes(30));        // 10
        System.out.println(s.countPrimes(31214));     // 3362
    }

    public int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }
        int count = 1;
        for (int i = 3; i < n; i++) {
            if (isPrime(i)) {
                count++;
            }
        }
        return count;
    }
    // check numbers greater than 3
    public boolean isPrime(int n) {
        //check if n is a multiple of 2
        if (n%2 == 0) return false;
        //if not, then just check the odds
        for(int i = 3; i * i <= n; i += 2) {
            if( n % i == 0)
                return false;
        }
        return true;
    }
}
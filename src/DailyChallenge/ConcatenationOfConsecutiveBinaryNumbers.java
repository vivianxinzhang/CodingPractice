package DailyChallenge;

public class ConcatenationOfConsecutiveBinaryNumbers {
    public static void main(String[] args) {
        ConcatenationOfConsecutiveBinaryNumbers s = new ConcatenationOfConsecutiveBinaryNumbers();
        System.out.println(s.concatenatedBinary(1));    // 1 -> 1 -> 1
        System.out.println(s.concatenatedBinary(2));    // 1 + 2 -> 1 + 10 -> 110 -> 6
        System.out.println(s.concatenatedBinary(3));    // 1 + 2 + 3 -> 1 + 10 + 11 -> 11011 -> 27
    }

    // Observation: appending binary string at the end of an existing string
    // will cause a left-shift effect by the size of the appended string
    // num      string          calculation     value
    // 1        1               -               1
    // 2        1 10            1 * 2^2 + 2     6
    // 3        1 10 11         6 * 2^2 + 3     27
    // 4        1 10 11 100     27 * 2^3 + 4    220
    // previous calculated value = p
    // current num = x
    // # of digis = log2(x) + 1 = D
    // new decimal value = p * 2^D + x  to avoid
    // Time O(n)
    // Space O(1)
    public int concatenatedBinary(int n) {
        long val = 0;
        int i = 1;
        int MOD = 1000000007;
        while (i <= n) {
            int D = Integer.toBinaryString(i).length();
            val <<= D;
            val %= MOD;
            val += i;
            i++;
        }
        return (int) val;
    }

    public int concatenatedBinaryII(int n) {
        int digits = 0, MOD = 1000000007;
        long result = 0;
        for(int i = 1; i <= n; i++){
			/* if "i" is a power of 2, then we have one additional digit in
			   its the binary equivalent compared to that of i-1 */
            if((i & (i - 1)) == 0) digits++;
            result = ((result << digits) + i) % MOD;
        }
        return (int) result;
    }

    public int concatenatedBinaryI(int n) {
        int mod = 1000000007 ;
        int newNo = 0;
        for(int num = 1; num <= n; num++) {
            String binaryRep= Integer.toBinaryString(num);
            for(char ch : binaryRep.toCharArray()){
                int val= (ch=='0') ? 0 : 1;
                newNo= (( newNo * 2 ) % mod + val) % mod;
            }
        }
        return newNo;
    }
}

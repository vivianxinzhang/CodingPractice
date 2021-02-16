package DailyChallenge;

public class SmallestStringWithAGivenNumericValue {

    public String getSmallestString(int n, int k) {
        char[] sol = new char[n];
        final char[] chr = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        int avail = k - n;
        for(int i=0; i < n; i++){
            int c = Math.min(25, avail);
            sol[n-i-1] = chr[c];
            avail -= c;
        }

        return new String(sol);
    }
}

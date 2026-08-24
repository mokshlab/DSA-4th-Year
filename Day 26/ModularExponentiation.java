import java.util.*;
import java.io.*;

public class ModularExponentiation {
    static class Solution {
        public long powMod(long x, long n, long M) {
            if (M == 1) return 0;

            long result = 1;
            x %= M;

            while (n > 0) {
                if ((n & 1) == 1) {
                    result = (result * x) % M;
                }
                x = (x * x) % M;
                n >>= 1;
            }

            return result;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        st.nextToken(); long x = (long) st.nval;
        st.nextToken(); long n = (long) st.nval;
        st.nextToken(); long M = (long) st.nval;
        Solution sol = new Solution();
        System.out.println(sol.powMod(x, n, M));
    }
}
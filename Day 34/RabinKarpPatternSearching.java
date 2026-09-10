import java.util.*;
import java.io.*;

public class RabinKarpPatternSearching {

    static class Solution {
        public boolean search(String S, String P) {
            int n = S.length();
            int m = P.length();

            if (m > n)
                return false;

            long base = 256;
            long mod = 1000000007L;
            long patternHash = 0;
            long textHash = 0;
            long power = 1;

            for (int i = 0; i < m; i++) {
                patternHash = (patternHash * base + P.charAt(i)) % mod;
                textHash = (textHash * base + S.charAt(i)) % mod;

                if (i < m - 1)
                    power = (power * base) % mod;
            }

            for (int i = 0; i <= n - m; i++) {
                if (patternHash == textHash) {
                    boolean match = true;

                    for (int j = 0; j < m; j++) {
                        if (S.charAt(i + j) != P.charAt(j)) {
                            match = false;
                            break;
                        }
                    }

                    if (match)
                        return true;
                }

                if (i < n - m) {
                    textHash = (textHash - S.charAt(i) * power % mod + mod) % mod;
                    textHash = (textHash * base + S.charAt(i + m)) % mod;
                }
            }

            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine().trim();
        String P = br.readLine().trim();

        Solution sol = new Solution();
        boolean result = sol.search(S, P);

        System.out.println(result ? "Yes" : "No");
    }
}

import java.util.*;
import java.io.*;

public class ArrangeBalls {
    static class Solution {
        static final int MOD = 1000000007;

        public int CountWays(int p, int q, int r) {
            int[][][] dpP = new int[p + 1][q + 1][r + 1];
            int[][][] dpQ = new int[p + 1][q + 1][r + 1];
            int[][][] dpR = new int[p + 1][q + 1][r + 1];

            if (p >= 1)
                dpP[1][0][0] = 1;
            if (q >= 1)
                dpQ[0][1][0] = 1;
            if (r >= 1)
                dpR[0][0][1] = 1;

            for (int i = 0; i <= p; i++) {
                for (int j = 0; j <= q; j++) {
                    for (int k = 0; k <= r; k++) {
                        if (i < p) {
                            dpP[i + 1][j][k] =
                                (dpP[i + 1][j][k] + dpQ[i][j][k]) % MOD;
                            dpP[i + 1][j][k] =
                                (dpP[i + 1][j][k] + dpR[i][j][k]) % MOD;
                        }

                        if (j < q) {
                            dpQ[i][j + 1][k] =
                                (dpQ[i][j + 1][k] + dpP[i][j][k]) % MOD;
                            dpQ[i][j + 1][k] =
                                (dpQ[i][j + 1][k] + dpR[i][j][k]) % MOD;
                        }

                        if (k < r) {
                            dpR[i][j][k + 1] =
                                (dpR[i][j][k + 1] + dpP[i][j][k]) % MOD;
                            dpR[i][j][k + 1] =
                                (dpR[i][j][k + 1] + dpQ[i][j][k]) % MOD;
                        }
                    }
                }
            }

            long ans = dpP[p][q][r];
            ans = (ans + dpQ[p][q][r]) % MOD;
            ans = (ans + dpR[p][q][r]) % MOD;

            return (int) ans;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);

        st.nextToken();
        int p = (int) st.nval;

        st.nextToken();
        int q = (int) st.nval;

        st.nextToken();
        int r = (int) st.nval;

        Solution sol = new Solution();
        System.out.println(sol.CountWays(p, q, r));
    }
}

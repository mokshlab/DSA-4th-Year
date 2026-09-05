import java.util.*;

public class ExpertNumber {
    static long solve(int n, int[] a) {
        int MAX = 1002;

        int[] last = new int[MAX];
        Arrays.fill(last, -1);

        long[] dp = new long[n + 1];

        for (int i = 0; i < n; i++) {
            last[a[i]] = i;

            long best = -1;

            if (last[0] < i) {
                best = dp[i];
            }

            int minLast = i;

            int limit = Math.min(MAX - 1, i + 1);

            for (int mex = 1; mex <= limit; mex++) {
                minLast = Math.min(minLast, last[mex - 1]);

                if (minLast < 0) {
                    break;
                }

                if (last[mex] < minLast) {
                    best = Math.max(best, dp[minLast] + mex);
                }
            }

            dp[i + 1] = best;
        }

        return dp[n];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        System.out.println(solve(n, a));
    }
}

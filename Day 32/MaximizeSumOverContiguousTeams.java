import java.util.*;

public class MaximizeSumOverContiguousTeams {
    static int solve(int[] a) {
        int n = a.length;
        int[] last = new int[1002];
        Arrays.fill(last, -1);

        int[] dp = new int[n + 1];

        for (int i = 0; i < n; i++) {
            if (a[i] <= 1001) {
                last[a[i]] = i;
            }

            dp[i + 1] = dp[i];

            int minLast = i;

            for (int mex = 1; mex <= 1001; mex++) {
                minLast = Math.min(minLast, last[mex - 1]);

                if (minLast < 0) {
                    break;
                }

                if (last[mex] < minLast) {
                    dp[i + 1] = Math.max(dp[i + 1], dp[minLast] + mex);
                }
            }
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

        System.out.println(solve(a));
    }
}

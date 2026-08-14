import java.util.*;

public class JobSequencingProblem {

    public static int[] minPla(int[] iD, int[] deadLine, int[] profit) {
        int n = iD.length;

        int[][] jobs = new int[n][2];
        int maxDeadline = 0;

        for (int i = 0; i < n; i++) {
            jobs[i][0] = deadLine[i];
            jobs[i][1] = profit[i];
            maxDeadline = Math.max(maxDeadline, deadLine[i]);
        }

        Arrays.sort(jobs, (a, b) -> Integer.compare(b[1], a[1]));

        maxDeadline = Math.min(maxDeadline, n);

        boolean[] slot = new boolean[maxDeadline + 1];

        int count = 0;
        int totalProfit = 0;

        for (int i = 0; i < n; i++) {
            int deadline = Math.min(jobs[i][0], maxDeadline);
            int jobProfit = jobs[i][1];

            for (int t = deadline; t >= 1; t--) {
                if (!slot[t]) {
                    slot[t] = true;
                    count++;
                    totalProfit += jobProfit;
                    break;
                }
            }
        }

        return new int[]{count, totalProfit};
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] id = new int[n];
        int[] dead = new int[n];
        int[] pro = new int[n];

        for (int i = 0; i < n; i++) {
            id[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            dead[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            pro[i] = sc.nextInt();
        }

        int[] ans = minPla(id, dead, pro);

        System.out.println(ans[0] + " " + ans[1]);
    }
}
import java.util.*;

public class GoodRectanglesGrid {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        int n = sc.nextInt(), m = sc.nextInt();
        int[][] grid = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) grid[i][j] = sc.nextInt();

        long ans = 0;
        for (int r1 = 0; r1 < n; r1++) {
            for (int r2 = r1 + 1; r2 < n; r2++) {
                int[] count = new int[16];
                for (int c = 0; c < m; c++) {
                    if (grid[r1][c] == grid[r2][c]) {
                        int val = grid[r1][c];
                        ans += count[val];
                        count[val]++;
                    }
                }
            }
        }
        System.out.println(ans);
    }
}
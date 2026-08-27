import java.util.*;

public class DistanceOfNearestCell {
    static class Solution {
        public int[][] nearest(int[][] grid) {
            int n = grid.length;
            int m = grid[0].length;

            int[][] dist = new int[n][m];
            Queue<int[]> q = new LinkedList<>();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == 1) {
                        dist[i][j] = 0;
                        q.offer(new int[]{i, j});
                    } else {
                        dist[i][j] = -1;
                    }
                }
            }

            int[] dr = {-1, 1, 0, 0};
            int[] dc = {0, 0, -1, 1};

            while (!q.isEmpty()) {
                int[] curr = q.poll();
                int r = curr[0];
                int c = curr[1];

                for (int k = 0; k < 4; k++) {
                    int nr = r + dr[k];
                    int nc = c + dc[k];

                    if (nr >= 0 && nr < n && nc >= 0 && nc < m && dist[nr][nc] == -1) {
                        dist[nr][nc] = dist[r][c] + 1;
                        q.offer(new int[]{nr, nc});
                    }
                }
            }

            return dist;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = sc.nextInt();
            }
        }
        Solution sol = new Solution();
        int[][] result = sol.nearest(grid);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(result[i][j]);
                if (j < m - 1) System.out.print(" ");
            }
            System.out.println();
        }
    }
}

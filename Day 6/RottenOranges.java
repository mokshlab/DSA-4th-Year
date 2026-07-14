import java.util.*;

public class RottenOranges {
    public static int count(int[][] grid){
        int m = grid.length, n = grid[0].length, fresh = 0, time = -1;
        Queue<int[]> queue = new LinkedList<>();
        int[][] d = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 2) queue.offer(new int[]{i, j});
                else if(grid[i][j] == 1) fresh++;
            }
        }
        if(fresh == 0) return 0;
        if(queue.isEmpty()) return -1;
        while(!queue.isEmpty()){
            time++;
            int size = queue.size();
            for(int i = 0; i < size; i++){
                int[] curr = queue.poll();
                for(int[] dir : d){
                    int x = curr[0] + dir[0], y = curr[1] + dir[1];
                    if(x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1){
                        grid[x][y] = 2;
                        fresh--;
                        queue.offer(new int[]{x, y});
                    }
                }
            }
            time++;
        }
        return fresh == 0 ? time : -1;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        int[][] grid = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                grid[i][j] = sc.nextInt();
            }
        }
        int ans = count(grid);
        System.out.println(ans);
    }
}
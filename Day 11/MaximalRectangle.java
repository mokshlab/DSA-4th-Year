import java.util.*;

public class MaximalRectangle {
    public static int maximalRectangle(int[][] matrix) {
        if(matrix.length == 0) return 0;
        int m = matrix[0].length;
        int[] h = new int[m];
        int ans = 0;
        for(int[] row : matrix) {
            for(int j = 0; j < m; j++) h[j] = row[j] == 0 ? 0 : h[j] + 1;
            Stack<Integer> st = new Stack<>();
            for(int i = 0; i <= m; i++) {
                int curr = (i == m) ? 0 : h[i];
                while(!st.isEmpty() && curr < h[st.peek()]) {
                    int height = h[st.pop()];
                    int width = st.isEmpty() ? i : i - st.peek() - 1;
                    ans = Math.max(ans, height * width);
                }
                st.push(i);
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] matrix = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                matrix[i][j] = sc.nextInt();
            }
        }
        int ans = maximalRectangle(matrix);
        System.out.println(ans);
        sc.close();
    }
}
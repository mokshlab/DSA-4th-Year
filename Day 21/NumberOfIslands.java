import java.io.*;
import java.util.*;

public class NumberOfIslands {
    static class Solution {
        public List<Integer> numOfIslands(int n, int m, int[][] A) {
            List<Integer> ans = new ArrayList<>();
            if (n <= 0 || m <= 0 || A == null || A.length == 0) {
                return ans;
            }

            int[] parent = new int[n * m];
            Arrays.fill(parent, -1);

            int count = 0;
            int[] dr = {-1, 1, 0, 0};
            int[] dc = {0, 0, -1, 1};

            for (int[] op : A) {
                int r = op[0];
                int c = op[1];
                int id = r * m + c;

                if (parent[id] != -1) {
                    ans.add(count);
                    continue;
                }

                parent[id] = id;
                count++;

                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    int nid = nr * m + nc;

                    if (nr >= 0 && nr < n && nc >= 0 && nc < m && parent[nid] != -1) {
                        int rootCurrent = find(parent, id);
                        int rootNeighbor = find(parent, nid);

                        if (rootCurrent != rootNeighbor) {
                            parent[rootCurrent] = rootNeighbor;
                            count--; 
                        }
                    }
                }
                ans.add(count);
            }

            return ans;
        }

        private int find(int[] parent, int i) {
            if (parent[i] == i) {
                return i;
            }
            return parent[i] = find(parent, parent[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        st.nextToken(); int n = (int) st.nval;
        st.nextToken(); int m = (int) st.nval;
        st.nextToken(); int k = (int) st.nval;
        int[][] A = new int[k][2];
        for (int i = 0; i < k; i++) {
            st.nextToken(); A[i][0] = (int) st.nval;
            st.nextToken(); A[i][1] = (int) st.nval;
        }
        
        Solution sol = new Solution();
        List<Integer> res = sol.numOfIslands(n, m, A);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.size(); i++) {
            if (i > 0) sb.append(' ');
            sb.append(res.get(i));
        }
        System.out.println(sb.toString());
    }
}
import java.util.*;

public class BiconnectedGraph {

    static class Solution {
        private int timer;
        private int[] tin;
        private int[] low;
        private boolean[] visited;
        private boolean hasArticulation;

        public int biConnect(int[][] adj, int n, int e) {
            List<List<Integer>> graph = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }

            for (int[] edge : adj) {
                int u = edge[0];
                int v = edge[1];

                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            timer = 0;
            tin = new int[n];
            low = new int[n];
            visited = new boolean[n];
            hasArticulation = false;

            Arrays.fill(tin, -1);

            dfs(0, -1, graph);

            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    return 0;
                }
            }

            return hasArticulation ? 0 : 1;
        }

        private void dfs(int u, int parent, List<List<Integer>> graph) {
            visited[u] = true;
            tin[u] = low[u] = timer++;

            int children = 0;

            for (int v : graph.get(u)) {
                if (v == parent) {
                    continue;
                }

                if (visited[v]) {
                    low[u] = Math.min(low[u], tin[v]);
                } else {
                    dfs(v, u, graph);

                    low[u] = Math.min(low[u], low[v]);

                    if (parent != -1 && low[v] >= tin[u]) {
                        hasArticulation = true;
                    }

                    children++;
                }
            }

            if (parent == -1 && children > 1) {
                hasArticulation = true;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int e = sc.nextInt();

        int[][] arr = new int[e][2];

        for (int i = 0; i < e; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        }

        Solution sol = new Solution();
        int result = sol.biConnect(arr, n, e);

        System.out.println(result);
    }
}

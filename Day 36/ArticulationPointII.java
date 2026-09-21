import java.util.*;

public class ArticulationPointII {
    static class Solution {
        private int timer;
        private int[] tin, low;
        private boolean[] visited, articulation;

        public List<Integer> articulationPoints(int V, int[][] edges) {
            List<List<Integer>> graph = new ArrayList<>();

            for (int i = 0; i < V; i++) {
                graph.add(new ArrayList<>());
            }

            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];

                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            timer = 0;
            tin = new int[V];
            low = new int[V];
            visited = new boolean[V];
            articulation = new boolean[V];

            Arrays.fill(tin, -1);

            for (int i = 0; i < V; i++) {
                if (!visited[i]) {
                    dfs(i, -1, graph);
                }
            }

            List<Integer> result = new ArrayList<>();

            for (int i = 0; i < V; i++) {
                if (articulation[i]) {
                    result.add(i);
                }
            }

            if (result.isEmpty()) {
                result.add(-1);
            }

            return result;
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
                        articulation[u] = true;
                    }

                    children++;
                }
            }

            if (parent == -1 && children > 1) {
                articulation[u] = true;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int V = sc.nextInt();
        int E = sc.nextInt();

        int[][] edges = new int[E][2];

        for (int i = 0; i < E; i++) {
            edges[i][0] = sc.nextInt();
            edges[i][1] = sc.nextInt();
        }

        Solution sol = new Solution();
        List<Integer> result = sol.articulationPoints(V, edges);

        Collections.sort(result);

        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i));

            if (i < result.size() - 1) {
                System.out.print(" ");
            }
        }

        System.out.println();
    }
}

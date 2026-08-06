import java.util.*;

public class TopologicalSort {

    static class Solution {
        static int[] topoSort(int V, int[][] edges) {
            List<List<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<>());
            }
            
            int[] indegree = new int[V];
            
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                adj.get(u).add(v);
                indegree[v]++;
            }
            
            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < V; i++) {
                if (indegree[i] == 0) {
                    q.add(i);
                }
            }
            
            int[] result = new int[V];
            int index = 0;
            
            while (!q.isEmpty()) {
                int curr = q.poll();
                result[index++] = curr;
                
                for (int neighbor : adj.get(curr)) {
                    indegree[neighbor]--;
                    if (indegree[neighbor] == 0) {
                        q.add(neighbor);
                    }
                }
            }
            
            return result;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        int V = sc.nextInt();
        int E = sc.nextInt();
        int[][] edges = new int[E][2];
        for (int i = 0; i < E; i++) {
            edges[i][0] = sc.nextInt();
            edges[i][1] = sc.nextInt();
        }

        int[] result = Solution.topoSort(V, edges);

        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]);
            if (i < result.length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}
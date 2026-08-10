import java.util.*;
import java.io.*;

public class RootToLeafPaths {

    static class TreeNode {
        int data;
        TreeNode left, right;
        TreeNode(int data) { this.data = data; }
    }

    static class Solution {
        public List<List<Integer>> Paths(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> path = new ArrayList<>();
            dfs(root, path, result);
            return result;
        }

        private void dfs(TreeNode node, List<Integer> path, List<List<Integer>> result) {
            if (node == null) return;

            path.add(node.data);

            if (node.left == null && node.right == null) {
                result.add(new ArrayList<>(path));
            } else {
                dfs(node.left, path, result);
                dfs(node.right, path, result);
            }
            path.remove(path.size() - 1);
        }
    }

    static TreeNode buildTree(String[] tokens) {
        if (tokens.length == 0) return null;
        if (tokens[0].equals("N")) return null;
        TreeNode root = new TreeNode(Integer.parseInt(tokens[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while (!queue.isEmpty() && i < tokens.length) {
            TreeNode node = queue.poll();
            if (i < tokens.length && !tokens[i].equals("N")) {
                node.left = new TreeNode(Integer.parseInt(tokens[i]));
                queue.add(node.left);
            }
            i++;
            if (i < tokens.length && !tokens[i].equals("N")) {
                node.right = new TreeNode(Integer.parseInt(tokens[i]));
                queue.add(node.right);
            }
            i++;
        }
        return root;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) line = "";
        line = line.trim().replaceAll("\"", "");
        String[] tokens = line.isEmpty() ? new String[0] : line.split("\\s+");
        TreeNode root = buildTree(tokens);

        Solution sol = new Solution();
        List<List<Integer>> result = sol.Paths(root);

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < result.size(); i++) {
            sb.append("[");
            List<Integer> path = result.get(i);
            for (int j = 0; j < path.size(); j++) {
                sb.append(path.get(j));
                if (j < path.size() - 1) sb.append(" ");
            }
            sb.append("]");
            if (i < result.size() - 1) sb.append(",");
        }
        sb.append("]");
        System.out.println(sb.toString());
    }
}
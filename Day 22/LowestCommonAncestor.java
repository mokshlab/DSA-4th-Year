import java.util.*;
import java.io.*;

public class LowestCommonAncestor {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    static class Solution {
    public int lca(TreeNode root, int n1, int n2) {
        TreeNode ans = findLCA(root, n1, n2);
        return (ans == null) ? -1 : ans.val;
    }

    private TreeNode findLCA(TreeNode root, int n1, int n2) {
        if (root == null) return null;

        if (root.val == n1 || root.val == n2)
            return root;

        TreeNode left = findLCA(root.left, n1, n2);
        TreeNode right = findLCA(root.right, n1, n2);

        if (left != null && right != null)
            return root;

        return (left != null) ? left : right;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine().trim();
        String[] tokens = line.split("\\s+");

        TreeNode root = null;
        if (tokens.length == 0 || tokens[0].equals("N")) {
            // empty tree
        } else {
            root = new TreeNode(Integer.parseInt(tokens[0]));
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            int i = 1;
            while (!queue.isEmpty() && i < tokens.length) {
                TreeNode curr = queue.poll();
                if (i < tokens.length && !tokens[i].equals("N")) {
                    curr.left = new TreeNode(Integer.parseInt(tokens[i]));
                    queue.add(curr.left);
                }
                i++;
                if (i < tokens.length && !tokens[i].equals("N")) {
                    curr.right = new TreeNode(Integer.parseInt(tokens[i]));
                    queue.add(curr.right);
                }
                i++;
            }
        }

        StreamTokenizer st = new StreamTokenizer(br);
        st.nextToken(); int n1 = (int) st.nval;
        st.nextToken(); int n2 = (int) st.nval;

        Solution sol = new Solution();
        int result = sol.lca(root, n1, n2);
        System.out.println(result);
    }
}
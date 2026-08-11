import java.util.*;
import java.io.*;

public class PseudoPalindromicPathsinBinaryTree {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    static class Solution {
        public int pseudoPalindromicPaths(TreeNode root) {
            if (root == null) {
                return 0;
            }

            int count = 0;

            Stack<TreeNode> nodeStack = new Stack<>();
            Stack<Integer> maskStack = new Stack<>();

            int initialMask = 1 << (root.val - 1);

            nodeStack.push(root);
            maskStack.push(initialMask);

            while (!nodeStack.isEmpty()) {
                TreeNode node = nodeStack.pop();
                int mask = maskStack.pop();

                if (node.left == null && node.right == null) {
                    if ((mask & (mask - 1)) == 0) {
                        count++;
                    }
                    continue;
                }

                if (node.left != null) {
                    int newMask = mask ^ (1 << (node.left.val - 1));
                    nodeStack.push(node.left);
                    maskStack.push(newMask);
                }

                if (node.right != null) {
                    int newMask = mask ^ (1 << (node.right.val - 1));
                    nodeStack.push(node.right);
                    maskStack.push(newMask);
                }
            }

            return count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        StreamTokenizer st = new StreamTokenizer(br);

        st.nextToken();
        int n = (int) st.nval;

        if (n == 0) {
            System.out.println(0);
            return;
        }

        int[] vals = new int[n];

        for (int i = 0; i < n; i++) {
            st.nextToken();
            vals[i] = (int) st.nval;
        }

        TreeNode[] nodes = new TreeNode[n];

        for (int i = 0; i < n; i++) {
            if (vals[i] != -1) {
                nodes[i] = new TreeNode(vals[i]);
            }
        }

        int idx = 1;

        for (int i = 0; i < n && idx < n; i++) {
            if (nodes[i] != null) {
                if (idx < n) {
                    nodes[i].left = nodes[idx++];
                }

                if (idx < n) {
                    nodes[i].right = nodes[idx++];
                }
            }
        }

        Solution sol = new Solution();
        int result = sol.pseudoPalindromicPaths(nodes[0]);

        System.out.println(result);
    }
}
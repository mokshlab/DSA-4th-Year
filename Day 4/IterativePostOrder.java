import java.util.*;
class TreeNode {
int val;
TreeNode left;
TreeNode right;
TreeNode(int val) {
    this.val = val;
    this.left = null;
    this.right = null;
}
}
public class IterativePostOrder {
static class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            result.add(current.val);
            if (current.left != null) {
                stack.push(current.left);
            }
            if (current.right != null) {
                stack.push(current.right);
            }
        }
        Collections.reverse(result);
        return result;
    }
}
public static void main(String[] args) {
    TreeNode root = new TreeNode(1);
    root.right = new TreeNode(2);
    root.right.left = new TreeNode(3);
    Solution sol = new Solution();
    List<Integer> res = sol.postorderTraversal(root);
    System.out.println(res);
}
}
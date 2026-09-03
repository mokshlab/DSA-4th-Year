import java.util.*;

public class PairsViolatingBSTProperty {

    static class TreeNode {
        int data;
        TreeNode left, right;
        TreeNode(int d) { data = d; left = null; right = null; }
    }

    static class Solution {
        public long pairsViolatingBST(TreeNode root, int n) {

            ArrayList<Integer> inorder = new ArrayList<>();

            Stack<TreeNode> stack = new Stack<>();
            TreeNode curr = root;

            while (curr != null || !stack.isEmpty()) {
                while (curr != null) {
                    stack.push(curr);
                    curr = curr.left;
                }

                curr = stack.pop();
                inorder.add(curr.data);
                curr = curr.right;
            }

            return mergeSortAndCount(inorder, 0, inorder.size() - 1);
        }

        static long mergeSortAndCount(ArrayList<Integer> arr, int left, int right) {
            if (left >= right) return 0;

            int mid = left + (right - left) / 2;

            long count = 0;
            count += mergeSortAndCount(arr, left, mid);
            count += mergeSortAndCount(arr, mid + 1, right);
            count += merge(arr, left, mid, right);

            return count;
        }

        static long merge(ArrayList<Integer> arr, int left, int mid, int right) {
            ArrayList<Integer> temp = new ArrayList<>();

            int i = left;
            int j = mid + 1;
            long count = 0;

            while (i <= mid && j <= right) {
                if (arr.get(i) <= arr.get(j)) {
                    temp.add(arr.get(i));
                    i++;
                } else {
                    temp.add(arr.get(j));
                    count += (mid - i + 1);
                    j++;
                }
            }

            while (i <= mid) {
                temp.add(arr.get(i));
                i++;
            }

            while (j <= right) {
                temp.add(arr.get(j));
                j++;
            }

            for (int k = 0; k < temp.size(); k++) {
                arr.set(left + k, temp.get(k));
            }

            return count;
        }
    }

    static TreeNode buildTree(String[] tokens) {
        if (tokens == null || tokens.length == 0) return null;
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        String line = sc.nextLine().trim();
        String[] tokens = line.split("\\s+");
        TreeNode root = buildTree(tokens);
        Solution sol = new Solution();
        long result = sol.pairsViolatingBST(root, n);
        System.out.println(result);
    }
}

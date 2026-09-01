import java.util.*;

public class NextGreaterElement {
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> nextGreater = new HashMap<>();
        Stack<Integer> stack = new Stack<>();

        for (int num : nums2) {
            while (!stack.isEmpty() && stack.peek() < num) {
                nextGreater.put(stack.pop(), num);
            }
            stack.push(num);
        }

        while (!stack.isEmpty()) {
            nextGreater.put(stack.pop(), -1);
        }

        int[] ans = new int[nums1.length];

        for (int i = 0; i < nums1.length; i++) {
            ans[i] = nextGreater.get(nums1[i]);
        }

        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] nums1 = new int[n];

        for (int i = 0; i < n; i++) {
            nums1[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int[] nums2 = new int[m];

        for (int i = 0; i < m; i++) {
            nums2[i] = sc.nextInt();
        }

        int[] ans = nextGreaterElement(nums1, nums2);

        for (int num : ans) {
            System.out.print(num + " ");
        }

        System.out.println();
        sc.close();
    }
}
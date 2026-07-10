import java.util.*;
public class ShortestSubaarayWithSumAtleastK {
public static int shortestSubarray(int[] nums, int K) {
    int n = nums.length;
    long[] prefix = new long[n + 1];
    for (int i = 0; i < n; i++) {
        prefix[i + 1] = prefix[i] + nums[i];
    }
    Deque<Integer> dq = new LinkedList<>();
    int ans = n + 1;
    for (int i = 0; i < prefix.length; i++) {
        while (!dq.isEmpty() && prefix[i] - prefix[dq.peekFirst()] >= K) {
            ans = Math.min(ans, i - dq.pollFirst());
        }
        while (!dq.isEmpty() && prefix[i] <= prefix[dq.peekLast()]) {
            dq.pollLast();
        }
        dq.offerLast(i);
    }
    return ans == n + 1 ? -1 : ans;
}
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter size of array: ");
    int n = sc.nextInt();
    int[] nums = new int[n];
    System.out.println("Enter array elements:");
    for (int i = 0; i < n; i++) {
        nums[i] = sc.nextInt();
    }
    System.out.print("Enter value of K: ");
    int K = sc.nextInt();
    int result = shortestSubarray(nums, K);
    System.out.println("Shortest Subarray Length: " + result);
    sc.close();
}
}
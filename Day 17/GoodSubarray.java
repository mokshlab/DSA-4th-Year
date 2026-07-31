import java.util.*;

public class GoodSubarray {
    public static long getMaxGoodSubarraySum(int[] A, int N, int k) {
        long[] prefixSum = new long[N + 1];
        for (int i = 0; i < N; i++) {
            prefixSum[i + 1] = prefixSum[i] + A[i];
        }

        Map<Integer, Integer> freqMap = new HashMap<>();
        Deque<Integer> deque = new ArrayDeque<>();
        
        long maxSum = 0;
        int l = 0;

        for (int r = 0; r < N; r++) {
            freqMap.put(A[r], freqMap.getOrDefault(A[r], 0) + 1);

            while (freqMap.size() > k) {
                freqMap.put(A[l], freqMap.get(A[l]) - 1);
                if (freqMap.get(A[l]) == 0) {
                    freqMap.remove(A[l]);
                }
                l++;
            }

            while (!deque.isEmpty() && deque.peekFirst() < l) {
                deque.pollFirst();
            }

            while (!deque.isEmpty() && prefixSum[deque.peekLast()] >= prefixSum[r]) {
                deque.pollLast();
            }
            deque.addLast(r);

            if (!deque.isEmpty()) {
                int bestL = deque.peekFirst();
                long currentSum = prefixSum[r + 1] - prefixSum[bestL];
                maxSum = Math.max(maxSum, currentSum);
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        int N = sc.nextInt();
        int k = sc.nextInt();
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }
        System.out.println(getMaxGoodSubarraySum(A, N, k));
    }
}
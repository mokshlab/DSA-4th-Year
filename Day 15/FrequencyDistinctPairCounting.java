import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.Map;

public class FrequencyDistinctPairCounting {
    static final int MOD = 1000000007;

    // Fenwick Tree (Binary Indexed Tree)
    static class FenwickTree {
        int size;
        int[] tree;

        public FenwickTree(int size) {
            this.size = size;
            this.tree = new int[size + 1];
        }

        public void add(int i, int delta) {
            for (; i <= size; i += i & -i) {
                tree[i] += delta;
            }
        }

        public int query(int i) {
            int sum = 0;
            for (; i > 0; i -= i & -i) {
                sum += tree[i];
            }
            return sum;
        }

        // Query total elements in range [left, right]
        public int queryRange(int left, int right) {
            if (left > right) return 0;
            return query(right) - query(left - 1);
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();
        String firstToken = scanner.nextToken();
        if (firstToken == null) return;

        int N = Integer.parseInt(firstToken);
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(scanner.nextToken());
        }

        int[] L = new int[N];
        int[] R = new int[N];

        // 1. Compute L[i] for prefix 0..i
        Map<Integer, Integer> freqMap = new HashMap<>();
        int distinctCount = 0;

        for (int i = 0; i < N; i++) {
            int val = A[i];
            int count = freqMap.getOrDefault(val, 0) + 1;
            freqMap.put(val, count);

            if (count == 1) {
                distinctCount++;
            }

            int fLeft = count;
            int dLeft = distinctCount;
            L[i] = fLeft - (dLeft / 2);
        }

        // 2. Compute R[j] for suffix j..N-1
        freqMap.clear();
        distinctCount = 0;

        for (int j = N - 1; j >= 0; j--) {
            int val = A[j];
            int count = freqMap.getOrDefault(val, 0) + 1;
            freqMap.put(val, count);

            if (count == 1) {
                distinctCount++;
            }

            int fRight = count;
            int dRight = distinctCount;
            R[j] = (dRight / 2) - fRight;
        }

        // 3. Count pairs using Fenwick Tree
        // Values of L[i] and R[j] range within [-N, N], so offset by N + 5
        int OFFSET = N + 5;
        int BIT_SIZE = 2 * N + 20;
        FenwickTree bit = new FenwickTree(BIT_SIZE);

        // Initially insert all R[j] for j from 1 to N - 1
        for (int j = 1; j < N; j++) {
            bit.add(R[j] + OFFSET, 1);
        }

        long pairCount = 0;

        for (int i = 0; i < N - 1; i++) {
            // Count R[j] such that R[j] >= L[i]
            int targetIdx = L[i] + OFFSET;
            int validJ = bit.queryRange(targetIdx, BIT_SIZE);
            pairCount = (pairCount + validJ) % MOD;

            // Remove R[i + 1] from the Fenwick tree as j must be > i + 1 for next iterations
            bit.add(R[i + 1] + OFFSET, -1);
        }

        System.out.println(pairCount);
    }

    // Fast I/O helper
    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String nextToken() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    String line = br.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    return null;
                }
            }
            return st.nextToken();
        }
    }
}
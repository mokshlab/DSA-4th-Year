import java.util.*;

public class ArrayTransformation {
    static class Fenwick {
        int[] tree;

        Fenwick(int n) {
            tree = new int[n + 1];
        }

        void add(int i) {
            while (i < tree.length) {
                tree[i]++;
                i += i & -i;
            }
        }

        int sum(int i) {
            int res = 0;
            while (i > 0) {
                res += tree[i];
                i -= i & -i;
            }
            return res;
        }
    }

    static long minSwaps(int[] A, int[] B) {
        int n = A.length;

        Map<Integer, Integer> pos = new HashMap<>();

        for (int i = 0; i < n; i++) {
            pos.put(B[i], i + 1);
        }

        int[] order = new int[n];

        for (int i = 0; i < n; i++) {
            if (!pos.containsKey(A[i])) {
                return -1;
            }
            order[i] = pos.get(A[i]);
        }

        Fenwick bit = new Fenwick(n);
        long swaps = 0;

        for (int i = 0; i < n; i++) {
            int already = bit.sum(order[i]);
            swaps += i - already;
            bit.add(order[i]);
        }

        return swaps;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        while (T-- > 0) {
            int n = sc.nextInt();

            int[] A = new int[n];
            int[] B = new int[n];

            for (int i = 0; i < n; i++) {
                A[i] = sc.nextInt();
            }

            for (int i = 0; i < n; i++) {
                B[i] = sc.nextInt();
            }

            System.out.println(minSwaps(A, B));
        }
    }
}

import java.util.*;

public class FrequencyDistinctPairCounting {
    static final long MOD = 1000000007L;

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
            int ans = 0;

            while (i > 0) {
                ans += tree[i];
                i -= i & -i;
            }

            return ans;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        int[] leftValue = new int[n];
        int[] rightValue = new int[n];

        HashMap<Integer, Integer> freq = new HashMap<>();

        int distinct = 0;

        for (int i = 0; i < n; i++) {
            int count = freq.getOrDefault(a[i], 0) + 1;
            freq.put(a[i], count);

            if (count == 1) {
                distinct++;
            }

            leftValue[i] = count - distinct / 2;
        }

        freq.clear();
        distinct = 0;

        for (int i = n - 1; i >= 0; i--) {
            int count = freq.getOrDefault(a[i], 0) + 1;
            freq.put(a[i], count);

            if (count == 1) {
                distinct++;
            }

            rightValue[i] = distinct / 2 - count;
        }

        Fenwick bit = new Fenwick(2 * n + 5);

        long answer = 0;

        for (int j = 0; j < n; j++) {
            if (j > 0) {
                int value = leftValue[j - 1] + n + 1;
                bit.add(value);
            }

            int value = rightValue[j] + n + 1;

            answer += bit.sum(value);
            answer %= MOD;
        }

        System.out.println(answer);
    }
}

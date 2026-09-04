import java.util.*;

public class ArrayQueries {
    static final long MOD = 1000000007L;

    static long[] tree;
    static long[] first;
    static long[] step;

    static void build(int node, int l, int r, long[] a) {
        first[node] = -1;

        if (l == r) {
            tree[node] = a[l] % MOD;
            return;
        }

        int mid = (l + r) / 2;

        build(node * 2, l, mid, a);
        build(node * 2 + 1, mid + 1, r, a);

        tree[node] = (tree[node * 2] + tree[node * 2 + 1]) % MOD;
    }

    static void apply(int node, int l, int r, long start, long d) {
        long len = r - l + 1;

        long sum = len * (2 * start + (len - 1) * d) / 2;
        tree[node] = sum % MOD;

        first[node] = start % MOD;
        step[node] = d % MOD;
    }

    static void push(int node, int l, int r) {
        if (first[node] == -1 || l == r) {
            return;
        }

        int mid = (l + r) / 2;
        long start = first[node];
        long d = step[node];

        apply(node * 2, l, mid, start, d);

        long rightStart = start + (mid - l + 1L) * d;

        apply(node * 2 + 1, mid + 1, r, rightStart, d);

        first[node] = -1;
    }

    static long get(int node, int l, int r, int pos) {
        if (l == r) {
            return tree[node];
        }

        push(node, l, r);

        int mid = (l + r) / 2;

        if (pos <= mid) {
            return get(node * 2, l, mid, pos);
        }

        return get(node * 2 + 1, mid + 1, r, pos);
    }

    static void update(int node, int l, int r, int ql, int qr, long base) {
        if (ql > r || qr < l) {
            return;
        }

        if (ql <= l && r <= qr) {
            long start = (l - ql + 1L) * base;
            apply(node, l, r, start, base);
            return;
        }

        push(node, l, r);

        int mid = (l + r) / 2;

        update(node * 2, l, mid, ql, qr, base);
        update(node * 2 + 1, mid + 1, r, ql, qr, base);

        tree[node] = (tree[node * 2] + tree[node * 2 + 1]) % MOD;
    }

    static long query(int node, int l, int r, int ql, int qr) {
        if (ql > r || qr < l) {
            return 0;
        }

        if (ql <= l && r <= qr) {
            return tree[node];
        }

        push(node, l, r);

        int mid = (l + r) / 2;

        return (query(node * 2, l, mid, ql, qr)
                + query(node * 2 + 1, mid + 1, r, ql, qr)) % MOD;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        long[] a = new long[n];

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextLong();
        }

        int q = sc.nextInt();

        tree = new long[4 * n];
        first = new long[4 * n];
        step = new long[4 * n];

        Arrays.fill(first, -1);

        build(1, 0, n - 1, a);

        long answer = 0;

        while (q-- > 0) {
            int type = sc.nextInt();
            int l = sc.nextInt();
            int r = sc.nextInt();

            if (type == 1) {
                long base = get(1, 0, n - 1, l);
                update(1, 0, n - 1, l, r, base);
            } else {
                answer += query(1, 0, n - 1, l, r);
                answer %= MOD;
            }
        }

        System.out.println(answer);
    }
}

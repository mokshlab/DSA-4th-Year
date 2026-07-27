import java.util.*;

public class AlternateORXORReduction {

    static int[] tree;

    static void build(int node, int start, int end, int[] arr, boolean isOr) {
        if (start == end) {
            tree[node] = arr[start];
            return;
        }

        int mid = start + (end - start) / 2;
        build(node * 2, start, mid, arr, !isOr);
        build(node * 2 + 1, mid + 1, end, arr, !isOr);

        tree[node] = isOr ? (tree[node * 2] | tree[node * 2 + 1]) : (tree[node * 2] ^ tree[node * 2 + 1]);
    }

    static void update(int node, int start, int end, int idx, int value, boolean isOr) {
        if (start == end) {
            tree[node] = value;
            return;
        }

        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(node * 2, start, mid, idx, value, !isOr);
        } else {
            update(node * 2 + 1, mid + 1, end, idx, value, !isOr);
        }

        tree[node] = isOr ? (tree[node * 2] | tree[node * 2 + 1]) : (tree[node * 2] ^ tree[node * 2 + 1]);
    }

    public List<Integer> left(int N, int[] A, int q, int[][] query) {
        List<Integer> ans = new ArrayList<>();

        tree = new int[4 * N];

        int height = 0;
        for (int temp = N; temp > 1; temp >>= 1) {
            height++;
        }

        boolean rootIsOr = (height % 2 == 1);
        build(1, 0, N - 1, A, rootIsOr);

        for (int k = 0; k < q; k++) {
            int idx = query[k][0];
            int val = query[k][1];

            if (idx < 0 || idx >= N) {
                ans.add(-1);
                continue;
            }

            update(1, 0, N - 1, idx, val, rootIsOr);
            ans.add(tree[1]);
        }

        return ans;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }

        int q = sc.nextInt();
        int[][] query = new int[q][2];
        for (int i = 0; i < q; i++) {
            query[i][0] = sc.nextInt();
            query[i][1] = sc.nextInt();
        }

        AlternateORXORReduction sol = new AlternateORXORReduction();
        List<Integer> result = sol.left(N, A, q, query);
        StringBuilder sb = new StringBuilder();
        for (int x : result) {
            sb.append(x).append('\n');
        }
        System.out.print(sb);
        sc.close();
    }
}
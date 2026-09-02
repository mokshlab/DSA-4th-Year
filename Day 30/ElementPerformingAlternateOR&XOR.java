import java.util.*;
import java.io.*;

public class ElementPerformingAlternateORXOR {
        int[] tree;

        public List<Integer> left(int N, int[] A, int q, int[][] query) {
            tree = new int[4 * N];
            build(1, 0, N - 1, A);

            List<Integer> ans = new ArrayList<>();

            for (int i = 0; i < q; i++) {
                int index = query[i][0];
                int value = query[i][1];

                if (index < 0 || index >= N) {
                    ans.add(-1);
                } else {
                    update(1, 0, N - 1, index, value);
                    ans.add(tree[1]);
                }
            }

            return ans;
        }

        void build(int node, int l, int r, int[] A) {
            if (l == r) {
                tree[node] = A[l];
                return;
            }

            int mid = (l + r) / 2;

            build(node * 2, l, mid, A);
            build(node * 2 + 1, mid + 1, r, A);

            int level = Integer.numberOfTrailingZeros(r - l + 1);

            if (level % 2 == 1)
                tree[node] = tree[node * 2] | tree[node * 2 + 1];
            else
                tree[node] = tree[node * 2] ^ tree[node * 2 + 1];
        }

        void update(int node, int l, int r, int index, int value) {
            if (l == r) {
                tree[node] = value;
                return;
            }

            int mid = (l + r) / 2;

            if (index <= mid)
                update(node * 2, l, mid, index, value);
            else
                update(node * 2 + 1, mid + 1, r, index, value);

            int level = Integer.numberOfTrailingZeros(r - l + 1);

            if (level % 2 == 1)
                tree[node] = tree[node * 2] | tree[node * 2 + 1];
            else
                tree[node] = tree[node * 2] ^ tree[node * 2 + 1];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);

        st.nextToken();
        int N = (int) st.nval;

        int[] A = new int[N];

        for (int i = 0; i < N; i++) {
            st.nextToken();
            A[i] = (int) st.nval;
        }

        st.nextToken();
        int q = (int) st.nval;

        int[][] query = new int[q][2];

        for (int i = 0; i < q; i++) {
            st.nextToken();
            query[i][0] = (int) st.nval;

            st.nextToken();
            query[i][1] = (int) st.nval;
        }

        Solution sol = new Solution();
        List<Integer> ans = sol.left(N, A, q, query);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < ans.size(); i++) {
            if (i > 0)
                sb.append(" ");
            sb.append(ans.get(i));
        }

        System.out.println(sb);
    }
}

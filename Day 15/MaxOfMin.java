import java.util.*;
import java.io.*;

public class MaxOfMin {

    static class Solution {

        public int[] solve(int N, int[] arr) {

            int[] left = new int[N];
            int[] right = new int[N];
            Stack<Integer> st = new Stack<>();

            // Previous Smaller Element
            for (int i = 0; i < N; i++) {
                while (!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                    st.pop();
                }

                left[i] = st.isEmpty() ? -1 : st.peek();
                st.push(i);
            }

            st.clear();

            // Next Smaller Element
            for (int i = N - 1; i >= 0; i--) {
                while (!st.isEmpty() && arr[st.peek()] > arr[i]) {
                    st.pop();
                }

                right[i] = st.isEmpty() ? N : st.peek();
                st.push(i);
            }

            int[] ans = new int[N + 1];

            for (int i = 0; i < N; i++) {
                int len = right[i] - left[i] - 1;
                ans[len] = Math.max(ans[len], arr[i]);
            }

            for (int i = N - 1; i >= 1; i--) {
                ans[i] = Math.max(ans[i], ans[i + 1]);
            }

            int[] res = new int[N];
            for (int i = 1; i <= N; i++) {
                res[i - 1] = ans[i];
            }

            return res;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);

        st.nextToken();
        int N = (int) st.nval;

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            st.nextToken();
            arr[i] = (int) st.nval;
        }

        Solution sol = new Solution();
        int[] result = sol.solve(N, arr);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            if (i > 0) sb.append(" ");
            sb.append(result[i]);
        }

        System.out.print(sb.toString());
    }
}
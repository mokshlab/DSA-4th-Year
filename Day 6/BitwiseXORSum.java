import java.io.*;
import java.util.*;

public class BitwiseXORSum {
    static class Solution {
        public List<Integer> specialXOR(int N, int Q, int[] a, int[][] query){
            int[] p = new int[N];
            p[0] = a[0];
            for(int i = 1; i < N; i++) p[i] = p[i - 1] ^ a[i];
            List<Integer> ans = new ArrayList<>();
            for(int[] q : query) ans.add(p[N - 1] ^ (p[q[1] - 1] ^ (q[0] > 1 ? p[q[0] - 2] : 0)));
            return ans;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        st.nextToken(); int N = (int) st.nval;
        st.nextToken(); int Q = (int) st.nval;
        int[] a = new int[N];
        for(int i = 0; i < N; i++){
            st.nextToken(); a[i] = (int) st.nval;
        }
        int[][] query = new int[Q][2];
        for(int i = 0; i < Q; i++){
            st.nextToken(); query[i][0] = (int) st.nval;
            st.nextToken(); query[i][1] = (int) st.nval;
        }
        Solution sol = new Solution();
        List<Integer> ans = sol.specialXOR(N, Q, a, query);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < ans.size(); i++){
            sb.append(ans.get(i)).append("\n");
        }
        System.out.print(sb);
    }
}
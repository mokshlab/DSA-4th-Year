import java.io.*;
import java.util.*;

public class RestrictiveCandyCrush {
    static class Solution {
        public String Reduced_String(int k, String s) {
            Stack<int[]> st = new Stack<>();
            for(char c : s.toCharArray()) {
                if(!st.isEmpty() && st.peek()[0] == c) {
                    st.peek()[1]++;
                    if(st.peek()[1] == k) st.pop();
                } else {
                    st.push(new int[]{c, 1});
                }
            }
            StringBuilder ans = new StringBuilder();
            for(int[] p : st) {
                for(int i = 0; i < p[1]; i++) {
                    ans.append((char) p[0]);
                }
            }
            return ans.toString();
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        st.nextToken();
        int k = (int) st.nval;
        st.nextToken();
        String s = st.sval;
        Solution sol = new Solution();
        String result = sol.Reduced_String(k, s);
        System.out.println(result);
    }
}
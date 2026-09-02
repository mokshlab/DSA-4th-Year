import java.util.*;
import java.io.*;

public class CamelCasePatternMatching {
    static class Solution {
        public List<String> camelCase(int n, String[] arr, String pat) {
            List<String> result = new ArrayList<>();

            for (String word : arr) {
                StringBuilder upper = new StringBuilder();

                for (char ch : word.toCharArray()) {
                    if (Character.isUpperCase(ch)) {
                        upper.append(ch);
                    }
                }

                if (upper.toString().startsWith(pat)) {
                    result.add(word);
                }
            }

            return result;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);

        st.nextToken();
        int n = (int) st.nval;

        String[] arr = new String[n];

        for (int i = 0; i < n; i++) {
            st.nextToken();
            arr[i] = st.sval;
        }

        st.nextToken();
        String pat = st.sval;

        Solution sol = new Solution();
        List<String> res = sol.camelCase(n, arr, pat);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < res.size(); i++) {
            if (i > 0) {
                sb.append(' ');
            }
            sb.append(res.get(i));
        }

        System.out.println(sb.toString());
    }
}

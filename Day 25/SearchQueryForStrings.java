import java.util.*;
import java.io.*;

public class SearchQueryForStrings {

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
    }

    static class Solution {
        TrieNode root = new TrieNode();

        void insert(String word) {
            TrieNode current = root;

            for (char ch : word.toCharArray()) {
                int index = ch - 'a';

                if (current.children[index] == null) {
                    current.children[index] = new TrieNode();
                }

                current = current.children[index];
            }

            current.isEnd = true;
        }

        boolean search(String word) {
            TrieNode current = root;

            for (char ch : word.toCharArray()) {
                int index = ch - 'a';

                if (current.children[index] == null) {
                    return false;
                }

                current = current.children[index];
            }

            return current.isEnd;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);

        st.nextToken();
        int n = (int) st.nval;

        st.nextToken();
        int q = (int) st.nval;

        Solution sol = new Solution();

        for (int i = 0; i < n; i++) {
            st.nextToken();
            sol.insert(st.sval);
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < q; i++) {
            st.nextToken();
            String query = st.sval;

            sb.append(sol.search(query) ? 1 : 0).append("\n");
        }

        System.out.print(sb);
    }
}

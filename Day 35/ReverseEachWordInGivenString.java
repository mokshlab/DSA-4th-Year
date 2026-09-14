import java.util.*;
import java.io.*;

public class ReverseEachWordInGivenString {

    static class Solution {
        public String reverseWords(String s) {
            String[] words = s.trim().split("\\s+");

            StringBuilder result = new StringBuilder();

            for (int i = 0; i < words.length; i++) {
                StringBuilder word = new StringBuilder(words[i]);
                result.append(word.reverse());

                if (i < words.length - 1) {
                    result.append(" ");
                }
            }

            return result.toString();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        if (s == null) s = "";

        Solution sol = new Solution();
        String result = sol.reverseWords(s);

        System.out.println(result);
    }
}

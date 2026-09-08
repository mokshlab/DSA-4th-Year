import java.util.*;

public class WordBreak {

    static class Solution {
        public List<String> wordBreak(String s, List<String> dict) {
            Set<String> wordSet = new HashSet<>(dict);

            // Memoization: index -> all valid sentences from this index
            Map<Integer, List<String>> memo = new HashMap<>();

            return solve(s, 0, wordSet, memo);
        }

        private List<String> solve(String s, int start,
                                   Set<String> wordSet,
                                   Map<Integer, List<String>> memo) {

            if (start == s.length()) {
                List<String> base = new ArrayList<>();
                base.add("");
                return base;
            }

            if (memo.containsKey(start)) {
                return memo.get(start);
            }

            List<String> result = new ArrayList<>();

            for (int end = start + 1; end <= s.length(); end++) {
                String word = s.substring(start, end);

                if (!wordSet.contains(word)) {
                    continue;
                }

                List<String> suffixSentences =
                        solve(s, end, wordSet, memo);

                for (String suffix : suffixSentences) {
                    if (suffix.isEmpty()) {
                        result.add(word);
                    } else {
                        result.add(word + " " + suffix);
                    }
                }
            }

            memo.put(start, result);
            return result;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String s = scanner.nextLine().trim();

        int n = Integer.parseInt(scanner.nextLine().trim());

        List<String> dict = new ArrayList<>();
        String[] words = scanner.nextLine().trim().split("\\s+");

        for (int i = 0; i < n; i++) {
            dict.add(words[i]);
        }

        Solution sol = new Solution();
        List<String> result = sol.wordBreak(s, dict);

        if (result == null || result.isEmpty()) {
            System.out.println(-1);
        } else {
            for (String sentence : result) {
                System.out.println(sentence);
            }
        }

        scanner.close();
    }
}

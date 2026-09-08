import java.util.*;
import java.io.*;

public class CombinationSumII {

    static class Solution {
        public List<List<Integer>> combinationSum2(int[] candidates, int target, int n) {
            List<List<Integer>> result = new ArrayList<>();

            Arrays.sort(candidates);

            backtrack(candidates, target, 0, new ArrayList<>(), result);

            return result;
        }

        private void backtrack(int[] candidates, int target, int start,
                               List<Integer> current,
                               List<List<Integer>> result) {

            if (target == 0) {
                result.add(new ArrayList<>(current));
                return;
            }

            for (int i = start; i < candidates.length; i++) {

                // Skip duplicates at the same recursion level
                if (i > start && candidates[i] == candidates[i - 1]) {
                    continue;
                }

                // Since array is sorted, no further element can work
                if (candidates[i] > target) {
                    break;
                }

                current.add(candidates[i]);

                // i + 1 ensures each element is used at most once
                backtrack(candidates, target - candidates[i], i + 1,
                          current, result);

                current.remove(current.size() - 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);

        st.nextToken();
        int n = (int) st.nval;

        int[] candidates = new int[n];
        for (int i = 0; i < n; i++) {
            st.nextToken();
            candidates[i] = (int) st.nval;
        }

        st.nextToken();
        int target = (int) st.nval;

        Solution sol = new Solution();
        List<List<Integer>> result = sol.combinationSum2(candidates, target, n);

        if (result == null || result.isEmpty()) {
            System.out.println("[]");
        } else {
            StringBuilder sb = new StringBuilder();

            for (List<Integer> combination : result) {
                for (int i = 0; i < combination.size(); i++) {
                    if (i > 0) {
                        sb.append(" ");
                    }
                    sb.append(combination.get(i));
                }
                sb.append("\n");
            }

            System.out.print(sb.toString());
        }
    }
}

import java.util.*;
import java.io.*;

public class MaxCircularSubarraySum {
    static class Solution {
        public int maxCircularSum(int[] arr, int n) {
            int maxEnding = arr[0];
            int maxSoFar = arr[0];

            int minEnding = arr[0];
            int minSoFar = arr[0];

            int totalSum = arr[0];

            for (int i = 1; i < n; i++) {
                maxEnding = Math.max(arr[i], maxEnding + arr[i]);
                maxSoFar = Math.max(maxSoFar, maxEnding);

                minEnding = Math.min(arr[i], minEnding + arr[i]);
                minSoFar = Math.min(minSoFar, minEnding);

                totalSum += arr[i];
            }

            if (maxSoFar < 0) {
                return maxSoFar;
            }

            int circularSum = totalSum - minSoFar;

            return Math.max(maxSoFar, circularSum);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);

        st.nextToken();
        int n = (int) st.nval;

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            st.nextToken();
            arr[i] = (int) st.nval;
        }

        Solution sol = new Solution();
        int result = sol.maxCircularSum(arr, n);

        System.out.println(result);
    }
}

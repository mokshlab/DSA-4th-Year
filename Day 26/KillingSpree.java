import java.util.*;
import java.io.*;

public class KillingSpree {
    static class Solution {
        public long killinSpree(long N) {
            long count = 0;
            long strength = 1;

            while (N >= strength * strength) {
                N -= strength * strength;
                count++;
                strength++;
            }

            return count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        st.nextToken();
        long N = (long) st.nval;
        Solution sol = new Solution();
        System.out.println(sol.killinSpree(N));
    }
}
import java.util.*;
import java.io.*;

public class ProductIsEvenOrOdd {

    static class Solution {
        public int EvenOdd(String N1, String N2) {
            char last1 = N1.charAt(N1.length() - 1);
            char last2 = N2.charAt(N2.length() - 1);

            boolean even1 = (last1 - '0') % 2 == 0;
            boolean even2 = (last2 - '0') % 2 == 0;

            return (even1 || even2) ? 1 : 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String N1 = br.readLine().trim();
        String N2 = br.readLine().trim();

        Solution sol = new Solution();
        int result = sol.EvenOdd(N1, N2);

        System.out.println(result);
    }
}

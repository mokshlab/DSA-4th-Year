import java.util.Scanner;

public class MinimumWindowSubstring {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.nextLine(),
        String s2 = scanner.nextLine();
        scanner.close();

        Solution solution = new Solution();
        String result = solution.shortestSubsequence(s1, s2);
        System.out.println(result);
    }

    static class Solution {

    public String shortestSubsequence(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0)
            return "";

        int n = s1.length(), m = s2.length(), st = -1, len = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {

            if (s1.charAt(i) != s2.charAt(0))
                continue;

            int j = i, k = 0;

            while (j < n && k < m) {
                if (s1.charAt(j++) == s2.charAt(k))
                    k++;
            }

            if (k < m)
                continue;

            int e = j - 1;
            k = m - 1;

            while (e >= i && k >= 0) {
                if (s1.charAt(e--) == s2.charAt(k))
                    k--;
            }

            e++;

            if (j - e < len) {
                len = j - e;
                st = e;
            }
        }
        return st == -1 ? "" : s1.substring(st, st + len);
    }
}
import java.util.Scanner;

public class SquaresInChessboard {
    static class Solution {
        public long squaresInChessBoard(int n) {
            return (long) n * (n + 1) * (2 * n + 1) / 6;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Solution sol = new Solution();
        long result = sol.squaresInChessBoard(n);
        System.out.println(result);
    }
}

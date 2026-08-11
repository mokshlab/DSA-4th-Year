import java.util.Scanner;

public class XORCountZeroAndOne {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Solution solution = new Solution();
        int result = solution.find_xor(n);
        System.out.println(result);
        scanner.close();
    }

    static class Solution {
    public int find_xor(int n) {
        int ones = 0;
        int bits = 0;

        while (n > 0) {
            if ((n & 1) == 1) {
                ones++;
            }
            bits++;
            n >>= 1;
        }

        int zeros = bits - ones;

        return ones ^ zeros;
    }
}
}

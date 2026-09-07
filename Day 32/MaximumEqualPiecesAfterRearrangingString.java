import java.util.*;

public class MaximumEqualPiecesAfterRearrangingString {
    static int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        int[] freq = new int[256];

        for (char c : s.toCharArray()) {
            freq[c]++;
        }

        int ans = 0;

        for (int count : freq) {
            if (count > 0) {
                ans = gcd(ans, count);
            }
        }

        System.out.println(ans);
    }
}

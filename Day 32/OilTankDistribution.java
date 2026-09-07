import java.util.*;

public class OilTankDistribution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int c = sc.nextInt();

        int sum = 0;
        int minSum = 0;
        int maxSum = 0;

        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            sum += x;
            minSum = Math.min(minSum, sum);
            maxSum = Math.max(maxSum, sum);
        }

        int low = Math.max(0, -minSum);
        int high = Math.min(c, c - maxSum);

        if (low <= high) {
            System.out.println(low);
        } else {
            System.out.println(Math.max(0, Math.min(c, -minSum)));
        }
    }
}

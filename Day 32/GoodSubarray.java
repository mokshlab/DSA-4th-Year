import java.util.*;

public class GoodSubarray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        long[] a = new long[n];

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextLong();
        }

        Map<Long, Integer> freq = new HashMap<>();

        int left = 0;
        int distinct = 0;
        long sum = 0;
        long ans = 0;

        for (int right = 0; right < n; right++) {
            freq.put(a[right], freq.getOrDefault(a[right], 0) + 1);

            if (freq.get(a[right]) == 1) {
                distinct++;
            }

            sum += a[right];

            while (distinct > k) {
                freq.put(a[left], freq.get(a[left]) - 1);

                if (freq.get(a[left]) == 0) {
                    freq.remove(a[left]);
                    distinct--;
                }

                sum -= a[left];
                left++;
            }

            ans = Math.max(ans, sum);
        }

        System.out.println(ans);
    }
}

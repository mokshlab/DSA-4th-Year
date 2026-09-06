import java.util.*;

public class FruitIntoBaskets {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] fruits = new int[n];

        for (int i = 0; i < n; i++) {
            fruits[i] = sc.nextInt();
        }

        Map<Integer, Integer> map = new HashMap<>();

        int left = 0;
        int ans = 0;

        for (int right = 0; right < n; right++) {
            map.put(fruits[right], map.getOrDefault(fruits[right], 0) + 1);

            while (map.size() > 2) {
                int x = fruits[left];

                map.put(x, map.get(x) - 1);

                if (map.get(x) == 0) {
                    map.remove(x);
                }

                left++;
            }

            ans = Math.max(ans, right - left + 1);
        }

        System.out.println(ans);
    }
}

import java.util.*;

public class LRUcache {
    public static int[] solve(int capacity, int[][] arr) {
        LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<>(capacity, 0.75f, true);
        ArrayList<Integer> result = new ArrayList<>();

        for (int[] query : arr) {
            if (query[0] == 1) {
                int key = query[1];
                result.add(cache.getOrDefault(key, -1));
            } else {
                int key = query[1];
                int value = query[2];

                if (cache.containsKey(key)) {
                    cache.put(key, value);
                } else {
                    if (cache.size() == capacity) {
                        Iterator<Integer> it = cache.keySet().iterator();
                        it.next();
                        it.remove();
                    }
                    cache.put(key, value);
                }
            }
        }

        int[] ans = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            ans[i] = result.get(i);
        }

        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int capacity = sc.nextInt();
        int n = sc.nextInt();
        int[][] arr = new int[n][3];

        for(int i = 0; i < n; i++) {
            int x = sc.nextInt();
            arr[i][0] = x;

            if(x == 1) {
                int y = sc.nextInt();
                arr[i][1] = y;
            } else {
                int y = sc.nextInt();
                int z = sc.nextInt();
                arr[i][1] = y;
                arr[i][2] = z;
            }
        }

        int[] ans = solve(capacity, arr);

        for(int num : ans) {
            System.out.print(num + " ");
        }

        System.out.println();
    }
}

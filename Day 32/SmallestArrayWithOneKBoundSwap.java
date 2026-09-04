import java.util.*;

public class SmallestArrayWithOneKBoundSwap {
    static int[] smallestArray(int[] a, int k) {
        int n = a.length;

        TreeMap<Integer, TreeSet<Integer>> map = new TreeMap<>();

        for (int i = 1; i <= Math.min(k, n - 1); i++) {
            map.computeIfAbsent(a[i], x -> new TreeSet<>()).add(i);
        }

        for (int i = 0; i < n - 1; i++) {
            if (!map.isEmpty()) {
                int min = map.firstKey();

                if (min < a[i]) {
                    int j = map.get(min).last();

                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;

                    return a;
                }
            }

            int remove = i + 1;
            if (remove < n) {
                TreeSet<Integer> set = map.get(a[remove]);
                if (set != null) {
                    set.remove(remove);
                    if (set.isEmpty()) {
                        map.remove(a[remove]);
                    }
                }
            }

            int add = i + k + 1;
            if (add < n) {
                map.computeIfAbsent(a[add], x -> new TreeSet<>()).add(add);
            }
        }

        return a;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        int k = sc.nextInt();

        a = smallestArray(a, k);

        for (int i = 0; i < n; i++) {
            System.out.println(a[i]);
        }
    }
}

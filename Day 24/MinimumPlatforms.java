import java.util.*;

public class MinimumPlatforms {
    public static int minPla(List<Integer> arr, List<Integer> dep) {
        // Write Your Code Here
        Collections.sort(arr);
        Collections.sort(dep);

        int i = 0, j = 0;
        int platforms = 0;
        int maxPlatforms = 0;

        while (i < arr.size() && j < dep.size()) {
            if (arr.get(i) <= dep.get(j)) {
                platforms++;
                maxPlatforms = Math.max(maxPlatforms, platforms);
                i++;
            } else {
                platforms--;
                j++;
            }
        }

        return maxPlatforms;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> arr = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            arr.add(sc.nextInt());
        }
        int m = sc.nextInt();
        List<Integer> dep = new ArrayList<>();
        for(int i = 0; i < m; i++) {
            dep.add(sc.nextInt());
        }
        int ans = minPla(arr, dep);
        System.out.println(ans);
    }
}
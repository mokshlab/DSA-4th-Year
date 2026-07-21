import java.util.*;

public class SubarrayDivByK {
    static long subarraysDivByK(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        long ans = 0, s = 0;
        for(int x : nums) {
            s += x;
            int r = (int) ((s % k + k) % k);
            ans += map.getOrDefault(r, 0);
            map.put(r, map.getOrDefault(r, 0) + 1);
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        long ans = subarraysDivByK(nums, k);
        System.out.println(ans);
    }
}
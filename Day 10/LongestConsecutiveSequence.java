import java.util.*;

public class LongestConsecutiveSequence {
    public static int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(int x : nums) set.add(x);
        int ans = 0;
        for(int x : set) {
            if(!set.contains(x - 1)) {
                int y = x + 1;
                while(set.contains(y)) y++;
                ans = Math.max(ans, y - x);
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        int ans = longestConsecutive(nums);
        System.out.println(ans);
    }
}
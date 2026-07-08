import java.util.HashMap;
import java.util.Scanner;
public class subarraySumEqualsk {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++){
            nums[i] = sc.nextInt();
        }
        int k = sc.nextInt();
        Solution sol = new Solution();
        System.out.println(sol.subarraySumEqualsK(nums, k));
        sc.close();
    }
    static class Solution{
        public int subarraySumEqualsK(int[] nums, int k){
            HashMap<Integer, Integer> map = new HashMap<>();
            map.put(0, 1);
            int prefixSum = 0;
            int count = 0;
            for(int num : nums){
                prefixSum += num;
                if(map.containsKey(prefixSum - k)){
                    count += map.get(prefixSum - k);
                }
                map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
            }
            return count;
        }
    }
}
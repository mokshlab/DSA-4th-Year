import java.util.*;

public class ContiguousSubarraySum {
    static class Solution {
        public List<Integer> solve (int N, int[] arr, int target){
            long curr_sum = 0;
            int left = 0;
            for(int right = 0; right < N; right++){
                curr_sum += arr[right];
                while(curr_sum > target && left <= right){
                    curr_sum -= arr[left];
                    left++;
                }
                if(curr_sum == target){
                    return Arrays.asList(left + 1, right + 1);
                }
            }
            return Arrays.asList(-1);
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = sc.nextInt();
        }
        int target = sc.nextInt();
        Solution solution = new Solution();
        List<Integer> ans = solution.solve(N, arr, target);
        System.out.println(ans);
    }
}
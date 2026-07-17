import java.util.*;

public class MaxSumSubarray {
    public static long maxSubArraySum(long[] nums, long k) {
        int n = nums.length;
        int K = (int) k;
        long windowSum = 0;

        for (int i = 0; i < K; i++) {
            windowSum += nums[i];
        }
        long maxSum = windowSum;
        for(int i = K; i < n; i++){
            windowSum += nums[i];
            windowSum -= nums[i - K];
            maxSum = Math.max(maxSum, windowSum);
        }

        return maxSum;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long[] nums = new long[(int) n];
        for(int i = 0; i < n; i++){
            nums[i] = sc.nextLong();
    }
        long k = sc.nextLong();
        long ans = maxSubArraySum(nums, k);
        System.out.println(ans);
    }
}
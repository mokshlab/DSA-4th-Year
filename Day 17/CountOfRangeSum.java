import java.util.*;

public class CountOfRangeSum {
    static class Solution {
        public int countRangeSum(int[] nums, int lower, int upper) {
            int n = nums.length;
            long[] sums = new long[n + 1];
            for (int i = 0; i < n; i++) {
                sums[i + 1] = sums[i] + nums[i];
            }
            return countWhileMergeSort(sums, 0, n + 1, lower, upper);
        }

        private int countWhileMergeSort(long[] sums, int start, int end, int lower, int upper) {
            if (end - start <= 1) return 0;
            int mid = (start + end) / 2;
            int count = countWhileMergeSort(sums, start, mid, lower, upper) 
                      + countWhileMergeSort(sums, mid, end, lower, upper);
            
            int j = mid, k = mid, t = mid;
            long[] cache = new long[end - start];
            int r = 0;
            
            for (int i = start; i < mid; i++) {
                while (j < end && sums[j] - sums[i] < lower) j++;
                while (k < end && sums[k] - sums[i] <= upper) k++;
                while (t < end && sums[t] < sums[i]) cache[r++] = sums[t++];
                
                cache[r++] = sums[i];
                count += (k - j);
            }
            
            while (t < end) {
                cache[r++] = sums[t++];
            }
            
            System.arraycopy(cache, 0, sums, start, end - start);
            return count;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        int lower = sc.nextInt();
        int upper = sc.nextInt();

        Solution sol = new Solution();
        int result = sol.countRangeSum(nums, lower, upper);
        System.out.println(result);
    }
}
import java.util.*;

public class CountOfRangeSum {

    static class Solution {

        public int countRangeSum(int[] nums, int lower, int upper) {
            long[] prefix = new long[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                prefix[i + 1] = prefix[i] + nums[i];
            }
            long count = mergeSort(prefix, 0, prefix.length - 1, lower, upper, new long[prefix.length]);
            return (int) count;
        }

        private long mergeSort(long[] arr, int left, int right, int lower, int upper, long[] temp) {
            if (left >= right) {
                return 0;
            }

            int mid = left + (right - left) / 2;
            long count = mergeSort(arr, left, mid, lower, upper, temp)
                    + mergeSort(arr, mid + 1, right, lower, upper, temp);

            int l = mid + 1, r = mid + 1;
            for (int i = left; i <= mid; i++) {
                while (l <= right && arr[l] - arr[i] < lower) l++;
                while (r <= right && arr[r] - arr[i] <= upper) r++;
                count += r - l;
            }

            int i = left;
            int j = mid + 1;
            int k = left;
            while (i <= mid && j <= right) {
                if (arr[i] <= arr[j]) {
                    temp[k++] = arr[i++];
                } else {
                    temp[k++] = arr[j++];
                }
            }
            while (i <= mid) {
                temp[k++] = arr[i++];
            }
            while (j <= right) {
                temp[k++] = arr[j++];
            }
            for (int idx = left; idx <= right; idx++) {
                arr[idx] = temp[idx];
            }

            return count;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++)
            nums[i] = sc.nextInt();

        int lower = sc.nextInt();
        int upper = sc.nextInt();

        Solution sol = new Solution();
        System.out.println(sol.countRangeSum(nums, lower, upper));
        sc.close();
    }
}
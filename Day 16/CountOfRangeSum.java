import java.util.*;

public class CountOfRangeSum {

    static long lower, upper;

    public static int countRangeSum(int[] nums, int lowerBound, int upperBound) {
        lower = lowerBound;
        upper = upperBound;

        long[] prefix = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        return (int) mergeSort(prefix, 0, prefix.length - 1);
    }

    static long mergeSort(long[] arr, int left, int right) {
        if (left >= right) return 0;

        int mid = left + (right - left) / 2;
        long count = mergeSort(arr, left, mid) + mergeSort(arr, mid + 1, right);

        int l = mid + 1, r = mid + 1;
        for (int i = left; i <= mid; i++) {
            while (l <= right && arr[l] - arr[i] < lower) l++;
            while (r <= right && arr[r] - arr[i] <= upper) r++;
            count += (r - l);
        }

        long[] temp = new long[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j])
                temp[k++] = arr[i++];
            else
                temp[k++] = arr[j++];
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        System.arraycopy(temp, 0, arr, left, temp.length);

        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int lower = sc.nextInt();
        int upper = sc.nextInt();

        System.out.println(countRangeSum(nums, lower, upper));
    }
}
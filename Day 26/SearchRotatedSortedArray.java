import java.util.*;
import java.io.*;

public class SearchRotatedSortedArray {
    static class Solution {
        public int findKey(int[] arr, int key) {
            int low = 0;
            int high = arr.length - 1;

            while (low <= high) {
                int mid = low + (high - low) / 2;

                if (arr[mid] == key) {
                    return mid;
                }

                if (arr[low] <= arr[mid]) {
                    if (arr[low] <= key && key < arr[mid]) {
                        high = mid - 1;
                    } else {
                        low = mid + 1;
                    }
                } else {
                    if (arr[mid] < key && key <= arr[high]) {
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                }
            }

            return -1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        st.nextToken(); int n = (int) st.nval;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            st.nextToken();
            arr[i] = (int) st.nval;
        }
        st.nextToken(); int key = (int) st.nval;
        Solution sol = new Solution();
        System.out.println(sol.findKey(arr, key));
    }
}
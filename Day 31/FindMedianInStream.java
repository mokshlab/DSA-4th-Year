import java.util.*;
import java.io.*;

public class FindMedianInStream {
    static class Solution {
        public double[] getMedians(int[] arr) {

            int n = arr.length;
            double[] result = new double[n];

            PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());

            PriorityQueue<Integer> right = new PriorityQueue<>();

            for (int i = 0; i < n; i++) {
                int x = arr[i];

                if (left.isEmpty() || x <= left.peek()) {
                    left.add(x);
                } else {
                    right.add(x);
                }

                if (left.size() > right.size() + 1) {
                    right.add(left.poll());
                } else if (right.size() > left.size()) {
                    left.add(right.poll());
                }

                if (left.size() == right.size()) {
                    result[i] = ((double) left.peek() + right.peek()) / 2.0;
                } else {
                    result[i] = left.peek();
                }
            }

            return result;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        st.nextToken();
        int n = (int) st.nval;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            st.nextToken();
            arr[i] = (int) st.nval;
        }
        Solution sol = new Solution();
        double[] res = sol.getMedians(arr);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            if (i > 0) sb.append(' ');
            sb.append(res[i]);
        }
        System.out.println(sb.toString());
    }
}

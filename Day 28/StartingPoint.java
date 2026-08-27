import java.util.*;
import java.io.*;

public class StartingPoint {

    static class Solution {
        public int[] findStartingPoint(int x, int y, int[][] pathCoordinates) {
            for (int i = 0; i < pathCoordinates.length; i++) {
                x -= pathCoordinates[i][0];
                y -= pathCoordinates[i][1];
            }

            return new int[]{x, y};
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);

        st.nextToken(); int x = (int) st.nval;
        st.nextToken(); int y = (int) st.nval;

        st.nextToken(); int n = (int) st.nval;

        int[][] pathCoordinates = new int[n][2];
        for (int i = 0; i < n; i++) {
            st.nextToken(); pathCoordinates[i][0] = (int) st.nval;
            st.nextToken(); pathCoordinates[i][1] = (int) st.nval;
        }

        Solution sol = new Solution();
        int[] result = sol.findStartingPoint(x, y, pathCoordinates);

        System.out.println("{" + result[0] + "," + result[1] + "}");
    }
}

import java.util.*;
import java.io.*;

public class StockBuyAndSell {
  static class Solution{
    public int maxProfit(int[] prices){
        int p=0;
        for(int i=1; i<prices.length; i++){
            if(prices[i]>prices[i-1])
            p += prices[i]-prices[i-1];
        }
        return p;
    }
  }

  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
    StreamTokenizer st = new StreamTokenizer(br);
    st.nextToken();
    int n = (int) st.nval;
    int[] prices = new int[n];
    for ( int i = 0; i < n; i++){
        st.nextToken();
        prices[i] = (int) st.nval;
    }
    Solution sol = new Solution();
    System.out.println(sol.maxProfit(prices));
  }
}
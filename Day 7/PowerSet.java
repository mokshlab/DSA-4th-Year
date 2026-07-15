import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PowerSet {
    static class Solution {
        public List<String> AllPossibleStrings(String s) {
            List<String> ans = new ArrayList<>();
            for(int i = 1; i < (1 << s.length()); i++) {
                StringBuilder str = new StringBuilder();
                for(int j = 0; j < s.length(); j++) {
                    if((i & (1 << j)) != 0) {
                        str.append(s.charAt(j));
                    }
                }
                ans.add(str.toString());
            }
            Collections.sort(ans);
            return ans;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        Solution sol = new Solution();
        List<String> ans = sol.AllPossibleStrings(s);
        for(String str : ans) {
            System.out.println(str);
        }
        sc.close();
    }
}
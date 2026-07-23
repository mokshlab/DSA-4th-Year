import java.util.*;

public class LexicographicallySmallestString {

    public static String lexicographicallySmallestString(String s) {
        char[] ch = s.toCharArray();
        Arrays.sort(ch);
        return new String(ch);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        System.out.println(lexicographicallySmallestString(s));
        sc.close();
    }
}
import java.util.Scanner;

public class Pizza {
    static int maxPizza(int m, int n) {
        return (m * n) / 2;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();

        int ans = maxPizza(m, n);
        System.out.println(ans);
    }
}

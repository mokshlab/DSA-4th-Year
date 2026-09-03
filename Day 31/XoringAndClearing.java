import java.util.*;

public class XoringAndClearing {

    static void solve(int arr[], int n) {

        for(int i = 0; i < n; i++) {
            arr[i] = arr[i] ^ i;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int arr[] = new int[n];

        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        solve(arr, n);

        for(int i = 0; i < n; i++) {
            System.out.print(arr[i]);
            if(i != n-1) System.out.print(" ");
        }
        System.out.println();

        for(int i = 0; i < n; i++) {
            arr[i] = 0;
            System.out.print(arr[i]);
            if(i != n-1) System.out.print(" ");
        }
        System.out.println();

        sc.close();
    }
}

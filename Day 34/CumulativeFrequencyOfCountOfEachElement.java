import java.util.*;

public class CumulativeFrequencyOfCountOfEachElement {
    static List<Integer> cumulativeFrequency(int[] arr) {
        Map<Integer, Integer> freq = new HashMap<>();

        for (int x : arr) {
            freq.put(x, freq.getOrDefault(x, 0) + 1);
        }

        List<Integer> elements = new ArrayList<>(freq.keySet());
        Collections.sort(elements);

        List<Integer> result = new ArrayList<>();
        int cumulative = 0;

        for (int x : elements) {
            cumulative += freq.get(x);
            result.add(cumulative);
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();

        while (sc.hasNextInt())
            list.add(sc.nextInt());

        int[] arr = new int[list.size()];

        for (int i = 0; i < arr.length; i++)
            arr[i] = list.get(i);

        List<Integer> res = cumulativeFrequency(arr);

        for (int x : res)
            System.out.print(x + " ");
    }
}

import java.util.*;
import java.io.*;

class AutoCompleteSystem {

    private HashMap<String, Integer> frequency;
    private StringBuilder currentQuery;

    public AutoCompleteSystem(String[] sentences, int[] times) {
        frequency = new HashMap<>();
        currentQuery = new StringBuilder();

        for (int i = 0; i < sentences.length; i++) {
            String sentence = sentences[i];

            frequency.put(
                sentence,
                frequency.getOrDefault(sentence, 0) + times[i]
            );
        }
    }

    public List<String> input(char c) {

        if (c == '#') {
            String sentence = currentQuery.toString();

            if (!sentence.isEmpty()) {
                frequency.put(
                    sentence,
                    frequency.getOrDefault(sentence, 0) + 1
                );
            }

            currentQuery.setLength(0);

            return new ArrayList<>();
        }

        currentQuery.append(c);

        String prefix = currentQuery.toString();

        List<String> result = new ArrayList<>();

        for (String sentence : frequency.keySet()) {
            if (sentence.startsWith(prefix)) {
                result.add(sentence);
            }
        }

        Collections.sort(result, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                int frequencyA = frequency.get(a);
                int frequencyB = frequency.get(b);

                if (frequencyA != frequencyB) {
                    return Integer.compare(frequencyB, frequencyA);
                }

                return a.compareTo(b);
            }
        });

        if (result.size() > 3) {
            return new ArrayList<>(result.subList(0, 3));
        }

        return result;
    }
}

public class SearchQueryAutoComplete {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int T = Integer.parseInt(br.readLine().trim());

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());

            String[] sentences = new String[n];
            int[] times = new int[n];

            for (int i = 0; i < n; i++) {
                sentences[i] = br.readLine();
                times[i] = Integer.parseInt(br.readLine().trim());
            }

            AutoCompleteSystem acs = new AutoCompleteSystem(sentences, times);

            int q = Integer.parseInt(br.readLine().trim());

            while (q-- > 0) {
                String query = br.readLine();

                for (int i = 0; i < query.length(); i++) {
                    char c = query.charAt(i);

                    List<String> res = acs.input(c);

                    if (c != '#') {
                        out.println(String.join(",", res));
                    }
                }
            }
        }

        out.flush();
    }
}

import java.io.*;
import java.util.*;

public class Main {

    static class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0;
        private int len = 0;

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;

                if (len == -1) {
                    return -1;
                }
            }

            return buffer[ptr++];
        }

        String next() throws IOException {
            StringBuilder sb = new StringBuilder();
            int c;

            do {
                c = read();
            } while (c <= 32 && c != -1);

            while (c > 32) {
                sb.append((char) c);
                c = read();
            }

            return sb.toString();
        }

        int nextInt() throws IOException {
            int c;

            do {
                c = read();
            } while (c <= 32 && c != -1);

            int result = 0;

            while (c >= '0' && c <= '9') {
                result = result * 10 + (c - '0');
                c = read();
            }

            return result;
        }
    }

    static class Trie {
        int[] head;
        int[] next;
        int[] to;
        byte[] ch;
        int[] terminal;

        int nodes;
        int edges;

        Trie(int maxNodes) {
            head = new int[maxNodes];
            terminal = new int[maxNodes];

            Arrays.fill(head, -1);

            next = new int[maxNodes];
            to = new int[maxNodes];
            ch = new byte[maxNodes];

            nodes = 1;
            edges = 0;
        }

        int findChild(int node, int c) {
            int edge = head[node];

            while (edge != -1) {
                if (ch[edge] == c) {
                    return to[edge];
                }

                edge = next[edge];
            }

            return -1;
        }

        int getOrCreateChild(int node, int c) {
            int edge = head[node];

            while (edge != -1) {
                if (ch[edge] == c) {
                    return to[edge];
                }

                edge = next[edge];
            }

            int newNode = nodes++;

            to[edges] = newNode;
            ch[edges] = (byte) c;
            next[edges] = head[node];
            head[node] = edges;

            edges++;

            return newNode;
        }

        int findUniquePrefixLength(String word) {
            int node = 0;

            for (int i = 0; i < word.length(); i++) {
                int c = word.charAt(i) - 'a';

                int child = findChild(node, c);

                if (child == -1) {
                    return i + 1;
                }

                node = child;
            }

            return word.length();
        }

        int insert(String word) {
            int node = 0;

            for (int i = 0; i < word.length(); i++) {
                int c = word.charAt(i) - 'a';
                node = getOrCreateChild(node, c);
            }

            terminal[node]++;

            return terminal[node];
        }
    }

    static class Solution {

        Trie trie;
        HashMap<String, Integer> frequency;

        Solution(int maxNodes) {
            trie = new Trie(maxNodes);
            frequency = new HashMap<>();
        }

        void check(String[] cities, int n) {
            StringBuilder out = new StringBuilder();

            for (int i = 0; i < n; i++) {
                String city = cities[i];

                Integer count = frequency.get(city);

                if (count != null) {
                    count++;
                    frequency.put(city, count);

                    out.append(city)
                       .append(' ')
                       .append(count)
                       .append('\n');
                } else {
                    frequency.put(city, 1);

                    int prefixLength = trie.findUniquePrefixLength(city);

                    trie.insert(city);

                    out.append(city, 0, prefixLength)
                       .append('\n');
                }

                if (out.length() >= (1 << 20)) {
                    System.out.print(out);
                    out.setLength(0);
                }
            }

            System.out.print(out);
        }
    }

    public static void main(String[] args) throws Exception {

        FastScanner fs = new FastScanner();

        int n = fs.nextInt();

        String[] cities = new String[n];

        long totalLength = 0;

        for (int i = 0; i < n; i++) {
            cities[i] = fs.next();
            totalLength += cities[i].length();
        }

        int maxNodes = (int) totalLength + 1;

        Solution solution = new Solution(maxNodes);

        solution.check(cities, n);
    }
}

import java.util.*;
import java.io.*;

public class MergeTwoSortedReverseLinkedList {
    static class Node {
        int data;
        Node next;
        Node(int d) { data = d; next = null; }
    }

    static class Solution {
    Node mergeResult(Node node1, Node node2) {

        Node head = null;

        while (node1 != null && node2 != null) {
            if (node1.data <= node2.data) {
                Node next = node1.next;
                node1.next = head;
                head = node1;
                node1 = next;
            } else {
                Node next = node2.next;
                node2.next = head;
                head = node2;
                node2 = next;
            }
        }

        while (node1 != null) {
            Node next = node1.next;
            node1.next = head;
            head = node1;
            node1 = next;
        }

        while (node2 != null) {
            Node next = node2.next;
            node2.next = head;
            head = node2;
            node2 = next;
        }

        return head;
    }
}

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        st.nextToken(); int n = (int) st.nval;
        st.nextToken(); int m = (int) st.nval;
        Node h1 = null, t1 = null;
        for (int i = 0; i < n; i++) {
            st.nextToken();
            Node nd = new Node((int) st.nval);
            if (h1 == null) { h1 = nd; t1 = nd; }
            else { t1.next = nd; t1 = nd; }
        }
        Node h2 = null, t2 = null;
        for (int i = 0; i < m; i++) {
            st.nextToken();
            Node nd = new Node((int) st.nval);
            if (h2 == null) { h2 = nd; t2 = nd; }
            else { t2.next = nd; t2 = nd; }
        }
        Solution sol = new Solution();
        Node res = sol.mergeResult(h1, h2);
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        while (res != null) {
            if (!first) sb.append(' ');
            sb.append(res.data);
            first = false;
            res = res.next;
        }
        System.out.println(sb.toString());
    }
}
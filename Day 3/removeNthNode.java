import java.io.*;
public class removeNthNode {
    static class ListNode{
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    static class Solution{
        public ListNode removeNthFromEnd(ListNode head, int n){
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            ListNode first = dummy;
            ListNode second = dummy;
            for(int i = 1; i <= n + 1; i++){
                first = first.next;
            }
            while(first != null){
                first = first.next;
                second = second.next;
            }
            second.next = second.next.next;
            return dummy.next;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        st.nextToken();
        int sz = (int) st.nval;
        ListNode head = null, tail = null;
        for(int i = 0; i < sz; i++){
            st.nextToken();
            int v = (int) st.nval;
            ListNode node = new ListNode(v);
            if(head == null) head = tail = node;
            else{
                tail.next = node;
                tail = node;
            }
    }
        st.nextToken();
        int n = (int) st.nval;
        Solution sol = new Solution();
        ListNode res = sol.removeNthFromEnd(head, n);
        
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        while(res != null){
            if(!first) sb.append(' ');
            sb.append(res.val);
            first = false;
            res = res.next;
        }
        System.out.println(sb.toString());
    }
}
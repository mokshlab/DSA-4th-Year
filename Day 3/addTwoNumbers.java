import java.util.*;
class Node {
    int data;
    Node next;
    Node(int val){
        data = val;
        next = null;
    }
}
public class addTwoNumbers{
    public static Node add(Node head, Node head1){
        Node dummy = new Node(0);
        Node curr = dummy;
        int carry = 0;
        while(head != null || head1 != null || carry != 0){
            int sum = carry;
            if(head != null){
                sum += head.data;
                head = head.next;
            }
            if(head1 != null){
                sum += head1.data;
                head1 = head1.next;
            }
            curr.next = new Node(sum % 10);
            curr = curr.next;
            carry = sum / 10;
        }
        return dummy.next;
    }
    public static void printList(Node head){
        while(head != null){
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }
    public static Node buildList(int n, Scanner sc){
        Node head = null, tail = null;
        for(int i = 0; i < n; i++){
            int val = sc.nextInt();
            Node node = new Node(val);
            if(head == null){
                head = tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
        }
        return head;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Node head = buildList(n, sc);
        int n1 = sc.nextInt();
        Node head1 = buildList(n1, sc);
        Node result = add(head, head1);
        printList(result);
        sc.close();
    }
}
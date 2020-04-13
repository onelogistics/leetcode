package basic.linkedList;
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
        next = null;
    }
    public static ListNode getListNodes(int...vals) {
        ListNode head=new ListNode(vals[0]);
        ListNode cur=head;
        for (int i=1;i<vals.length;i++) {
            ListNode listNode=new ListNode(vals[i]);
            cur.next=listNode;
            cur=listNode;
        }
        return head;
    }
}
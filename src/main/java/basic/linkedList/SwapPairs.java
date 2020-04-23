package basic.linkedList;

/**
 * 成对交换节点
 * 1, 2, 3, 4, 5, 6, 7
 * 2, 1, 4, 3, 6, 5, 7
 */
public class SwapPairs {
    public static void main(String[] args) {
        ListNode.println(swapPairs(ListNode.getListNodes(1, 2, 3, 4, 5, 6, 7)));
    }
    public ListNode swapPairsRecursion(ListNode head) {
        if(head == null || head.next == null) return head;
        //第二个节点设为头结点
        ListNode newHead=head.next;
        //第一个节点指向下一组节点
        head.next=swapPairs(head.next.next);
        //第二个节点指向第一个节点
        newHead.next=head;
        return newHead;
    }

    /**
     * 创建虚拟节点，逻辑交换节点指向
     * @param head
     * @return
     */
   public static ListNode swapPairs(ListNode head) {
       ListNode dummy=new ListNode(0);
       dummy.next=head;
       ListNode cur=dummy;
       while (cur.next!=null && cur.next.next!=null) {
           ListNode swap1=cur.next;
           ListNode swap2=cur.next.next;
           cur.next=swap2;
           swap1.next=swap2.next;
           swap2.next=swap1;
           cur=swap1;
       }
       return dummy.next;
   }
}

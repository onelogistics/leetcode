package basic.doublePointer;

import basic.linkedList.ListNode;


/**
 *143. Reorder List
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 *
 * Example 1:
 *
 * Given 1->2->3->4, reorder it to 1->4->2->3.
 * Example 2:
 *
 * Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 *
 */
public class ReorderList {
    public static void main(String[] args) {
        ReorderList reorderList=new ReorderList();
        ListNode head=ListNode.getListNodes(1,2,3,4,5);
        reorderList.reorderList(head);
        ListNode.println(head);
    }
    /**
     * 基本思想：
     * 1、利用快慢指针将链表一分为二
     * 2、将链表的后半部分倒序
     * 3、merge
     * @param head
     */
    public void reorderList(ListNode head) {
        if(head==null || head.next==null || head.next.next==null) return;
        ListNode slowPointer=head;
        ListNode fastPointer=head;
        while (fastPointer!=null && fastPointer.next!=null) {
            slowPointer=slowPointer.next;
            fastPointer=fastPointer.next.next;
        }
        //此时slow指向的是前半部分的最后一个元素
        ListNode behindHead=slowPointer.next;
        slowPointer.next=null;

        //后半部分倒序排序
        ListNode pre=null;
        ListNode cur=behindHead;
        while (cur!=null) {
            ListNode next=cur.next;
            cur.next=pre;
            pre=cur;
            cur=next;
        }
        //此时pre是倒序之后的链表后半部分
        //merge
        cur=head;
        while (pre!=null) {
            ListNode beforeNext=cur.next;
            ListNode behindNext=pre.next;
            cur.next=pre;
            pre.next=beforeNext;
            pre=behindNext;
            cur=beforeNext;
        }
    }
}

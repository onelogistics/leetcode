package basic.doublePointer;

import basic.linkedList.ListNode;


public class DeleteDuplicatesFromListNode {
    private static DeleteDuplicatesFromListNode deleteDuplicatesFromListNode = new DeleteDuplicatesFromListNode();

    public static void main(String[] args) {
        ListNode head = ListNode.getListNodes(1,1,1,2,3);
        System.out.println(deleteDuplicatesFromListNode.deleteDuplicates2(head).toString());
    }
    /**
     * leetcode82   Remove Duplicates from Sorted List II
     * <p>
     * 给定一个有序链表，删除所有重复元素，只保留不重复的元素
     * <p>
     * Example 1:
     * <p>
     * Input: 1->2->3->3->4->4->5
     * Output: 1->2->5
     * Example 2:
     * <p>
     * Input: 1->1->1->2->3
     * Output: 2->3
     */
    public ListNode deleteDuplicates2(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null) {
            boolean isDuplicate =false;
            //不断删除重复元素，while循环结束时只保留一个元素
            while (cur.next!=null && cur.val == cur.next.val) {
                cur.next = cur.next.next;
                isDuplicate =true;
            }
            //如果本轮迭代中出现过重复元素，跳过重复元素
            if(isDuplicate) {
                pre.next=pre.next.next;
            }else {
                pre=pre.next;
            }
            cur=pre.next;
        }
        return dummy.next;
    }

    /**
     * leetcode83. Remove Duplicates from Sorted List
     * Given a sorted linked list, delete all duplicates such that each element appear only once.
     *
     * Example 1:
     *
     * Input: 1->1->2
     * Output: 1->2
     * Example 2:
     *
     * Input: 1->1->2->3->3
     * Output: 1->2->3
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null || head.next==null) return head;
        ListNode cur=head;
        while (cur!=null) {
            while (cur.next!=null && cur.val==cur.next.val) {
                cur.next=cur.next.next;
            }
            cur=cur.next;
        }
        return head;
    }
}

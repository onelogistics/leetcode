package basic.sort;

import basic.linkedList.ListNode;

/**
 * 链表的归并排序
 */
public class MergeListSort {
    public static void main(String[] args) {
        ListNode head=ListNode.getListNodes(2, 3, 5, 1, 4, 1, 4, 6);
        ListNode sorted=new MergeListSort().sort(head);
        while (sorted!=null) {
            System.out.print(sorted.val);
            sorted=sorted.next;
        }
    }
    public ListNode sort(ListNode head) {
        if(head==null || head.next==null)
            return head;
        //利用一快一慢两个指针寻找中间节点
        ListNode fast=head;
        ListNode slow=head;
        //链表长度小于2不进入循环
        while (fast!=null && fast.next!=null && fast.next.next!=null) {
            fast=fast.next.next;
            slow=slow.next;
        }
        fast=slow;
        slow=slow.next;
        fast.next=null;
        //各自分别排序
        fast=sort(head);
        slow=sort(slow);
        //排序完成后归并
        return merge(fast,slow);
    }

    /**
     * 归并两个子链表
     * @param left
     * @param right
     * @return
     */
    public ListNode merge(ListNode left,ListNode right) {
        ListNode dummy=new ListNode(0);
        ListNode iterator=dummy;
        while (left!=null && right!=null) {
            if(left.val<=right.val) {
                iterator.next=left;
                left=left.next;
            }else {
                iterator.next=right;
                right=right.next;
            }
            iterator=iterator.next;
        }
        //如果其中一个子链表还有剩余，则直接把iterator指向剩余的子链表
        if(left!=null)
            iterator.next=left;
        if(right!=null)
            iterator.next=right;
        return dummy.next;
    }

}


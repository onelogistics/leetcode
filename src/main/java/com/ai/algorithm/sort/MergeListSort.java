package com.ai.algorithm.sort;

import com.ai.algorithm.linkedList.ListNode;

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
        //两个元素及以内不进入循环
        while (fast!=null && fast.next!=null && fast.next.next!=null) {
            fast=fast.next.next;
            slow=slow.next;
        }
        fast=slow;
        slow=slow.next;
        fast.next=null;
        fast=sort(head);
        slow=sort(slow);
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
        if(left!=null)
            iterator.next=left;
        if(right!=null)
            iterator.next=right;
        return dummy.next;
    }

}


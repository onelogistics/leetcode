package com.ai.leetcode;

import com.ai.algorithm.linkedList.ListNode;

/**反转指定范围内的列表
 * @author JunjunYang
 * @date 2019/12/20 17:41
 */
public class RevertLinkedList {
    public static void main(String[] args) {
        ListNode node=ListNode.getListNodes(1,2,3,4,5,6,7);
        ListNode revert=reverseBetween(node,2,6);
        while (revert!=null) {
            System.out.print(revert.val+" ");
            revert=revert.next;
        }
    }
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode pre=null;
        ListNode cur=head;
        //cur已经指向head了，所以只用移动m-1次，就可以指向第m个节点
        while (m>1) {
            pre=cur;
            cur=cur.next;
            m--;n--;
        }
        //第M-1个节点
        ListNode cHead=pre;
        //第M个节点
        ListNode cTail=cur;
        //反转从M到N之间的指针
        while (n>0) {
            //反转指针指向
            ListNode third=cur.next;
            cur.next=pre;
            //指针迭代
            pre=cur;
            cur=third;
            n--;
        }
        //原第M个节点指向第N+1个节点
        cTail.next=cur;
        if(cHead!=null) {
            //原第M-1个节点指向第N个节点
            cHead.next=pre;
            return head;
        }
        //第M-1个节点为NULL，第N个节点即为头结点
        return pre;
    }
}

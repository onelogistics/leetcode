package com.ai.algorithm.linkedList;

import java.util.HashSet;
import java.util.Set;

/**
 * 返回链表末节点指向的节点
 */
public class CycleNode {
    /**
     * 依次将链表中的元素加入Set中，直接判断是否有重复元素即可
     * memory：O(N),time: O(N)
     * @param head
     * @return
     */
   /* public ListNode detectCycle(ListNode head) {
        Set<ListNode> set=new HashSet();
        ListNode cur=head;
        while(!set.contains(cur) && cur!=null) {
            set.add(cur);
            cur=cur.next;
        }
        return cur;
    }*/

    /**
     * 使用弗洛伊德算法
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
       ListNode slow=head;
       ListNode fast=head;
       while (fast!=null && fast.next!=null) {
           fast=fast.next.next;
           slow=slow.next;
           if(fast==slow) {
               ListNode slow2=head;
               while (slow2!=slow) {
                   slow2=slow2.next;
                   slow=slow.next;
               }
               return slow;
           }
       }
       return null;
    }
}

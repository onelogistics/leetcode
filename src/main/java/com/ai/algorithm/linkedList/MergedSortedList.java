package com.ai.algorithm.linkedList;

import java.util.*;

public class MergedSortedList {
    /**
     * Approach 1:compare one by one
     * Time complexity: O(N*K)
     * Space complexity:O(1)
     * @param
     * @return
     */
/*    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy=new ListNode(0);
        ListNode iter=dummy;
        int inx=0,min=Integer.MAX_VALUE;
        while (true) {
            //比较各个链表的首元素
            for (int i=0;i<lists.length;i++) {
                if(lists[i]!=null && lists[i].val<min) {
                    min=lists[i].val;
                    inx=i;
                }
            }
            //各个链表都已经循环到了null节点
            if(min==Integer.MAX_VALUE) {
                break;
            }
            //链接到链表的首元素，首元素弹出
            iter.next=lists[inx];
            iter=iter.next;
            lists[inx]=lists[inx].next;
            min=Integer.MAX_VALUE;
        }
        return dummy.next;
    }*/
    /**
     * Approach 2:compare one by one (used priorityQueue)
     * N is the total number of nodes,K is the total number of LinkedList
     *  Time complexity:O(N*logK)
     *  Space complexity:O(K)
     * @return
     */
/*    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy=new ListNode(0);
        PriorityQueue<ListNode> priorityQueue=new PriorityQueue<ListNode>(Comparator.comparingInt(o -> o.val));
        for (ListNode L:lists) {
            if(L!=null) {
                priorityQueue.add(L);
            }
        }
        ListNode iter=dummy;
        while (!priorityQueue.isEmpty()) {
            ListNode node=priorityQueue.poll();
            iter.next=node;
            iter=iter.next;
            node=node.next;
            if(node!=null) {
                priorityQueue.add(node);
            }
        }
        return dummy.next;
    }*/
    /**
     * Approach 3: merge with divide and conquer(recursion)
     * Time complexity: O(logK*N)
     * Space complexity:O(logK*N)
     * @return
     */
   /* public ListNode mergeKLists(ListNode[] lists) {
        return partition(lists,0,lists.length-1);
    }
    public ListNode partition(ListNode[] lists,int left,int right) {
        if(left==right) return lists[left];
        if(left<right) {
            int mid=(left+right)/2;
            ListNode leftNode=partition(lists,left,mid);
            ListNode rightNode=partition(lists,mid+1,right);
            return merge(leftNode,rightNode);
        }else {
            return null;
        }
    }
    public ListNode merge(ListNode L1,ListNode L2) {
        if(L1==null) return L2;
        if(L2==null) return L1;
        if(L1.val<L2.val) {
            L1.next=merge(L1.next,L2);
            return L1;
        }else {
            L2.next=merge(L1,L2.next);
            return L2;
        }
    }*/
    public static void main(String[] args) {
        MergedSortedList mergedSortedList=new MergedSortedList();
        mergedSortedList.mergeKLists(ListNode.getListNodes(1,4,5),ListNode.getListNodes(1,3,4),ListNode.getListNodes(2,6));
    }
    /**
     * merge with divide and conquer(iteration)
     * Time complexity: O(N*logK)
     * Space complexity:O(1)
     * @return
     */
    public ListNode mergeKLists(ListNode... lists) {
        if(lists==null || lists.length==0) return null;
        List<ListNode> queue = new LinkedList<>(Arrays.asList(lists));
        while (queue.size()>1) {
            queue.add(merge(queue.get(0),queue.get(1)));
            queue.remove(0);
            queue.remove(0);
        }
        return queue.get(0);
    }
    public ListNode merge(ListNode L1,ListNode L2) {
        ListNode dummy=new ListNode(0);
        ListNode iter=dummy;
        while (L1!=null && L2!=null) {
            if(L1.val<L2.val) {
                iter.next=L1;
                L1=L1.next;
            }else {
                iter.next=L2;
                L2=L2.next;
            }
            iter=iter.next;
        }
        iter.next=L1==null?L2:L1;
        return dummy.next;
    }
}

package basic.linkedList;

/**
 * leetcode160
 * https://leetcode.com/problems/intersection-of-two-linked-lists/
 * 学一个程序，找出两个单链表开始相交的节点
 * example:
 * Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
 * Output: Reference of the node with value = 8
 *
 * 注意:
 * 如果题目没假设链表无环，则一定要做链表是否有环的判断，否则下面的两种解法在链表有环时都可能出现死循环
 */
public class IntersectionNode {
    /**
     * solution 2:两个链表如有交点，则最后n个节点一定相同，设链表A为子链表a和子链表c拼成，链表B为子链表a和子链表b拼成，
     * 则a+c+b+c == b+c+a+c,利用此原理，最多在O(M+N)的时间复杂度内就可以得到结果
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode pA = headA;
        ListNode pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    /**
     * solution 1:长度较长的链表head先移动Diff次，然后同步移动headA和headB，如有交叉，则pA=PB;
     * @param headA
     * @param headB
     * @return
     *//*
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = 0;
        int lenB = 0;
        ListNode pA = headA;
        ListNode pB = headB;
        while (pA != null) {
            lenA++;
            pA = pA.next;
        }
        while (pB != null) {
            lenB++;
            pB = pB.next;
        }
        int diff = Math.abs(lenA - lenB);
        pA = headA;
        pB = headB;
        if (lenA > lenB) {
            for (int i = 0; i < diff; i++)
                pA = pA.next;
        } else {
            for (int i = 0; i < diff; i++)
                pB = pB.next;
        }
        while (pA != pB) {
            pA = pA.next;
            pB = pB.next;
        }
        return pA;
    }*/
}
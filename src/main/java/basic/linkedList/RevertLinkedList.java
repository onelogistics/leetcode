package basic.linkedList;

/**
 * 反转指定范围内的列表
 *
 * @author JunjunYang
 * @date 2019/12/20 17:41
 */
public class RevertLinkedList {
    public static void main(String[] args) {
        ListNode.println(reverseListIterator(ListNode.getListNodes(1, 2, 3, 4, 5, 6, 7)));
        ListNode.println(reverseListRecursion(ListNode.getListNodes(1, 2, 3, 4, 5, 6, 7)));
        ListNode.println(reverseKGroup(ListNode.getListNodes(1, 2, 3, 4, 5, 6, 7), 3));
        ListNode.println(reverseBetween(ListNode.getListNodes(1, 2, 3, 4, 5, 6, 7), 2,4));
    }

    /**
     * 迭代法反转链表
     * 依次记录pre指针和cur指针，不断遍历
     */
    public static ListNode reverseListIterator(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /**
     * 递归法
     * 假设从k+1之后的链表节点都已经反转完成，那要怎么完成前面节点的反转？k.next.next=k,将第k+1个节点的next指向第k个节点，然后记得将第k个节点的next指向null
     */
    public static ListNode reverseListRecursion(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode cur = reverseListRecursion(head.next);
        head.next.next = head;
        head.next = null;
        return cur;
    }

    /**
     * leetcode 25
     * 每k个节点一组，反转链表
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        int count = 0;
        ListNode cur = head;
        while (cur != null && ++count != k) {
            cur = cur.next;
        }
        //不足K个，直接返回
        if (cur == null) return head;
        //递归下一组
        cur = reverseKGroup(cur.next, k);
        while (k-- > 0) {
            //调整指针指向
            ListNode next = head.next;
            head.next = cur;
            //相应指针依次向后移动
            cur = head;
            head = next;
        }
        return cur;
    }

    /**
     * leetcode 92 https://www.cnblogs.com/grandyang/p/4306611.html
     * 反转m到n之间的链表节点,要考虑第m-1个节点为null的情况
     * 1 -> 2 -> 3 -> 4 -> 5 -> NULL
     * <p>
     * 1 -> 3 -> 2 -> 4 -> 5 -> NULL
     * <p>
     * 1 -> 4 -> 3 -> 2 -> 5 -> NULL
     */
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        //找到第m-1个节点
        for (int i = 0; i < m - 1; i++) pre = pre.next;
        //找到第m个节点
        ListNode cur = pre.next;
        //循环，依次更改指针指向，cur指向的节点不断后移
        for (int i = m; i < n; i++) {
            ListNode t = cur.next;
            cur.next = t.next;
            t.next = pre.next;
            pre.next = t;
        }
        return dummy.next;
    }
}

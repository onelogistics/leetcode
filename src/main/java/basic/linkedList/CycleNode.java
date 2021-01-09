package basic.linkedList;

/**
 * leetcode142
 * https://leetcode.com/problems/linked-list-cycle-ii/
 * 返回链表末节点指向的节点：如果链表有环，返回这个环的交点，如果链表无环，返回null
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
     *
     * 找出环起点的证明：
     * 设链表的起始节点到环的起始节点距离为s，环的起始节点到快慢指针相遇节点的距离为m,相遇时快指针已经在环中跑了n1圈，慢指针跑了n2圈，则有
     * s + m + n1r = 2(s + m + n2r),则有s+m=(n1-n2)r,即s与m之和一定是环长度的整数倍，那么用一个指针从链表的起始位置移动，另一个指针从相遇节点同步移动，则这两个指针一定会在环的起始位置相遇。
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        //由于fast肯定比slow跑的快，所以只需要对fast进行空指针验证
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                ListNode slow2 = head;
                while (slow2 != slow) {
                    slow2 = slow2.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }
}

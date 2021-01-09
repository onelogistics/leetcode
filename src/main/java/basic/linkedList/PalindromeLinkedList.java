package basic.linkedList;

/**
 * leetcode234 easy
 * 判断一个链表是不是回文链表
 * Given a singly linked list, determine if it is a palindrome.
 * <p>
 * Example 1:
 * <p>
 * Input: 1->2
 * Output: false
 * Example 2:
 * <p>
 * Input: 1->2->2->1
 * Output: true
 * null指针或者只有一个元素的指针也是回文链表
 */
public class PalindromeLinkedList {
    static PalindromeLinkedList main=new PalindromeLinkedList();
    public static void main(String[] args) {
        main.isPalindrome(ListNode.getListNodes(1,2,3));
    }
    /**
     * 基本思想：找到后半部分，翻转，然后逐个比对
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        //slowPoint指向链表后半部分的头
        ListNode slowPoint = head;
        ListNode quickPoint = head;
        while (quickPoint != null) {
            if (quickPoint.next == null) {
                slowPoint = slowPoint.next;
                break;
            }
            slowPoint = slowPoint.next;
            quickPoint = quickPoint.next.next;
        }
        ListNode behindCur = slowPoint;
        //反转之后后半部分的头指针
        ListNode behindPre = null;
        while (behindCur != null) {
            ListNode next = behindCur.next;
            behindCur.next = behindPre;
            behindPre = behindCur;
            behindCur = next;
        }
        //behindPre是后半部分长度较小的部分，因此只用比较behind是否为null即可
        while (behindPre != null) {
            if (behindPre.val != head.val) {
                return false;
            }
            behindPre = behindPre.next;
            head = head.next;
        }
        return true;
    }
}

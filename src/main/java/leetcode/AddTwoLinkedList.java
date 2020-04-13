package leetcode;

import basic.linkedList.ListNode;

import java.util.Stack;

/**
 * 将两个单链表相加
 * eg input:1->2->3.1->2->7
 * output:2->5->0
 */
public class AddTwoLinkedList {
    public static void main(String[] args) {
        ListNode listNode = solution(ListNode.getListNodes(1, 8, 3), ListNode.getListNodes(1, 2, 7));
        while (listNode != null) {
            System.out.print(listNode.val + " ");
            listNode = listNode.next;
        }
    }

    public static ListNode solution(ListNode head1, ListNode head2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        while (head1 != null) {
            stack1.push(head1.val);
            head1 = head1.next;
        }
        while (head2 != null) {
            stack2.push(head2.val);
            head2 = head2.next;
        }
        int n1 = 0;//head1的值
        int n2 = 0;//head2的值
        int ca = 0;//进位
        int n = 0;//n1+n2+ca
        //当前节点
        ListNode cur = null;
        //前驱节点
        ListNode pre = null;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            n1 = stack1.isEmpty() ? 0 : stack1.pop();
            n2 = stack2.isEmpty() ? 0 : stack2.pop();
            n = n1 + n2 + ca;
            cur = new ListNode(n % 10);
            ca = n / 10;
            //因为是从个位加起，需要将加和后的链表逆序呈现
            cur.next = pre;
            pre = cur;
        }
        if (ca == 1) {
            cur = new ListNode(1);
            cur.next = pre;
        }
        return cur;
    }
}

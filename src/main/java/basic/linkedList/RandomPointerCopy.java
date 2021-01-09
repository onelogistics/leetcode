package basic.linkedList;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 138. Copy List with Random Pointer
 *
 * https://leetcode.com/problems/copy-list-with-random-pointer/
 * 复制链表（此链表节点比较特征，除了指向下一个节点的指针，还有一个random指针随机指向任意其他节点）
 * 此题难点在于如何解决random point的指向问题，通过使用map，建立了real node和copy node的联系
 */
public class RandomPointerCopy {
    public static void main(String[] args) {
        RandomPointerCopy randomPointerCopy=new RandomPointerCopy();
        Node head=buildSample();
        Node copyNode = randomPointerCopy.copyRandomList(head);
        while (copyNode!=null) {
            System.out.println(String.format("node:%s,random:%s",copyNode.val,copyNode.random==null?"null":copyNode.random.val));
            copyNode=copyNode.next;
        }
    }
    /** 构造测试用例 */
    private static Node buildSample() {
        Node node0=new Node(7);
        Node node1=new Node(13);
        Node node2=new Node(11);
        Node node3=new Node(10);
        Node node4=new Node(1);
        node0.next=node1;
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        node1.random=node0;
        node2.random=node4;
        node3.random=node2;
        node4.random=node0;
        return node0;
    }

    //solution 1：use map between real node and copy node
    public Node copyRandomListWithMap(Node head) {
        Map<Node,Node> map=new HashMap<>();
        Node iter=head;
        while (iter!=null) {
            map.put(iter,new Node(iter.val));
            iter=iter.next;
        }
        for (Map.Entry<Node,Node> entry:map.entrySet()) {
            entry.getValue().next=map.get(entry.getKey().next);
            entry.getValue().random=map.get(entry.getKey().random);
        }
        return map.get(head);
    }
    //solution 2 : don't use other data structure.
    public Node copyRandomList(Node head) {
        Node iterator=head;
        //copy a node,and insert to real node's next;
        while (iterator!=null) {
            Node copyNode=new Node(iterator.val);
            Node next=iterator.next;
            iterator.next=copyNode;
            copyNode.next=next;
            iterator=next;
        }
        iterator=head;
        //assign copy node's random pointer
        while (iterator!=null) {
            if(iterator.random!=null)
                iterator.next.random=iterator.random.next;
            //because of iterator.next is copied node,so it's never be null
            iterator=iterator.next.next;
        }
        iterator=head;
        Node copyHead=new Node(0);
        Node copyIterator=copyHead;
        //extract copy node and recover origin list;
        while (iterator!=null) {
            //find copied next node
            copyIterator.next=iterator.next;
            copyIterator=copyIterator.next;
            //find original next node
            iterator.next=iterator.next.next;
            iterator=iterator.next;
        }
        return copyHead.next;
    }
}
// Definition for a Node.
class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
};

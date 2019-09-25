package com.ai.algorithm.linkedList;

import java.util.HashMap;
import java.util.Map;

/**
 * 此题难点在于如何解决random point的指向问题，通过使用map，建立了real node和copy node的联系
 */
public class RandomPointerCopy {
    //solution 1：use map between real node and copy node
//    public Node copyRandomList(Node head) {
//        Map<Node,Node> map=new HashMap<>();
//        Node iter=head;
//        while (iter!=null) {
//            map.put(iter,new Node(iter.val,null,null));
//            iter=iter.next;
//        }
//        for (Map.Entry<Node,Node> entry:map.entrySet()) {
//            entry.getValue().next=map.get(entry.getKey().next);
//            entry.getValue().random=map.get(entry.getKey().random);
//        }
//        return map.get(head);
//    }
    //solution 2 : don't use other data structure.
    public Node copyRandomList(Node head) {
        Node iter=head,next;
        //copy a node,and insert to real node's next;
        while (iter!=null) {
            next=iter.next;
            Node copyNode=new Node(iter.val,null,null);
            iter.next=copyNode;
            copyNode.next=next;
            iter=next;
        }
        iter=head;
        //assign copy node's random pointer
        while (iter!=null) {
            if(iter.random!=null)
            iter.next.random=iter.random.next;
            iter=iter.next.next;
        }
        iter=head;
        Node copyHead=new Node(0,null,null);
        Node copy,copyIter=copyHead;
        //extract copy node and recover origin list;
        while (iter!=null) {
            copy=iter.next;
            copyIter.next=copy;
            copyIter=copy;
            iter.next=iter.next.next;
            iter=iter.next;
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

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
};

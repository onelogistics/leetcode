package basic.linkedList;

/**
 * 给定一个链表和一个值x，对此链表节点进行排序，保证小于x的节点都在前面，大于x的节点都在后面。
 */
public class PartitionList {
    public static void main(String[] args) {
        PartitionList partitionList=new PartitionList();
        ListNode listNode=partitionList.partition(ListNode.getListNodes(1,4,3,2,5,2),3);
        ListNode.println(listNode);
    }

    /**
     * 创建傀儡节点实在太好用了，虽然多了两个节点内存，但是相较于不创建，代码简洁很多
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        ListNode beforeDummyNode=new ListNode(0);
        ListNode afterDummyNode=new ListNode(0);
        ListNode befreCur=beforeDummyNode;
        ListNode afterCur=afterDummyNode;
        while (head!=null) {
            if(head.val<x) {
                befreCur.next=head;
                befreCur=head;
            }else {
                afterCur.next=head;
                afterCur=head;
            }
            head=head.next;
        }
        afterCur.next=null;
        befreCur.next=afterDummyNode.next;
        return beforeDummyNode.next;
    }
//    public ListNode partition(ListNode head, int x) {
//        ListNode lessHead=null;
//        ListNode lessCur=null;
//        ListNode greatHead=null;
//        ListNode greatCur=null;
//        ListNode cur=head;
//        while(cur!=null) {
//            int val=cur.val;
//            if(val<x) {
//                //较小链表还未产生
//                if(lessHead==null) {
//                    lessHead=cur;
//                }else{
//                    lessCur.next=cur;
//                }
//                lessCur=cur;
//            }else {
//                //较大链表还未产生
//                if(greatHead==null) {
//                    greatHead=cur;
//                }else{
//                    greatCur.next=cur;
//                }
//                greatCur=cur;
//            }
//            cur=cur.next;
//        }
//        if(lessHead!=null && greatHead!=null )  {
//            lessCur.next=greatHead;
//            greatCur.next=null;
//            return lessHead;
//        }
//        return head;
//    }
}

package basic.tree;

import org.checkerframework.checker.units.qual.C;

import java.util.Stack;

/**
 * leetcode426 将二叉搜索树转化为排序的双向链表
 *
 * @author JunjunYang
 * @date 2020/2/3 11:10
 */
public class ConvertTreeNode2DoubleList {
    static ConvertTreeNode2DoubleList main = new ConvertTreeNode2DoubleList();
    private TreeNode head;
    private TreeNode pre;

    public static void main(String[] args) {
        TreeNode root = ConstructorTreeNode.reConstructorWithPreAndMid(new int[]{4,2,1,3,5},new int[]{1,2,3,4,5});
        System.out.println(root.toString());
//        TreeNode head = main.treeNodeToDoubleList(root);
        TreeNode head = main.treeNodeToDoubleList2(root);
        while (head != null) {
            System.out.print(head.val+"->");
            head=head.right;
        }

    }
    /**
     * 非递归
     * 解法：借鉴中序遍历的思路，利用傀儡节点串联整个链表
     *
     * @param root
     * @return
     */
    public TreeNode treeNodeToDoubleList(TreeNode root) {
        if (root == null) return null;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode dummy = new TreeNode();
        TreeNode cur = dummy;
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.add(root);
                root = root.left;
            } else {
                TreeNode top = stack.pop();
                cur.right = top;
                top.left = cur;
                cur = top;
                //遍历当前节点的右子树，循环判断
                root = top.right;
            }
        }
        return dummy.right;
    }

    /**递归解法
     * @param root
     * @return
     */
    public TreeNode treeNodeToDoubleList2(TreeNode root) {
        head = pre = null;
        treeNode2DoubleList2Recursion(root);
        return head;
    }

    public void treeNode2DoubleList2Recursion(TreeNode root) {
        if (root == null) {
            return;
        }
        treeNode2DoubleList2Recursion(root.left);
        //head未被赋值，说明是最左节点
        if (head == null) {
            head = root;
            pre = root;
        } else {
            pre.right = root;
            root.left = pre;
            pre = root;
        }
        treeNode2DoubleList2Recursion(root.right);
    }


}

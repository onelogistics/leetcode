package com.ai.algorithm.tree;

/**
 * leetcode426 将二叉搜索树转化为排序的双向链表
 *
 * @author JunjunYang
 * @date 2020/2/3 11:10
 */
public class ConvertTreeNode2DoubleList {
    protected TreeNode leftLast = null;

    public TreeNode Convert(TreeNode root) {
        if (root == null)
            return null;
        if (root.left == null && root.right == null) {
            leftLast = root;// 最后的一个节点可能为最右侧的叶节点
            return root;
        }
        // 1.将左子树构造成双链表，并返回链表头节点
        TreeNode left = Convert(root.left);
        // 3.如果左子树链表不为空的话，将当前root追加到左子树链表
        if (left != null) {
            leftLast.right = root;
            root.left = leftLast;
        }
        leftLast = root;// 当根节点只含左子树时，则该根节点为最后一个节点
        // 4.将右子树构造成双链表，并返回链表头节点
        TreeNode right = Convert(root.right);
        // 5.如果右子树链表不为空的话，将该链表追加到root节点之后
        if (right != null) {
            right.left = root;
            root.right = right;
        }
        return left != null ? left : root;
    }
}

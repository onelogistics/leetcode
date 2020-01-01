package com.ai.algorithm.tree;

/**
 * Given a binary tree, determine if it is height-balanced.
 * a binary tree in which the left and right subtrees of every node differ in height by no more than 1.
 *
 * @author JunjunYang
 * @date 2020/1/1 11:02
 */
public class IsBalanced {
    /**
     * 当root为空的时候，我们将None也看成是一棵二叉树，所以返回True。接着我们判断左子树高度和右子树高度差是不是大于1，如果是，那么我们返回False就好啦。如果不是接着递归判断左子树和右子树是不是一棵平衡二叉树。
     *
     * @param root
     * @return
     */
    public boolean solution(TreeNode root) {
        if (root == null) {
            return true;
        }
        return Math.abs(TreeNode.maxPath(root.left) - TreeNode.maxPath(root.right)) < 1 && solution(root.left) && solution(root.right);
    }

    /**
     * 只遍历一次，不平衡的子树高度设为-1
     *
     * @param root
     * @return
     */
    public boolean solution2(TreeNode root) {
        return maxDepth2(root) != -1;
    }

    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth2(root.left);
        int right = maxDepth2(root.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }
}

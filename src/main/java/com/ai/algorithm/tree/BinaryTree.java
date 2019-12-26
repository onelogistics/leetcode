package com.ai.algorithm.tree;

/**
 * @author JunjunYang
 * @date 2019/12/26 17:05
 */

public class BinaryTree {
    public int val;
    public BinaryTree left;
    public BinaryTree right;

    public BinaryTree(int val, BinaryTree left, BinaryTree right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    /**
     * 前序遍历
     * @param i
     * @param vals
     * @return
     */
    public void preOrder(BinaryTree root) {
        if(root!=null) {
            System.out.print(root.val+" ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

}

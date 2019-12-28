package com.ai.algorithm.tree;

import java.util.Arrays;

/**
 * @author JunjunYang
 * @date 2019/12/26 17:05
 */

public class BinaryTree {
    public int val;
    public BinaryTree left;
    public BinaryTree right;

    public BinaryTree(int val) {
        this.val = val;
    }

    /**利用前序数组和中序数组重建二叉树
     * @param pre
     * @param mid
     * @return
     */
    public static BinaryTree reConstructor(int[] pre, int[] mid) {
        if (pre.length == 0 || mid.length == 0) {
            return null;
        }
        BinaryTree root = new BinaryTree(pre[0]);
        for (int i = 0; i < mid.length; i++) {
            if (pre[0] == mid[i]) {
                root.left = reConstructor(Arrays.copyOfRange(pre, 1, i + 1), Arrays.copyOfRange(mid, 0, i));
                root.right = reConstructor(Arrays.copyOfRange(pre, i + 1, pre.length), Arrays.copyOfRange(mid, i + 1, mid.length));
                break;
            }
        }
        return root;
    }

    /**
     * 前序遍历
     *@param root
     * @return
     */
    public static void preOrder(BinaryTree root) {
        if (root != null) {
            System.out.print(root.val + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

}

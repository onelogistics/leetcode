package com.ai.algorithm.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author JunjunYang
 * @date 2019/12/26 17:04
 */
public class MirrorBinaryTree {
    public static void main(String[] args) {
        BinaryTree binaryTree = BinaryTree.reConstructor(new int[]{1, 2, 4, 7, 3, 5, 6, 8}, new int[]{4, 7, 2, 1, 5, 3, 8, 6});
        BinaryTree mirror = solution(binaryTree);
        BinaryTree.preOrder(mirror);
        solution2(binaryTree);
        System.out.println();
        BinaryTree.preOrder(binaryTree);
    }

    /**
     * 递归：镜像二叉树，反转左右子树
     *
     * @param root
     * @return
     */
    public static BinaryTree solution(BinaryTree root) {
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }
        BinaryTree left = solution(root.left);
        BinaryTree right = solution(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    /**
     * 非递归
     *
     * @param root
     * @return
     */
    public static void solution2(BinaryTree root) {
        if (root == null) {
            return;
        }
        Queue<BinaryTree> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BinaryTree binaryTree = queue.poll();
            BinaryTree temp = binaryTree.left;
            binaryTree.left = binaryTree.right;
            binaryTree.right = temp;
            if (binaryTree.left != null) {
                queue.add(binaryTree.left);
            }
            if (binaryTree.right != null) {
                queue.add(binaryTree.right);
            }
        }
    }

}

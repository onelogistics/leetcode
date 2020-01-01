package com.ai.algorithm.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, determine if it is a complete binary tree.
 * In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 *
 * @author JunjunYang
 * @date 2020/1/1 11:19
 */
public class IsCompleteTree {
    public boolean solution(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        //是否遇到过NUlL节点
        boolean seenNull = false;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                if (!seenNull) {
                    seenNull = true;
                }
                //遇到了NULL节点.继续遍历下一个节点
                continue;
            } else if (seenNull) {
                //之前已经遇到了NULL节点且本节点不为NULL
                return false;
            }
            queue.add(node.left);
            queue.add(node.right);
        }
        return true;
    }

    public boolean solution2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        //将遇到NULL之前的所有节点都PUSH进去
        while (queue.peek() != null) {
            TreeNode node = queue.poll();
            queue.add(node.left);
            queue.add(node.right);
        }
        //弹出队列头部的NULL节点，如果还有多余节点，则说明不为完全二叉树
        while (!queue.isEmpty() && queue.peek() == null) {
            queue.poll();
        }
        return queue.isEmpty();
    }
}

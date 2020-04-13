package basic.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author JunjunYang
 * @date 2019/12/26 17:04
 */
public class MirrorBinaryTree {
    public static void main(String[] args) {
        TreeNode treeNode = TreeNode.reConstructor(new int[]{1, 2, 4, 7, 3, 5, 6, 8}, new int[]{4, 7, 2, 1, 5, 3, 8, 6});
        TreeNode mirror = solution(treeNode);
        solution2(treeNode);
        System.out.println();
    }

    /**
     * 递归：镜像二叉树，反转左右子树
     *
     * @param root
     * @return
     */
    public static TreeNode solution(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }
        TreeNode left = solution(root.left);
        TreeNode right = solution(root.right);
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
    public static void solution2(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            TreeNode temp = treeNode.left;
            treeNode.left = treeNode.right;
            treeNode.right = temp;
            if (treeNode.left != null) {
                queue.add(treeNode.left);
            }
            if (treeNode.right != null) {
                queue.add(treeNode.right);
            }
        }
    }

}

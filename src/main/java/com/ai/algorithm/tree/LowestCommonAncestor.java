package com.ai.algorithm.tree;

/**
 * 最低公共祖先
 * leetcode 236
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 *
 * @author JunjunYang
 * @date 2020/1/1 21:10
 */
public class LowestCommonAncestor {
    public TreeNode solution(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = solution(root.left, p, q);
        TreeNode right = solution(root.right, p, q);
        if (left != null && right != null) {
            //左子树和右子树返回值都不为null，说明一个在左子树一个在右子树
            return root;
        }
        //左子树为null，说明p和q都在右子树、反之都在左子树
        return left == null ? right : left;
    }

    /**
     * 求二叉搜索树的最小公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode solution2(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val > p.val && root.val > q.val) {
            return solution2(root.left, p, q);
        } else if (root.val < p.val && root.val < q.val) {
            return solution2(root.right, p, q);
        }
        return root;
    }
}

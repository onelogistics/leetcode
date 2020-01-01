package com.ai.algorithm.tree;

import com.ai.algorithm.linkedList.ListNode;

import java.util.*;

/**
 * @author JunjunYang
 * @date 2019/12/26 17:05
 */

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    /**
     * 利用前序数组和中序数组重建二叉树
     *
     * @param pre
     * @param mid
     * @return
     */
    public static TreeNode reConstructor(int[] pre, int[] mid) {
        if (pre.length == 0 || mid.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(pre[0]);
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
     * 求树的深度(根节点深度为1)
     *
     * @param root
     * @return
     */
    public static int maxPath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxPath(root.left);
        int right = maxPath(root.right);
        return Math.max(left, right) + 1;
    }

    /**
     * 求树的最小深度(根节点到最近叶子节点的节点个数)
     * DFS思想，递归实现
     *
     * @param root
     * @return
     */
    public static int minPath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = minPath(root.left);
        int right = minPath(root.right);
        //(left==0 || right==0)用来判断左子树为空或者右子树为空的情况，如果不加以判断，最终结果返回的是根节点到非叶子节点的距离。
        return (left == 0 || right == 0) ? left + right + 1 : Math.min(left, right) + 1;
    }

    /**
     * 最小深度
     * BFS思想，队列实现（相较于DFS实现，此方案在树倾斜的很严重时效率较高,如左子树50000个节点，右子树2个节点）
     *
     * @param root
     * @return
     */
    public static int minPathBFS(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        int min = 1;
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            //此循环是为了将上一层的所有节点都弹出队列，以便统计min值
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    return min;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                size--;
            }
            min++;
        }
        return min;
    }

    /**
     * 求二叉树中的节点个数
     *
     * @param root
     * @return
     */
    public static int NumOfTreeNode(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return NumOfTreeNode(root.left) + NumOfTreeNode(root.right) + 1;
    }

    /**
     * 求二叉树中叶子节点的个数
     *
     * @param root
     * @return
     */
    public static int NumOfLeafNode(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return NumOfLeafNode(root.left) + NumOfLeafNode(root.right);
    }

    public static void main(String[] args) {
        //1,2,3,4,5,null,7
        TreeNode treeNode=TreeNode.reConstructor(new int[]{1,2,4,5,3,7},new int[]{4,2,5,1,3,7});
        System.out.println(NumOfKLevelTreeNode(treeNode,3));
        System.out.println(NumOfKLevelTreeNodeBFS(treeNode,3));
        System.out.println(Arrays.toString(kLevelTreeNodeIterator(treeNode,3).toArray()));
    }

    /**
     * 遍历第K层的所有子节点
     * @param root
     * @param k
     * @return
     */
    public static List<Integer> kLevelTreeNodeIterator(TreeNode root, int k) {
        List<Integer> ans=new ArrayList<>();
        kLevelTreeNodeIterator(root,k,ans);
        return ans;
    }
    private static void kLevelTreeNodeIterator(TreeNode root, int k,List<Integer> ans) {
        if (root == null || k < 1) {
            return;
        }
        if(k==1) {
            ans.add(root.val);
        }
        kLevelTreeNodeIterator(root.left,k-1,ans);
        kLevelTreeNodeIterator(root.right,k-1,ans);
    }
    /**
     * 求二叉树第K层的节点个数DFS(根节点为第一层)
     *
     * @param root
     * @param k
     * @return
     */
    public static int NumOfKLevelTreeNode(TreeNode root, int k) {
        if (root == null || k < 1) {
            return 0;
        }
        if (k == 1) {
            return 1;
        }
        return NumOfKLevelTreeNode(root.left, k - 1) + NumOfKLevelTreeNode(root.right, k - 1);
    }

    /**
     * 求二叉树第K层的节点个数BFS(根节点为第一层)
     *
     * @param root
     * @return
     */
    public static int NumOfKLevelTreeNodeBFS(TreeNode root, int k) {
        if (root == null || k < 1) {
            return 0;
        }
        if (k == 1) {
            return 1;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        //层数
        int level = 1;
        //每层的个数
        int size = queue.size();
        while (level < k) {
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(root.left);
                }
                if (node.right != null) {
                    queue.add(root.right);
                }
                size--;
            }
            size = queue.size();
            level++;
        }
        return size;
    }
}

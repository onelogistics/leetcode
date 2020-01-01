package com.ai.algorithm.tree;

import java.util.*;

/**
 * @author JunjunYang
 * @date 2020/1/1 12:10
 */
public class TraverseOrder {
    /**
     * 递归前序遍历
     *
     * @param root
     * @return
     */
    public static List<Integer> preOrderRecursion(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preOrderRecursion(root, list);
        return list;
    }

    private static void preOrderRecursion(TreeNode root, List<Integer> ans) {
        if (root != null) {
            ans.add(root.val);
            preOrderRecursion(root.left,ans);
            preOrderRecursion(root.right,ans);
        }
    }

    /**
     * 非递归前序遍历
     *
     * @param root
     * @return
     */
    public static List<Integer> preOrderIterator(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                ans.add(node.val);
                //先放入右节点
                stack.add(node.right);
                stack.add(node.left);
            }
        }
        return ans;
    }

    /**
     * 非递归中序遍历
     *
     * @param root
     * @return
     */
    public static List<Integer> midOrderIterator(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.empty()) {
            //一直找到最左子节点
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            root = stack.pop();
            ans.add(root.val);
            //把右子树放到栈中
            root = root.right;
        }
        return ans;
    }

    /**
     * 非递归后续遍历
     * 解法一：逆序插入值
     *
     * @param root
     * @return
     */
    public static List<Integer> postOrderIterator(TreeNode root) {
        LinkedList<Integer> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            //先将根节点插入最后一位，然后将左节点右节点推入栈中。
            ans.addFirst(node.val);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return ans;
    }

    /**
     * 非递归后序遍历
     * 解法二
     *
     * @param root
     * @return
     */
    public static List<Integer> postOrderIterator2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        TreeNode pre = null;
        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.peek();
            //当前节点右子节点不为null且未被访问过
            if (current.right != null && pre != current.right) {
                current = current.right;
            } else {
                stack.pop();
                list.add(current.val);
                pre = current;
                current = null;
            }
        }
        return list;
    }

    /**
     * 非递归层次遍历
     * (左视图和右视图都是层次遍历的一个变种,取每层最左边的值或者最右边的值)
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> leverOrderIterator(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> cur = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                //放入当前节点
                cur.add(node.val);
                //将当前节点的左右子节点加入队列中
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            ans.add(cur);
        }
        return ans;
    }

    /**
     * 层次遍历
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> leverOrderRecursion(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        leverOrderRecursion(root, ans, 0);
        return ans;
    }

    private static void leverOrderRecursion(TreeNode root, List<List<Integer>> ans, int level) {
        if (root == null) return;
        if (ans.size() == level) {
            ans.add(new ArrayList<>());
        }
        ans.get(level).add(root.val);
        leverOrderRecursion(root.left, ans, level + 1);
        leverOrderRecursion(root.right, ans, level + 1);
    }
}

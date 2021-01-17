package basic.tree;

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
            //dfs,将所有左节点压栈，然后依次pop出来，达到回溯的效果
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            //去除最左的叶子节点
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
    /**
     * 之字形层次遍历
     * 103. Binary Tree Zigzag Level Order Traversal
     * Given binary tree [3,9,20,null,null,15,7],
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * return its zigzag level order traversal as:
     * [
     *   [3],
     *   [20,9],
     *   [15,7]
     * ]
     */
    public static List<List<Integer>> levelOrderRecursionZigzag(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        levelOrderRecursionZigzag(root,result,0);
        return result;
    }
    private static void levelOrderRecursionZigzag(TreeNode root, List<List<Integer>> result, int level) {
        if(root == null) return;
        if(result.size() == level) {
            result.add(new LinkedList<>());
        }
        // 正向遍历
        if(level%2 == 0) {
            ((LinkedList)result.get(level)).add(root.val);
        }else {
            ((LinkedList)result.get(level)).addFirst(root.val);
        }
        levelOrderRecursionZigzag(root.left, result, level+1);
        levelOrderRecursionZigzag(root.right, result, level+1);
    }
}

package basic.tree;

/**
 * @author JunjunYang
 * @date 2020/1/1 11:44
 */
public class IsSameTreeNode {
    /**
     * 判断两个二叉树是否相同
     * @param root1
     * @param root2
     * @return
     */
    public boolean isSameTreeNode(TreeNode root1,TreeNode root2) {
        if(root1==null && root2==null) {
            return true;
        }else if(root1==null || root2==null) {
            return false;
        }
        boolean left=isSameTreeNode(root1.left,root2.left);
        boolean right=isSameTreeNode(root1.right,root2.right);
        return root1.val==root2.val && left && right;
    }

    /**
     * 判断两个二叉树是否互为镜像
     * @param root1
     * @param root2
     * @return
     */
    public boolean isMirror(TreeNode root1,TreeNode root2) {
        //边界条件，都为null时返回true，只有其中一个不为null时返回false，两个都不为null时进入递归
        if(root1==null && root2==null) {
            return true;
        }else if(root1==null || root2==null) {
            return false;
        }
        return root1.val==root2.val && isMirror(root1.left,root2.right) && isMirror(root1.right,root2.left);
    }
}

package basic.tree;

/**
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过根结点也可能不穿过。
 *      2
 *   3      4
 * 5
 * 则最长距离为从4节点到5节点，最终结果为3
 *
 * @author JunjunYang
 * @date 2019/12/30 20:38
 */
public class DiameterOfBinaryTree {
    //真实的MaxLen
    int ans;

    public static void main(String[] args) {
        TreeNode root1 = TreeNode.reConstructor(new int[]{2, 3, 5, 4}, new int[]{5, 3, 2, 4});
        TreeNode root2 = TreeNode.reConstructor(new int[]{4,-7,2,3,5,7,6,4,7,9,8}, new int[]{-7,4,7,5,3,6,2,9,7,4,8});
        DiameterOfBinaryTree diameterOfBinaryTree1 =new DiameterOfBinaryTree();
        System.out.println(diameterOfBinaryTree1.diameterOfBinaryTree(root1));
        System.out.println(diameterOfBinaryTree1.diameterOfBinaryTree(root2));
    }
    public int diameterOfBinaryTree(TreeNode root) {
        ans = 0;
        depth(root);
        return ans;
    }

    /**
     * 递归求解节点的最大深度，顺便计算最大直径
     * @param node
     * @return
     */
    public int depth(TreeNode node) {
        if (node == null) return 0;
        int L = depth(node.left);
        int R = depth(node.right);
        //最大距离等于左子树深度+右子树深度,之所以要取较大值，是由于最长直径不一定经过root节点，比如示例2
        ans = Math.max(ans, L+R);
        //返回当前节点的深度=左右子节点中较深的深度+1
        return Math.max(L, R) + 1;
    }
}

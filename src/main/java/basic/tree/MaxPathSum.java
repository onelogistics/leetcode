package basic.tree;

/**leetcode124
 * Given a non-empty binary tree, find the maximum path sum.
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.
 *        -10
 *       2    6
 *   -2   -3  -4  5
 *   则最大路径和为节点6到节点5，最终结果为11
 * @author JunjunYang
 * @date 2019/12/30 20:41
 */
public class MaxPathSum {
    int maxSum;
    public static void main(String[] args) {
        TreeNode root2 = TreeNode.reConstructor(new int[]{-10, 2, -2, -3, 6, -4, 5}, new int[]{-2, 2, -3, -10, -4, 6, 5});
        System.out.println(new MaxPathSum().solution(root2));
    }
    //递归解法
    public int solution(TreeNode root) {
        maxSum=Integer.MIN_VALUE;
        compute(root);
        return maxSum;
    }
    public int compute(TreeNode root) {
        if(root == null) {
            return 0;
        }
        //左子树单边最长路径(从左子树的任意子节点开始并以左子树根节点结尾)之和有可能是负数，此时根节点的最长路径就无需左子树了，直接从自身起始，所以要取0和左子树的较大值
        int left=Math.max(0,compute(root.left));
        int right=Math.max(0,compute(root.right));
        //可能不经过root节点,所以取maxSum和left+right+root.val中的较大值
        maxSum=Math.max(maxSum,left+right+root.val);
        //递归求取以每一个节点作为根节点的单边最长路径
        return Math.max(left,right)+root.val;
    }
}

package com.ai.algorithm.tree;

/**
 * 寻找二叉树任意两个节点之间的最长距离
 *      2
 *   3      4
 * 5
 * 则最长距离为从3节点到5节点，最终结果为3
 *
 * @author JunjunYang
 * @date 2019/12/30 20:38
 */
public class FindMaxLen {
    //真实的MaxLen
    int maxLen = 0;

    public static void main(String[] args) {
        BinaryTree root1 = BinaryTree.reConstructor(new int[]{2, 3, 5, 4}, new int[]{5, 3, 2, 4});
        BinaryTree root2 = BinaryTree.reConstructor(new int[]{-10, 2, -2, -3, 6, -4, 5}, new int[]{-2, 2, -3, -10, -4, 6, 5});
        FindMaxLen findMaxLen1=new FindMaxLen();
        findMaxLen1.solution(root1);
        FindMaxLen findMaxLen2=new FindMaxLen();
        findMaxLen2.solution(root2);
        System.out.println(findMaxLen1.maxLen);
        System.out.println(findMaxLen2.maxLen);
    }
    public int solution(BinaryTree root) {
        if (root == null || (root.left == null && root.right == null)) {
            return 0;
        }
        //左子树的路径长度
        int leftLen = solution(root.left) + 1;
        //右子树的路径长度
        int rightLen = solution(root.right) + 1;
        //左子树+右子树的路径长度
        int tmp = leftLen + rightLen;
        if (tmp > maxLen) maxLen = tmp;
        //返回较大的子树长度
        return leftLen > rightLen ? leftLen : rightLen;
    }
}

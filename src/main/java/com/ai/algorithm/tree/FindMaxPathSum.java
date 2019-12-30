package com.ai.algorithm.tree;

/**求二叉树中最大路径之和（路径之和即为节点权重的累加，权重有可能为负数）
 *        -10
 *       2    6
 *   -2   -3  -4  5
 *   则最大路径和为节点6到节点5，最终结果为11
 * @author JunjunYang
 * @date 2019/12/30 20:41
 */
public class FindMaxPathSum {
    public static void main(String[] args) {
        BinaryTree root2 = BinaryTree.reConstructor(new int[]{-10, 2, -2, -3, 6, -4, 5}, new int[]{-2, 2, -3, -10, -4, 6, 5});
    }
}

package com.ai.algorithm.tree;

import java.util.Arrays;

/**leetcode 1008
 * 根据先序遍历重建二叉搜索树
 * @author JunjunYang
 * @date 2020/1/1 20:49
 */
public class BstFromPreorder {
    int index;
    public static void main(String[] args) {
        TreeNode treeNode=new BstFromPreorder().solution(new int[]{8,5,1,7,10,12});
        System.out.println(Arrays.toString(TraverseOrder.preOrderRecursion(treeNode).toArray()));
    }

    public TreeNode solution(int[] preOrder) {
        index=0;
        return solution(preOrder,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }

    /**
     * 设定最大值与最小值，以设置终止条件
     * @param preOrder
     * @param minValue
     * @param maxValue
     * @return
     */
    private TreeNode solution(int[] preOrder,int minValue,int maxValue) {
        if(index==preOrder.length) {
            return null;
        }
        int val=preOrder[index];
        if(val<minValue || val>maxValue) {
            return null;
        }
        index++;
        TreeNode root=new TreeNode(val);
        root.left=solution(preOrder,minValue,val);
        root.right=solution(preOrder,val,maxValue);
        return root;
    }
}

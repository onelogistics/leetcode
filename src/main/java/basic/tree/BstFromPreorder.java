package basic.tree;

import java.util.Arrays;

/**leetcode 1008
 * 根据先序遍历重建二叉搜索树
 * @author JunjunYang
 * @date 2020/1/1 20:49
 */
public class BstFromPreorder {
    int index;
    public static void main(String[] args) {
        TreeNode treeNode=new BstFromPreorder().solution(new int[]{5,3,2,4,7,6,8});
        System.out.println(Arrays.toString(TraverseOrder.preOrderRecursion(treeNode).toArray()));
    }

    public TreeNode solution(int[] preOrder) {
        index=0;
        return solution(preOrder,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }

    /**
     * 设定最大值与最小值，以设置终止条件
     * 巧妙的通过最大最小值来终止递归
     * @param preOrder
     * @param minValue
     * @param maxValue
     * @return
     */
    private TreeNode solution(int[] preOrder,int minValue,int maxValue) {
        //查找到数组末尾，返回null
        if(index==preOrder.length) {
            return null;
        }
        int val=preOrder[index];
        //遍历到的value已经不符合二叉搜索树的定义，返回null
        if(val<minValue || val>maxValue) {
            return null;
        }
        index++;
        TreeNode root=new TreeNode(val);
        root.left=solution(preOrder,minValue,val);
        root.right=solution(preOrder,val,maxValue);
        return root;
    }

    /**
     * 解法2，分别找到左子区间和右子区间，递归
     * @param preorder
     * @return
     */
    public TreeNode bstFromPreOrder(int[] preOrder) {
        return bstFromPreOrder(preOrder,0,preOrder.length);
    }
    //左闭右开区间
    public TreeNode bstFromPreOrder(int[] preOrder, int start, int end) {
        if(start >= end) return null;
        int i=start+1;
        //找到第一个大于根节点的下标，也就是右子树的起点
        for(;i<end && preOrder[i]<preOrder[start];i++);
        TreeNode root = new TreeNode(preOrder[start]);
        root.left = bstFromPreOrder(preOrder,start+1,i);
        root.right = bstFromPreOrder(preOrder,i,end);
        return root;
    }
}

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
}

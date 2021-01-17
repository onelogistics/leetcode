package basic.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConstructorTreeNode {
    @Test
    public void testReConstructorWithPreAndMid() {
        TreeNode treeNode1 = reConstructorWithPreAndMid(new int[]{1,2,4,3,5,6},new int[]{4,2,1,5,3,6});
        List<List<Integer>> expectList=new ArrayList<>();
        expectList.add(Arrays.asList(1));
        expectList.add(Arrays.asList(2,3));
        expectList.add(Arrays.asList(4,5,6));
        String expectString = Arrays.deepToString(expectList.toArray());
        Assert.assertEquals(expectString,treeNode1.toString());
    }

    /**
     * leetcode105
     * 利用前序数组和中序数组重建二叉树，不使用中间数组
     * @param pre
     * @param mid
     * @return
     */
    public static TreeNode reConstructorWithPreAndMid(int[] preorder, int[] inorder) {
        if(preorder == null || inorder == null || preorder.length != inorder.length) return null;
        return reConstructorWithPreAndMid(preorder,inorder,0,preorder.length-1,0,inorder.length-1);
    }

    /**
     * leetcode106
     * 利用后序数组和中序数组重建二叉树，不使用中间数组
     * @param mid
     * @param back
     * @return
     */
    public static TreeNode reConstructorWithMidAndBack(int[] inorder, int[] postorder) {
        if(inorder == null || postorder == null || inorder.length!=postorder.length) return null;
        return reConstructorWithMidAndBack(inorder, postorder,0,inorder.length-1,0,postorder.length-1);
    }


    private static TreeNode reConstructorWithPreAndMid(int[] pre, int[] mid, int preStart, int preEnd, int midStart, int midEnd) {
        if(preStart > preEnd) return null;
        TreeNode root = new TreeNode(pre[preStart]);
        //中序数组中根节点的下标
        int rootIndex=0;
        for (int i=midStart;i<=midEnd;i++) {
            if(mid[i] == pre[preStart]) {
                rootIndex=i;
                break;
            }
        }
        int subTreeSize=rootIndex-midStart;
        root.left=reConstructorWithPreAndMid(pre,mid,preStart+1,preStart+subTreeSize,midStart,rootIndex-1);
        root.right=reConstructorWithPreAndMid(pre,mid,preStart+subTreeSize+1,preEnd,rootIndex+1,midEnd);
        return root;
    }
    private static TreeNode reConstructorWithMidAndBack(int[] mid, int[] back, int midStart, int midEnd, int backStart, int backEnd) {
        if(backStart>backEnd) return null;
        TreeNode root=new TreeNode(back[backEnd]);
        for (int i=midStart;i<=midEnd;i++) {
            if(mid[i] == back[backEnd]) {
                root.left=reConstructorWithMidAndBack(mid,back,midStart,i-1,backStart,backStart+(i-midStart)-1);
                root.right=reConstructorWithMidAndBack(mid,back,i+1,midEnd,backStart+(i-midStart),backEnd-1);
                break;
            }
        }
        return root;
    }
}

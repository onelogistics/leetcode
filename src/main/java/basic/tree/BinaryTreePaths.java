package basic.tree;

import java.util.ArrayList;
import java.util.List;

/**leetcode 257
 * Given a binary tree, return all root-to-leaf paths.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 *
 * Input:
 *
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 *
 * Output: ["1->2->5", "1->3"]
 *
 * Explanation: All root-to-leaf paths are: 1->2->5, 1->3
 */
public class BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList();
        if(root != null) binaryTreePaths(root,"",list);
        return list;
    }
    public void binaryTreePaths(TreeNode root, String s, List<String> list) {
        if(root.left == null && root.right == null){
            list.add(s+root.val);
        }
        if(root.left != null) binaryTreePaths(root.left,s+root.val+"->",list);
        if(root.right != null) binaryTreePaths(root.right, s+root.val+"->", list);
    }
}

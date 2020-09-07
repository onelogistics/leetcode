package basic.slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/fruit-into-baskets/
 * 题目描述
 * 给定一个数组A，数组中每一个元素i都代表一棵树，每棵树产生的水果类型为A[i],
 * 现在你有两个水果筐，水果筐中可以装任意数量的水果，但是每个水果筐只能装一种类型的水果，
 * 你可以选择从任意一棵树开始采摘水果，采摘之后向右移动，直到没有水果可采摘，请问最终能收集到
 * 的水果总数最大为多少？
 * 示例：
 * Example 1:
 *
 * Input: [1,2,1]
 * Output: 3
 * Explanation: We can collect [1,2,1].
 * Example 2:
 *
 * Input: [0,1,2,2]
 * Output: 3
 * Explanation: We can collect [1,2,2].
 * If we started at the first tree, we would only collect [0, 1].
 * Example 3:
 *
 * Input: [1,2,3,2,2]
 * Output: 4
 * Explanation: We can collect [2,3,2,2].
 * If we started at the first tree, we would only collect [1, 2].
 * Example 4:
 *
 * Input: [3,3,3,1,2,1,1,2,3,3,4]
 * Output: 5
 * Explanation: We can collect [1,2,1,1,2].
 * If we started at the first tree or the eighth tree, we would only collect 4 fruits.
 */
public class TotalFruit {
    /**
     * @see LengthOfLongestSubstringKDistinct#solutionUseMapByIndex(String, int) 
     * @param tree
     * @return
     */
    public int totalFruit(int[] tree) {
        Map<Integer,Integer> map = new HashMap<>();
        int left=0;
        int result=0;
        for (int i=0;i<tree.length;i++) {
            map.put(tree[i],i);
            while (map.size()>2) {
                if(map.get(tree[left])==left) {
                    map.remove(tree[left]);
                }
                left++;
            }
            result=Math.max(result,i-left+1);
        }
        return result;
    }

    /**
     * https://www.cnblogs.com/grandyang/p/11129845.html
     * 尚未理解 解题思路
     * @param tree
     * @return
     */
    public int totalFruit2(int[] tree) {
        int res=0,left=0,right=-1,n=tree.length;
        for (int i=1;i<n;i++) {
            if(tree[i]==tree[i-1]) continue;
            if(right>=0 && tree[right]!=tree[i]) {
                res=Math.max(res,i-left);
                left=right+1;
            }
            right=i-1;
        }
        return Math.max(n-left,res);
    }
}

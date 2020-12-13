package basic.dynamic;

/**
 * leetcode5627
 *
 * 石子游戏中，爱丽丝和鲍勃轮流进行自己的回合，爱丽丝先开始 。
 *
 * 有 n 块石子排成一排。每个玩家的回合中，可以从行中 移除 最左边的石头或最右边的石头，并获得与该行中剩余石头值之 和 相等的得分。当没有石头可移除时，得分较高者获胜。
 *
 * 鲍勃发现他总是输掉游戏（可怜的鲍勃，他总是输），所以他决定尽力 减小得分的差值 。爱丽丝的目标是最大限度地 扩大得分的差值 。
 *
 * 给你一个整数数组 stones ，其中 stones[i] 表示 从左边开始 的第 i 个石头的值，如果爱丽丝和鲍勃都 发挥出最佳水平 ，请返回他们 得分的差值 。
 *
 *
 * 示例 1：
 *
 * 输入：stones = [5,3,1,4,2]
 * 输出：6
 * 解释：
 * - 爱丽丝移除 2 ，得分 5 + 3 + 1 + 4 = 13 。游戏情况：爱丽丝 = 13 ，鲍勃 = 0 ，石子 = [5,3,1,4] 。
 * - 鲍勃移除 5 ，得分 3 + 1 + 4 = 8 。游戏情况：爱丽丝 = 13 ，鲍勃 = 8 ，石子 = [3,1,4] 。
 * - 爱丽丝移除 3 ，得分 1 + 4 = 5 。游戏情况：爱丽丝 = 18 ，鲍勃 = 8 ，石子 = [1,4] 。
 * - 鲍勃移除 1 ，得分 4 。游戏情况：爱丽丝 = 18 ，鲍勃 = 12 ，石子 = [4] 。
 * - 爱丽丝移除 4 ，得分 0 。游戏情况：爱丽丝 = 18 ，鲍勃 = 12 ，石子 = [] 。
 * 得分的差值 18 - 12 = 6 。
 * 示例 2：
 *
 * 输入：stones = [7,90,5,1,100,10,10,2]
 * 输出：122
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/stone-game-vii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class StoneGameVII {

    /**
     * 记忆化深度优先搜索
     * @param stones
     * @return
     */
    public int stoneGameVII(int[] stones) {
        //dp[i][j]代表在本轮中得到的最大价值差
        int[][] dp = new int[stones.length][stones.length];
        //pre[i+1]代表stone[0]...stone[i]的和
        int[] pre = new int[stones.length+1];
        for (int i=0;i<stones.length;i++) {
            pre[i+1] = pre[i]+stones[i];
        }
        return dfs(0,stones.length-1,pre,dp);
    }
    public int stoneGameVII2(int[] stones) {
        //dp[i][j]代表在本轮中得到的最大价值差
        int[][] dp = new int[stones.length][stones.length];
        //pre[i+1]代表stone[0]...stone[i]的和
        int[] pre = new int[stones.length+1];
        for (int i=0;i<stones.length;i++) {
            pre[i+1] = pre[i]+stones[i];
        }
        for (int i=stones.length-1;i>=0;i--) {
            for (int j=i+1;j<stones.length;j++) {
                if(j==i+1) dp[i][j]=Math.max(stones[i],stones[j]);
                else dp[i][j]=Math.max(pre[j+1]-pre[i+1]-dp[i+1][j], pre[j]-pre[i]-dp[i][j-1]);
            }
        }
        return dp[0][stones.length-1];
    }
    /**
     * 记忆化深度优先搜索
     * @param l
     * @param r
     * @param pre
     * @param dp
     * @return
     */
    private int dfs(int l, int r, int[] pre, int[][] dp) {
        if(l == r) return 0;
        if(dp[l][r]!=0) return dp[l][r];
        //pre[r+1]-pre[l+1]为本次Alice先选得到的分数和，dfs(l+1,r,pre,dp)为下次迭代Bob和Alice的最大价值差
        //这两者之差，即为Alice本轮的最大价值差
        return dp[l][r] = Math.max(pre[r+1]-pre[l+1]-dfs(l+1,r,pre,dp), pre[r]-pre[l]-dfs(l,r-1,pre,dp));
    }
}

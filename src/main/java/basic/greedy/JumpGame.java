package basic.greedy;

/**
 * leetcode 55
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个位置。
 * 示例 1:
 *
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 从位置 0 到 1 跳 1 步, 然后跳 3 步到达最后一个位置。
 * 示例 2:
 *
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 */
public class JumpGame {
    static JumpGame jumpGame=new JumpGame();
    public static void main(String[] args) {
        jumpGame.canJump(new int[]{2,3,1,1,4});
    }
    /**
     * 题目求解的是是否能跳出数组尾，那我们只需要关注最大跳跃长度即可
     * 由于数组中每一个值就是当前位置所能跳跃的最大长度，我们从头遍历
     * 不断计算在当前位置i所能跳跃的最大长度，如果最终在当前位置i所能跳跃的最大长度为0
     * 则说明我们无法跳出此数组了
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        //在当前位置能跳跃的最大长度
        int far=-1;
        for (int i=0;i<nums.length;i++) {
            //更新far，
            if(nums[i]>far) far=nums[i];
            //可以跳出数组
            if(far>=nums.length-1-i) return true;
            if(far==0) break;
            //继续下一轮位置的迭代，此处far--是因为跳到下一个位置需要耗费一步
            far--;
        }
        return false;
    }

    /**
     * 如果某一个作为 起跳点 的格子可以跳跃的距离是 3，那么表示后面 3 个格子都可以作为 起跳点。
     * 可以对每一个能作为 起跳点 的格子都尝试跳一次，把 能跳到最远的距离 不断更新。
     * 如果可以一直跳到最后，就成功了。
     *
     * 作者：ikaruga
     * 链接：https://leetcode-cn.com/problems/jump-game/solution/55-by-ikaruga/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     */
    public boolean canJump2(int[] nums) {
        int maxRight=0;
        for (int i=0;i<nums.length;i++) {
            if (i>maxRight) return false;
            maxRight=Math.max(maxRight,i+nums[i]);
        }
        return true;
    }
}

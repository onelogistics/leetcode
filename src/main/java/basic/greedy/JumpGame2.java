package basic.greedy;

/**
 * leetcode45 跳跃游戏2
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置
 * https://zhuanlan.zhihu.com/p/82831669
 * 示例
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 *
 */
public class JumpGame2 {
    static JumpGame2 jumpGame2=new JumpGame2();
    public static void main(String[] args) {
        System.out.println(jumpGame2.solution(new int[]{2, 3, 1, 1, 4}));
    }

    /**
     * 每次都以最大步数去跳，不过在跳过的元素中，有可能存在更大的步数
     * 因此需要不断计算最大步数，并更新end，遇到一次end就说明此前的最大步数已经到头了，需要再跳一次
     * @param nums
     * @return
     */
    public int solution(int[] nums) {
        //最大能抵达的位置
        int maxReach=0;
        //需要走的步数
        int step=0;
        //记录最大可选范围的下标
        int end=0;
        //因为只要求到达数组最后一个位置，所以是len-1，如果写成i<len,则有可能算上从最后一个位置起跳的步数
        for (int i=0;i<nums.length-1;i++) {
            //不断更新可选范围内的能抵达的最远位置
            maxReach=Math.max(maxReach,i+nums[i]);
            //到达最大可选范围时，必须要跳一步，同时更新最大可选范围值
            if(i==end) {
                step++;
                end=maxReach;
            }
        }
        return step;
    }
}

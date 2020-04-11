package com.ai.algorithm.greedy;

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
    public boolean canJump(int[] nums) {
        //在当前位置能跳跃的最大长度
        int far=-1;
        for (int i=0;i<nums.length;i++) {
            //更新far，
            if(nums[i]>far) far=nums[i];
            //可以跳出数组
            if(far>=nums.length-i+1) return true;
            if(far==0) break;
            //继续下一轮位置的迭代，此处far--是因为跳到下一个位置需要耗费一步
            far--;
        }
        return false;
    }

}

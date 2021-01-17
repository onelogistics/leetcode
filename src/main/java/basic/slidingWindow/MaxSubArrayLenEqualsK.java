package basic.slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.
 *
 * Note:
 * The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.
 *
 * Example 1:
 *
 * Input: nums = [1, -1, 5, -2, 3], k = 3
 * Output: 4
 * Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.
 * Example 2:
 *
 * Input: nums = [-2, -1, 2, 1], k = 1
 * Output: 2
 * Explanation: The subarray [-1, 2] sums to 1 and is the longest.
 * Follow Up:
 * Can you do it in O(n) time?
 */
public class MaxSubArrayLenEqualsK {
    /**
     *解题思路
     * 题目给出了一个数组和一个K值，要求找出最大子数组，使其之和等于K
     * 这里我们可以想到用累加和来解题，sum[i]表示0~i的累加和，那sum[i]-sum[j]就表示j~i的累加和
     * 我们只需要求出累加和为K的各个子数组，然后找出其中最长的一个即可。
     *
     * @param nums
     * @param k
     * @return
     */
    public int maxSubArrayLen(int[] nums,int k) {
        //存储累加和与下标的对应关系，如果出现重复的累加和，只保留第一个
        Map<Integer,Integer> map=new HashMap<>();
        //累加和
        int sum=0;
        //累加和为k的子数组长度
        int res=0;
        for (int i=0;i<nums.length;i++) {
            sum+=nums[i];
            //如果有累加和直接为k的，res就是当前的数组下标加1
            if(sum == k) {
                res=i+1;
            }else if(map.containsKey(sum-k)) {//如果map中有等于sum-k的累加和，则sum-k对应的下标到i即为符合条件的子数组
                res=Math.max(res,i-map.get(sum-k));
            }
            if(!map.containsKey(sum)) {
                map.put(sum,i);
            }
        }
        return res;
    }
}

package basic.slidingWindow;

/**
 * @see MaxSubArrayLenEqualsK#maxSubArrayLen(int[], int) 和这道题比较相似
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。如果不存在符合条件的连续子数组，返回 0。
 *
 * 示例:
 *
 * 输入: s = 7, nums = [2,3,1,2,4,3]
 * 输出: 2
 * 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
 */
public class minSubArrayLenEqualOrGreaterS {
    /**
     *
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen(int s, int[] nums) {
        if(nums == null) return 0;
        //minLen初始化为nums.length+1,如果到方法尾仍为此值，则说明不存在满足条件的最小数组
        int left=0,minLen=nums.length+1,sum=0;
        for (int right = 0; right < nums.length; right++) {
            sum+=nums[right];
            while (sum >= s) {
                minLen = Math.min(minLen,right-left+1);
                sum-=nums[left];
                left++;
            }
        }
        if(minLen == nums.length+1) {
            return 0;
        }
        return minLen;
    }
}

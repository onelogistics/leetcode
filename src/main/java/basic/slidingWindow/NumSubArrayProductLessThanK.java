package basic.slidingWindow;

/**
 * 713. Subarray Product Less Than K
 * 给定一个数组nums和一个整数k，求元素之积小于k的连续数组的个数
 * Example 1:
 * Input: nums = [10, 5, 2, 6], k = 100
 * Output: 8
 * Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
 * Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
 */
public class NumSubArrayProductLessThanK {
    /**
     * 本题可以使用滑动窗口的思想结论
     * 题目要求的是满足元素之积小于K的连续数组的个数，那我们怎么求出连续数组且保证不出现重复数组呢。
     * 遍历元素，对每一个当前元素right来讲，我们只需要找到最小的left，使得nums[left]*nums[left+1]..
     * nums[right]<k,此时，所有起始元素大于等于left，且以right元素为结尾的连续数组都是满足要求的，
     * 这样的连续数组一共有right-left+1个，分别为(left,left+1..right), (left+1...right),(right)。
     * 在遍历每一个right时统计覆盖条件的连续数组个数，直至数组末尾
     * @param nums
     * @param k
     * @return
     */
    public int numSubArrayProductLessThanK(int[] nums, int k) {
        if( k <= 1) return 0;
        int prod = 1, ans = 0, left = 0;
        for (int right = 0; right < nums.length; right++) {
            prod *= nums[right];
            while (prod >= k) prod /=nums[left++];
            ans += right -left +1;
        }
        return ans;
    }
}

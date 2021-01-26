package basic.slidingWindow;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * leetcode239  Sliding Window Maximum
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口 k 内的数字。滑动窗口每次只向右移动一位。
 *
 * 返回滑动窗口最大值。
 *
 * 示例:
 *
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 */
public class MaxSlidingWindow {
    static MaxSlidingWindow main = new MaxSlidingWindow();
    public static void main(String[] args) {
        int[] ans=main.maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7},3);
        System.out.println(Arrays.toString(ans));
    }
    /**
     * 解法：此题用最大堆来解的话，复杂度会超时，因此用双端队列来解
     * 双端队列存储数组下标，队列头至队列尾，是严格的递减关系，逐步遍历数组，如果当前遍历到的元素大于
     * 队列尾的元素，则队列尾的元素出队，将当前元素push进队列尾，同时要注意，当前元素的下标与队列头的下标之差大于k时
     * 此时即说明队列头的元素已不在滑动窗口范围内，需要将其移除
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length < k || k==0) {
            return new int[0];
        }
        int[] result = new int[nums.length-k+1];
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            //淘汰末尾较小的元素
            while (!deque.isEmpty() && nums[deque.getLast()] < nums[i]) {
                deque.removeLast();
            }
            //将当前元素加入到序列中来
            deque.addLast(i);
            //淘汰超出滑动窗口的元素
            if(deque.getFirst() == i-k) {
                deque.removeFirst();
            }
            //赋值
            if(i >= k-1) {
                result[i-k+1] = nums[deque.getFirst()];
            }
        }
        return result;
    }
}

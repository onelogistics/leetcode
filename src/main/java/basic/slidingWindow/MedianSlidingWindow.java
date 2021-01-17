package basic.slidingWindow;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * leetcode480 Sliding Window Median
 * 解法2参考：https://leetcode.com/problems/sliding-window-median/discuss/96346/Java-using-two-Tree-Sets-O(n-logk)
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
 * <p>
 * Examples:
 * [2,3,4] , the median is 3
 * <p>
 * [2,3], the median is (2 + 3) / 2 = 2.5
 * <p>
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Your job is to output the median array for each window in the original array.
 * <p>
 * For example,
 * Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 * <p>
 * Window position                Median
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       1
 * 1 [3  -1  -3] 5  3  6  7       -1
 * 1  3 [-1  -3  5] 3  6  7       -1
 * 1  3  -1 [-3  5  3] 6  7       3
 * 1  3  -1  -3 [5  3  6] 7       5
 * 1  3  -1  -3  5 [3  6  7]      6
 * Therefore, return the median sliding window as [1,-1,-1,3,5,6].
 */
public class MedianSlidingWindow {
    public static void main(String[] args) {
        MedianSlidingWindow medianSlidingWindow = new MedianSlidingWindow();
        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        System.out.println(Arrays.toString(medianSlidingWindow.medianSlidingWindow(nums, 3)));

    }

    /**
     * 使用堆求解
     * 创建一个大顶堆和一个小顶堆，将滑动窗口范围内的元素推入堆中，并保证两个堆中的元素数目相差不超过1
     * 这样如果当前滑动窗口内的元素数量是偶数，两个堆顶的元素均值就是结果
     * 如果是奇数，存放元素较多的那个堆，堆顶元素就是结果
     *
     * @param nums
     * @param k
     * @return
     */
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];
        //存放较大的一批元素
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        //存放较小的一批元素
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < nums.length; i++) {
            maxHeap.offer(nums[i]);
            minHeap.offer(maxHeap.poll());
            if (minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
            if (i >= k - 1) {
                //由于上面代码中判断了当minHeap大于maxHeap时，会将maxHeap中的元素放入minHeap中，因此本处只考虑MinHeap小于MaxHeap的情况，而不考虑MinHeap大于MaxHeap的情况
                result[i - k + 1] = minHeap.size() < maxHeap.size() ? maxHeap.peek() : ((long)maxHeap.peek() + minHeap.peek()) * 0.5;
                //移动滑动窗口，删除旧元素
                if (maxHeap.contains(nums[i - k + 1])) {
                    maxHeap.remove(nums[i - k + 1]);
                } else {
                    minHeap.remove(nums[i - k + 1]);
                }
            }
        }
        return result;
    }

}

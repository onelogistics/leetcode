package com.ai.algorithm.search;

/**
 * 34. Find First and Last Position of Element in Sorted Array
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 * <p>
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * <p>
 * If the target is not found in the array, return [-1, -1].
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * 升序重复数组中，查找给定整数的左右边界
 *
 * @author JunjunYang
 * @date 2020/2/2 18:14
 */
public class SearchRange {
    public static void main(String[] args) {
        new SearchRange().searchRange(new int[]{1, 1, 2, 2, 2, 3, 4, 5}, 0);
    }

    /**
     * 基本思路：二分查找，在O(logN)的时间复杂度内ac
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int[] ans = {-1, -1};
        int leftIndex = find(nums, target, true);
        if (leftIndex == nums.length || nums[leftIndex] != target) {
            return ans;
        }
        ans[0] = leftIndex;
        ans[1] = find(nums, target, false) - 1;
        return ans;
    }

    /**
     * left为true时，返回第一个小于等于target的下标，left为false时，返回第一个大于等于target的下标。
     *
     * @param left 是否是查找左下标
     * @return
     */
    public int find(int[] nums, int target, boolean left) {
        int lo = 0;
        int hi = nums.length;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] > target || (left && nums[mid] == target)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }
}

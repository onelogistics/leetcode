package basic.search;

import org.junit.Test;

import java.util.Arrays;

/**二分查找有几种写法？它们的区别是什么？ - labuladong的回答 - 知乎
 https://www.zhihu.com/question/36132386/answer/712269942
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
        System.out.println(Arrays.toString(new SearchRange().searchRange(new int[]{1, 1, 2, 2, 2, 3, 4, 5}, 0)));
        System.out.println(Arrays.toString(new SearchRange().searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8)));
    }
    @Test
    public void test() {
        System.out.println(new SearchRange().search(new int[]{5}, 6));
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
        //查找出来后要再进行一次边界条件的补充判断。
        if (leftIndex == nums.length || nums[leftIndex] != target) {
            return ans;
        }
        ans[0] = leftIndex;
        //之所以要减1是因为我们在nums[mid] == target时，对left赋值为mid+1，所以nums[left]肯定不等于mid
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

    /**
     * 最基础的二分查找
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums,int target) {
        int lo=0;
        int hi=nums.length;
        while(lo<hi) {
            int mid=lo+(hi-lo)/2;
            if(nums[mid]==target) {
                return mid;
            }else if(nums[mid]>target) {
                hi=mid;
            }else{
                lo=mid+1;
            }
        }
        return -1;
    }
}

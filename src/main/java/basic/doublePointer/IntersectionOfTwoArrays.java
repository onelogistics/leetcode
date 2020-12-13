package basic.doublePointer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/intersection-of-two-arrays/
 * 找出两个数组的交集
 * 349. Intersection of Two Arrays
 * Given two arrays, write a function to compute their intersection.
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2]
 * Example 2:
 *
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [9,4]
 * Note:
 *
 * Each element in the result must be unique.
 * The result can be in any order.
 */
public class IntersectionOfTwoArrays {

    /**时间复杂度O(N)
     * 使用hashset的解法
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> nums1Set = new HashSet<>();
        Set<Integer> intersectionSet = new HashSet<>();
        for (int n : nums1) {
            nums1Set.add(n);
        }
        for (int n : nums2) {
            if(nums1Set.contains(n)) {
                intersectionSet.add(n);
            }
        }
        int[] ans = new int[intersectionSet.size()];
        int i = 0;
        for (Integer n: intersectionSet) {
            ans[i++] = n;
        }
        return ans;
    }

    /**
     * 时间复杂度O（N*logN）
     * 双指针解法
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        Set<Integer> intersectionSet = new HashSet<>();
        int i=0,j=0;
        while (i<nums1.length && j<nums2.length) {
            if(nums1[i] < nums2[j]) {
                i++;
            }else if(nums1[i] > nums2[j]) {
                j++;
            }else {
                intersectionSet.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] ans = new int[intersectionSet.size()];
        int k = 0;
        for (Integer n: intersectionSet) {
            ans[k++] = n;
        }
        return ans;
    }

    /**
     * 二分搜索解法
     * 时间复杂度O(N*logN)
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection3(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Set<Integer> set=new HashSet<>();
        for (int num: nums2) {
            if(binarySearch(nums1,num)) {
                set.add(num);
            }
        }
        int[] ans = new int[set.size()];
        int i=0;
        for (int n: set) {
            ans[i++]=n;
        }
        return ans;
    }

    /**
     * 查找target是否在nums中出现过，出现过返回true，未出现过返回false
     * @param nums
     * @param target
     * @return
     */
    public boolean binarySearch(int[] nums, int target) {
        int lo=0;
        int ho=nums.length;
        while (lo < ho) {
            int mid = lo + (ho-lo)/2;
            if(nums[mid] == target) {
                return true;
            }else if(nums[mid]<target) {
                lo=mid+1;
            }else {
                ho=mid;
            }
        }
        return false;
    }

}

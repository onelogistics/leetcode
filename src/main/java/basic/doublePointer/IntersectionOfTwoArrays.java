package basic.doublePointer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
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
    /**
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
}

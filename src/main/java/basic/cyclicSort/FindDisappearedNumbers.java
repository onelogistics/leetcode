package basic.cyclicSort;

import java.util.ArrayList;
import java.util.List;

/**
 * 448. Find All Numbers Disappeared in an Array
 * 给定一个int数组，1 <= a[i] <= n，其中有些元素出现了两次，有些出现了一次
 * 找出[1,n]中所有没在数组中出现的元素
 * Example:
 *
 * Input:
 * [4,3,2,7,8,2,3,1]
 *
 * Output:
 * [5,6]
 */
public class FindDisappearedNumbers {
    public static void main(String[] args) {
        System.out.println(findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
    }
    /**
     * 基本思想，建立 i+1 = nums[i]的映射关系，重复的元素直接不动
     * 时间复杂度：O(N)
     * @param nums
     * @return
     */
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        for (int i=0;i<nums.length;) {
            //当前元素不满足i+1的关系并且nums[i]尚未出现在合适的位置
            if(nums[i] != i+1 && nums[nums[i]-1] != nums[i]) {
                int temp =nums[i];
                nums[i] =nums[temp-1];
                nums[temp - 1] =temp;
            } else {
                i++;
            }
        }
        for (int i=0;i<nums.length;i++) {
            if(i+1 != nums[i]) {
                ans.add(i+1);
            }
        }
        return ans;
    }

    /**
     * 基本思路：数组中的元素都在[1,n]范围内，目的是找出所有缺失的数字
     * 那我们可以标记出所以已经出现过的数字，然后遍历数组，没有被标记的数字下标，就是缺失的
     * 时间复杂度：O(N)
     * @param nums
     * @return
     */
    public static List<Integer> findDisappearedNumbers2(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        for (int num: nums) {
            //所有出现过的元素，其对应下标的元素值都大于nums.length
            //此处之所以要求余，是因为第一次加和之后，值可能大于nums.length
            nums[(num-1) % nums.length] +=nums.length;
        }
        for (int i=0;i<nums.length;i++) {
            if(nums[i] <= nums.length) ans.add(i+1);
        }
        return ans;
    }


}

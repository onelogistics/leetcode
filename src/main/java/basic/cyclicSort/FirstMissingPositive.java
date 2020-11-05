package basic.cyclicSort;

import org.junit.Assert;
import org.junit.Test;

/**
 * leetcode 041  hard 第一个缺失的正整数
 * Given an unsorted integer array, find the smallest missing positive integer.
 * Input: [1,2,0]
 * Output: 3
 * Input: [3,4,-1,1]
 * Output: 2
 * Input: [7,8,9,11,12]
 * Output: 1
 *
 * @author JunjunYang
 * @date 2020/1/10 9:00
 */
public class FirstMissingPositive {
    @Test
    public void test() {
        int[] nums=new int[]{3,4,-1,1};
        Assert.assertEquals(2,soultion(nums));
    }

    /**
     * 题目要求用O(N)的时间复杂度，O(1)的空间复杂度
     * 如果是有序数组，我们可以通过判断nums[i]-nums[i-1]是否等于1快速找出答案
     * 但现在是无序重复数组，长度为len的数组，其第一个缺失的正整数一定在[1,length+1]之间，其他的数据可以不用关心(越界数据不做处理)
     * 那么我们可以通过为数组下标和值建立映射关系(nums[i] == nums[nums[i] - 1])，来找出第一个映射关系不正确的下标，即为我们要找的答案
     * index ： 0 1 2
     * value:   1 2 3
     *
     *
     * @param nums
     * @return
     */
    public  int soultion(int[] nums) {
        for (int i = 0; i < nums.length;) {
            //数组未越界且数组需要交换到相应下标位置
            //每个元素只会被移动到正确的位置一次，因此这个for循环的时间复杂度是O(n+n)
            if (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]) {
                //暂存临时值，交换元素
                int temp = nums[i];
                nums[i] = nums[temp - 1];
                nums[temp - 1] = temp;
            } else {
                //当前位置的值已放到正确位置或者越出了范围
                i++;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

}

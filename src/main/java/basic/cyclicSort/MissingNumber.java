package basic.cyclicSort;

/**leetcode268
 * 给定一个数组，数组中包含互不重复的元素，元素为0~n范围内的数字，求该数组中缺失的数。
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 * Input: [3,0,1]
 * Output: 2
 * Input: [9,6,4,2,3,5,7,0,1]
 * Output: 8
 *
 * @author JunjunYang
 * @date 2020/1/10 9:36
 */
public class MissingNumber {
    /**
     * O(N) O(1)
     * 位处理方案，利用位异或的特性(相同比特值异或得0，不同比特值异或得1)，A异或A得到0，A异或0得到A
     * value的取值范围为0~n的可能性
     * 下标为0~n-1，我们额外为下标补上n，则下标范围为0~n
     * 对下标和value取异或，则最终结果即为缺失的那个值(异或满足交换律、结合律)
     *
     * @param nums
     * @return
     */
    public int bitManipulation(int[] nums) {
        int miss = nums.length;
        for (int i = 0; i < nums.length; i++) {
            miss = miss ^ i ^ nums[i];
        }
        return miss;
    }

    /**
     * 利用高斯求和公式
     * O(N)O(1) 有数值溢出的风险
     *
     * @param nums
     * @return
     */
    public int guassFormula(int[] nums) {
        int expectSum = nums.length * (nums.length + 1) / 2;
        int actualSum = 0;
        for (int num : nums) {
            actualSum += num;
        }
        return expectSum - actualSum;
    }
}

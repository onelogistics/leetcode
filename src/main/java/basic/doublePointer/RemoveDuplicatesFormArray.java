package basic.doublePointer;

/**
 * leetcode26 && leetcode80
 */
public class RemoveDuplicatesFormArray {
    public static void main(String[] args) {
        RemoveDuplicatesFormArray removeDuplicatesFormArray = new RemoveDuplicatesFormArray();
        removeDuplicatesFormArray.removeDuplicates(new int[]{1, 1, 2});
    }

    /**
     * 26. Remove Duplicates from Sorted Array
     * 给定一个数组，原地删除重复元素，保证每个元素只出现一次并返回新的数组长度
     * Example 1:
     * <p>
     * Input: nums = [1,1,2]
     * Output: 2, nums = [1,2]
     * Explanation: Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. It doesn't matter what you leave beyond the returned length.
     * Example 2:
     * <p>
     * Input: nums = [0,0,1,1,1,2,2,3,3,4]
     * Output: 5, nums = [0,1,2,3,4]
     * Explanation: Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively. It doesn't matter what values are set beyond the returned length.
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null) return 0;
        if (nums.length <= 1) return nums.length;
        int d = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[d++] = nums[i];
            }
        }
        return d;
    }

    /**
     * 80. Remove Duplicates from Sorted Array II
     * 给定一个有序数组，去除多余元素，使得其中的每个元素最多出现两次
     * Example 1:
     * <p>
     * Given nums = [1,1,1,2,2,3],
     * <p>
     * Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
     * <p>
     * It doesn't matter what you leave beyond the returned length.
     * Example 2:
     * <p>
     * Given nums = [0,0,1,1,1,1,2,3,3],
     * <p>
     * Your function should return length = 7, with the first seven elements of nums being modified to 0, 0, 1, 1, 2, 3 and 3 respectively.
     * <p>
     * It doesn't matter what values are set beyond the returned length.
     *
     * @param nums
     * @return
     */
    public int removeDuplicates2(int[] nums) {
        int i = 0;
        for (int n : nums) {
            //n > nums[i - 2],如果连续有三个以上的重复元素，则不会进入到此循环中，因此可以保证i左侧的元素都是满足条件的。
            if (i < 2 || n > nums[i - 2]) {
                nums[i++] = n;
            }
        }
        return i;
    }

}

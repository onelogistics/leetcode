package basic.doublePointer;

import org.apache.commons.math3.stat.descriptive.summary.Sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 228. Summary Ranges
 * 给定一个有序且不重复的数组nums
 * 返回最小的有序范围list，这个有序范围list需要精确的覆盖所有元素
 * 也就是说，nums中的每个元素都被list中的一个范围唯一覆盖到了，并且不存在某个元素
 * 包含在list的范围内但不在nums内
 * <p>
 * "a->b" if a != b
 * "a" if a == b
 * Example 1:
 * <p>
 * Input: nums = [0,1,2,4,5,7]
 * Output: ["0->2","4->5","7"]
 * Explanation: The ranges are:
 * [0,2] --> "0->2"
 * [4,5] --> "4->5"
 * [7,7] --> "7"
 * Example 2:
 * <p>
 * Input: nums = [0,2,3,4,6,8,9]
 * Output: ["0","2->4","6","8->9"]
 * Explanation: The ranges are:
 * [0,0] --> "0"
 * [2,4] --> "2->4"
 * [6,6] --> "6"
 * [8,9] --> "8->9"
 * Example 3:
 * <p>
 * Input: nums = []
 * Output: []
 * Example 4:
 * <p>
 * Input: nums = [-1]
 * Output: ["-1"]
 * Example 5:
 * <p>
 * Input: nums = [0]
 * Output: ["0"]
 */
public class SummaryRanges {
    public static void main(String[] args) {
        SummaryRanges summaryRanges=new SummaryRanges();
        summaryRanges.summaryRanges(new int[]{0,1,2,4,5,7});
    }
    /**
     * 解题思路1
     * 两个指针，一个指向范围的头，一个指向范围的尾
     * 当有序数组中断时，移动指针
     *
     * @param nums
     * @return
     */
    //todo 这种解法边界条件难以处理，代码繁琐，只使用一个指针好处理
    public List<String> summaryRanges1(int[] nums) {
        if (nums.length == 0) return new ArrayList<>();
        if (nums.length == 1) return Arrays.asList(String.valueOf(nums[0]));

        List<String> ans = new ArrayList<>();
        int head = 0;
        int tail;
        for (tail = 0; tail < nums.length - 1; tail++) {
            if (nums[tail] + 1 != nums[tail + 1]) {
                if (nums[head] == nums[tail]) {
                    ans.add(String.valueOf(nums[head]));
                } else {
                    ans.add(nums[head] + "->" + nums[tail]);
                }
                head = tail + 1;
            }
        }
        if (nums[head] == nums[tail]) {
            ans.add(String.valueOf(nums[head]));
        } else {
            ans.add(nums[head] + "->" + nums[tail]);
        }
        return ans;
    }

    /**
     * 解法2，使用单指针，不断右移指针，直到不满足移动条件为止
     * @param nums
     * @return
     */
    public List<String> summaryRanges(int[] nums) {
        if (nums.length == 1) return Arrays.asList(String.valueOf(nums[0]));
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int head = nums[i];
            while (i + 1 < nums.length && nums[i+1] - nums[i] == 1) {
                i++;
            }
            if (head != nums[i]) {
                ans.add(head + "->" + nums[i]);
            } else {
                ans.add(head + "");
            }
        }
        return ans;
    }
}

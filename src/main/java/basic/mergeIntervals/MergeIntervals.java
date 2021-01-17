package basic.mergeIntervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**56. Merge Intervals
 * 给定一个区间集合，合并所有重叠的区间
 * Example 1:
 *
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * Example 2:
 *
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 */
public class MergeIntervals {
    /**
     * 基本思路
     * 本题要求合并区间，先将区间按照起始下标排序，这样能保证起始下标小的总在前面
     * 只需要比较末尾下标元素和下一个元素的起始下标元素，如果末尾下标元素大于等于下一个元素的起始下标元素
     * 则这两者可以合并
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        if(intervals.length <= 1) return intervals;
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        List<int[]> ans = new ArrayList<>();
        int[] newIntervals = intervals[0];
        ans.add(newIntervals);
        for (int[] interval : intervals) {
            //两个区间重叠
            if(newIntervals[1] >= interval[0]) {
                newIntervals[1] = Math.max(newIntervals[1],interval[1]);
            }else {
                newIntervals = interval;
                ans.add(newIntervals);
            }
        }
        return ans.toArray(new int[ans.size()][]);
    }
}

package basic.mergeIntervals;

import java.util.ArrayList;
import java.util.List;

/**
 * 57. Insert Interval
 * 给定一个无重叠的区间集合，插入一条新区间到区间集合中（如果插入之后有重叠，合并区间）
 * 你可以假定给定的区间集合已经按照他们的起始下标排序好了
 * Example 1:
 *
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 * Example 2:
 *
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 * Example 3:
 *
 * Input: intervals = [], newInterval = [5,7]
 * Output: [[5,7]]
 * Example 4:
 *
 * Input: intervals = [[1,5]], newInterval = [2,3]
 * Output: [[1,5]]
 * Example 5:
 *
 * Input: intervals = [[1,5]], newInterval = [2,7]
 * Output: [[1,7]]
 */
public class InsertIntervals {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> ans =new ArrayList<>();
        int i = 0;
        //将与新区间无交集的区间加入进来
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            ans.add(intervals[i++]);
        }
        //与newInterval有重叠的区间
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(intervals[i][0],newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1],newInterval[1]);
            i++;
        }
        ans.add(newInterval);
        //将后半部分与newInterval无交集的元素加入进来
        while (i < intervals.length) {
            ans.add(intervals[i++]);
        }
        return ans.toArray(new int[ans.size()][]);
    }
}

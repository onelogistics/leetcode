package basic.mergeIntervals;

import java.util.ArrayList;
import java.util.List;

/**
 * 986. Interval List Intersections  区间列表相交
 * 给定两个由一些 闭区间 组成的列表，每个区间列表都是成对不相交的，并且已经排序。
 * <p>
 * 返回这两个区间列表的交集。
 * <p>
 * （形式上，闭区间 [a, b]（其中 a <= b）表示实数 x 的集合，而 a <= x <= b。两个闭区间的交集是一组实数，要么为空集，要么为闭区间。例如，[1, 3] 和 [2, 4] 的交集为 [2, 3]。）
 * 示例：
 * <p>
 * <p>
 * <p>
 * 输入：A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
 * 输出：[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/interval-list-intersections
 */
public class IntervalListIntersection {
    public static void main(String[] args) {
        IntervalListIntersection intervalListIntersection=new IntervalListIntersection();
        intervalListIntersection.intervalIntersection(new int[][]{{8,15}},new int[][]{{2,6},{8,10},{12,20}});

    }
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> ans = new ArrayList<>();
        int i = 0, j = 0;
        while (i < A.length && j < B.length) {
            //如果B[j][0]处在A[i]区间范围内，则有交织
            int lo=Math.max(B[j][0],A[i][0]);
            int hi=Math.min(A[i][1],B[j][1]);
            if(lo<=hi) {
                ans.add(new int[]{lo,hi});
            }
            if(A[i][1]<B[j][1]) { //移除尾元素较小的区间，以尾元素做判断，如果尾元素较小，说明此区间已经不可能再与另外一个列表的下一个区间相交，所以移除
                i++;
            } else {
                j++;
            }
        }
        return ans.toArray(new int[ans.size()][]);
    }
}

package basic.mergeIntervals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

/**715. Range Module 区间模块
 * 设计一个区间模块类，使其接口满足以下特性
 * addRange(int left, int right) ：添加一个左闭右开的区间[left, right)到区间模块中，如果和已有的区间模块有重复，合并重复区间
 * removeRange(int left, int right)：移除一个区间
 * queryRange(int left, int right)：查询给定的区间是否在已有区间内
 *
 * Example 1:
 * addRange(10, 20): null
 * removeRange(14, 16): null
 * queryRange(10, 14): true (Every number in [10, 14) is being tracked)
 * queryRange(13, 15): false (Numbers like 14, 14.03, 14.17 in [13, 15) are not being tracked)
 * queryRange(16, 17): true (The number 16 in [16, 17) is still being tracked, despite the remove operation)
 *
 *
 *
 * 使用TreeSet,有序数组
 * 按照右下标排序，以便新加入区间或删除区间时，确定待处理的集合
 */
public class RangeModule {
    TreeSet<Interval> ranges;
    public RangeModule() {
        ranges = new TreeSet<>();
    }

    /**
     * 时间复杂度 O(N)
     * @param left
     * @param right
     */
    public void addRange(int left, int right) {
        //筛选出所有右下标大于等于给定left的区间
        Iterator<Interval> iterator = ranges.tailSet(new Interval(0,left)).iterator();
        while (iterator.hasNext()) {
            //给定元素的左下标小于当前元素的右下标
            Interval interval=iterator.next();
            //给定元素的右下标，小于当前元素的左下标，无交集
            if(right < interval.left) break;
            //有交集时，取最大的范围集合，分别赋值left和right
            left = Math.min(left, interval.left);
            right = Math.max(right, interval.right);
            //删除当前区间，因为当前区间已经与给定区间合并
            iterator.remove();

        }
        ranges.add(new Interval(left,right));
    }
    public void removeRange(int left, int right) {
        //筛选出所有右下标大于等于给定left的区间,即有可能和给定区间有重复的区间
        Iterator<Interval> iterator = ranges.tailSet(new Interval(0,left)).iterator();
        List<Interval> list = new ArrayList<>();
        while (iterator.hasNext()) {
            //给定元素的左下标小于当前元素的右下标
            Interval interval=iterator.next();
            //给定元素的右下标，小于当前元素的左下标，无交集
            if(right < interval.left) break;
            //当前元素的左下标小于给定元素的左下标
            if(interval.left < left) list.add(new Interval(interval.left, left));
            //当前元素的右下标大于给定元素的右下标
            if(interval.right > right) list.add(new Interval(right, interval.right));
            iterator.remove();
        }
        for (Interval interval : list) {
            ranges.add(interval);
        }
    }

    public boolean queryRange(int left, int right) {
        Interval interval = ranges.higher(new Interval(0,left));
        return interval !=null && right <= interval.right && left >= interval.left;
    }


}
class Interval implements Comparable<Interval>{
    int left;
    int right;

    public Interval(int left, int right){
        this.left = left;
        this.right = right;
    }

    /**
     * 以尾右下标比较，右下标相等时，以左下标比较
     * @param that
     * @return
     */
    public int compareTo(Interval that){
        if (this.right == that.right) return this.left - that.left;
        return this.right - that.right;
    }
}

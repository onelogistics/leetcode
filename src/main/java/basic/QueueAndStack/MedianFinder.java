package basic.QueueAndStack;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * leetcode295 hard https://leetcode.com/problems/find-median-from-data-stream/
 * 设计一个数据结构以支持如下两种操作：
 * addNum()，将一个int值加到你的数据结构中
 * findMedian(), 返回迄今为止所有元素的中位数
 *
 *Example:
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 *
 * 注意：
 * 1、如果所有元素都在0和100之间，你要怎么优化算法？
 * 维护一个int[101]的数组，记录每个数字出现的次数以及总元素的数量，然后遍历数组找到中位数
 * 2、如果99%的元素在0到100之间，你要怎么优化算法？
 * 由于99%的元素都在0到100之间，因此可以确定中位数一定在0到100之间，我们只需要记录下不在0到100之间的元素个数
 * 然后在计算中位数时减去他们即可
 */
public class MedianFinder {
    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
    }

    /**
     * 此问题的难点在于如何平衡这两棵树
     */
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

    /** initialize your data structure here. */
    public MedianFinder() {

    }

    public void addNum(int num) {

        //先将num直接加入maxHeap,这样maxHeap中就多了一个元素，我们需要平衡，所以把maxHeap的栈顶元素吐出
        //给minHeap,这样做一方面可以保持平衡，另一方面可以保证minHeap中总能取到较大值
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());

        //在上一步操作做完后，MinHeap中有可能保存了更多的元素，需要把MinHeap中的较小元素，平衡给MaxHeap
        if(maxHeap.size()<minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
       return minHeap.size() != maxHeap.size() ? maxHeap.peek() :(minHeap.peek()+maxHeap.peek())/2.0;
    }
}

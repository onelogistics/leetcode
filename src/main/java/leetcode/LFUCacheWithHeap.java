package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 实现方案：哈希+最小堆
 */
public class LFUCacheWithHeap {
    /**
     * 全局计数器，每多一次访问加1，并将此值赋给相应的Node.index,这样index越大的Node就是越近访问过得Node
     */
    private long globalIndex = 0;
    /**
     * 最大容量
     */
    private long size;
    private Map<Integer, Node> cache;
    private PriorityQueue<Node> priorityQueue;

    public LFUCacheWithHeap(int capacity) {
        size = capacity;
        cache = new HashMap<>();
        priorityQueue = new PriorityQueue<>();
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        node.frequency += 1;
        node.index = globalIndex++;
        //重排序
        priorityQueue.remove(node);
        priorityQueue.offer(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (size <= 0) {
            return;
        }
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            node.frequency += 1;
            node.index = globalIndex++;
            //重排序
            priorityQueue.remove(node);
            priorityQueue.offer(node);
        } else {
            if (cache.size() >= size) {
                Node node = priorityQueue.poll();
                cache.remove(node.key);
            }
            Node node = new Node(key, value, 1, globalIndex++);
            cache.put(key, node);
            priorityQueue.offer(node);
        }
    }

    class Node implements Comparable<Node> {
        private int key;
        private int value;
        private int frequency;
        private long index;

        public Node(int key, int value, int frequency, long index) {
            this.key = key;
            this.value = value;
            this.frequency = frequency;
            this.index = index;
        }

        /**
         * 按照frequency升序排序，frequency相同时，按照index升序排序
         *
         * @param o
         * @return
         */
        @Override
        public int compareTo(Node o) {
            int compare = Integer.compare(this.frequency, o.frequency);
            if (compare == 0) {
                return Long.compare(this.index, o.index);
            } else {
                return compare;
            }
        }
    }
}

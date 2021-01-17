package leetcode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * 460
 * Least Frequently Used
 * 请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。
 * <p>
 * 实现 LFUCache 类：
 * 基本思想：把数据加入到链表中，按频次排序，一个数据被访问过，把它的频次+1，发生淘汰的时候，把频次低的淘汰掉。
 * 比如有数据 1，1，1，2，2，3
 * 缓存中有（1(3次)，2(2次)）
 * 当3加入的时候，得把后面的2淘汰，变成（1(3次)，3(1次)）
 * <p>
 * LFUCache(int capacity) - 用数据结构的容量 capacity 初始化对象
 * int get(int key) - 如果键存在于缓存中，则获取键的值，否则返回 -1。
 * void put(int key, int value) - 如果键已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量时，则应该在插入新项之前，使最不经常使用的项无效。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最久未使用 的键。
 * 注意「项的使用次数」就是自插入该项以来对其调用 get 和 put 函数的次数之和。使用次数会在对应项被移除后置为 0 。
 * <p>
 * 双哈希表
 */
public class LFUCache {
    private int size;
    private int minFrequency;
    private Map<Integer, Node> cache = new HashMap<>();
    //访问频次和节点的对应关系
    private Map<Integer, LinkedList<Node>> freMap = new HashMap<>();

    public LFUCache(int capacity) {
        size = capacity;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (null == node) {
            return -1;
        }
        adjustFre(node);
        return node.value;

    }

    public void put(int key, int value) {
        if (size <= 0) {
            return;
        }
        //已存在key
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            adjustFre(node);
        } else {
            eliminate();
            Node node = new Node(key, value, 1);
            minFrequency = 1;
            cache.put(key, node);
            //加入到频次哈希表中
            if (!freMap.containsKey(node.frequency)) {
                freMap.put(node.frequency, new LinkedList<Node>());
            }
            freMap.get(node.frequency).add(node);
        }
    }

    //清除需要淘汰的缓存
    private void eliminate() {
        while (cache.size() >= size) {
            Iterator<Node> iterator = freMap.get(minFrequency).iterator();
            Node node = iterator.next();
            iterator.remove();
            cache.remove(node.key);
        }
    }

    /**
     * 调整此节点的频次
     *
     * @param node
     */
    private void adjustFre(Node node) {
        LinkedList<Node> nodes = freMap.get(node.frequency);
        nodes.remove(node);
        //最小频次的数量已空
        if (minFrequency == node.frequency && nodes.isEmpty()) {
            //因为下面有node..frequency++这一步，所以一定存在频次为minFrequency++的元素
            minFrequency++;
        }
        node.frequency++;
        if (!freMap.containsKey(node.frequency)) {
            freMap.put(node.frequency, new LinkedList<Node>());
        }
        freMap.get(node.frequency).add(node);
    }

    class Node {
        int key;
        int value;
        int frequency;

        public Node() {
        }

        ;

        public Node(int key, int value, int frequency) {
            this.key = key;
            this.value = value;
            this.frequency = frequency;
        }
    }
}
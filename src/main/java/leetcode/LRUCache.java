package leetcode;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * 164
 * Least Recently Used
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
 * 实现 LRUCache 类：
 * <p>
 * LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *  
 * <p>
 * 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * 哈希表+双端链表
 */
class DoubleLinkedNode<K, V> {
    K key;
    V value;
    DoubleLinkedNode pre;
    DoubleLinkedNode post;
}

public class LRUCache<K, V> {
    /**
     * 缓存的Map
     */
    private Map<K, DoubleLinkedNode> cache;
    /**
     * LRU缓存的最大容量
     */
    private int capacity;
    /**
     * LRU缓存的实际容量
     */
    private int count;
    private DoubleLinkedNode head, tail;

    public LRUCache(int capacity) {
        cache = new ConcurrentHashMap<>(capacity * 4 / 3);
        this.capacity = capacity;
        this.count = 0;
        head = new DoubleLinkedNode();
        tail = new DoubleLinkedNode();
        head.post = tail;
        tail.pre = head;
    }

    public V get(K key) {
        DoubleLinkedNode doubleLinkedNode = cache.get(key);
        if (doubleLinkedNode == null) {
            return null;
        }
        moveToHead(doubleLinkedNode);
        return (V) doubleLinkedNode.value;
    }

    public V set(K key, V value) {
        DoubleLinkedNode doubleLinkedNode = cache.get(key);
        if (doubleLinkedNode == null) {
            DoubleLinkedNode node = new DoubleLinkedNode();
            node.key = key;
            node.value = value;
            addNode(node);
            cache.put(key, node);
            count++;
            if (count > capacity) {
                DoubleLinkedNode tail = pop();
                cache.remove(tail.key);
                count--;
            }
            return null;
        } else {
            V oldValue = (V) doubleLinkedNode.value;
            doubleLinkedNode.value = value;
            moveToHead(doubleLinkedNode);
            return oldValue;
        }
    }

    /**
     * 加入一个节点到双向链表中
     *
     * @param node
     */
    public void addNode(DoubleLinkedNode node) {
        node.pre = head;
        node.post = head.post;
        head.post.pre = node;
        head.post = node;
    }

    /**
     * 删除双向链表中指定节点
     *
     * @param node
     */
    public void removeNode(DoubleLinkedNode node) {
        node.pre.post = node.post;
        node.post.pre = node.pre;
    }

    public void moveToHead(DoubleLinkedNode node) {
        removeNode(node);
        addNode(node);
    }

    /**
     * 淘汰节点
     */
    public DoubleLinkedNode pop() {
        DoubleLinkedNode res = tail.pre;
        removeNode(res);
        return res;
    }

}

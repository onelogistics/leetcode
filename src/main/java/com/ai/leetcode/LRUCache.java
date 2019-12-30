package com.ai.leetcode;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

package basic.search;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.TreeMap;

/**
 * leetcode 208
 * Trie树 也就是字典查找树，是一种能够实现在一个字符串集中实现快速查找和匹配的多叉树结构
 * 以空间换时间
 * Trie数的常见应用场景：
 * 1. 字符串检索
 * <p>
 * 2. 词频统计
 * <p>
 * 3. 前缀检索
 * <p>
 * 4.前缀词频统计
 * <p>
 * 5. 对所有的字符串按照字典序排序
 */
public class Trie {
    @Test
    public void test() {
        Trie trie = new Trie();
        trie.insert("apple");
        Assert.assertTrue(trie.search("apple"));
        Assert.assertFalse(trie.search("app"));
        Assert.assertTrue(trie.startWith("app"));
        trie.insert("app");
        Assert.assertTrue(trie.search("app"));
    }

    public TrieNode root = new TrieNode();

    /**
     * 插入词
     * @param word
     */
    public void insert(String word) {
        insert(word, root, 0);
    }

    public void insert(String word, TrieNode root, int index) {
        char c = word.charAt(index);
        if (root.children == null) {
            root.children = new TreeMap();
        }
        if (!root.children.containsKey(c)) {
            root.children.put(c, new TrieNode(c));
        }
        //字符串尾
        if (index == word.length() - 1) {
            root.children.get(c).count++;
            return;
        }
        insert(word, root.children.get(c), index + 1);
    }

    /**
     * 搜索词是否在trie树中
     * @param word
     * @return
     */
    public boolean search(String word) {
        return search(word, root, 0);
    }

    public boolean search(String word, TrieNode root, int index) {
        char c = word.charAt(index);
        if (root.children == null || !root.children.containsKey(c)) {
            return false;
        }
        if (index == word.length() - 1) {
            return root.children.get(c).count > 0;
        }
        return search(word, root.children.get(c), index + 1);
    }

    /**
     * 搜索是否存在指定前缀的词
     * @param prefix
     * @return
     */
    public boolean startWith(String prefix) {
        return startWith(prefix, root, 0);
    }

    public boolean startWith(String prefix, TrieNode root, int index) {
        char c = prefix.charAt(index);
        if (root.children == null || !root.children.containsKey(c)) {
            return false;
        }
        if (index == prefix.length() - 1) {
            return true;
        }
        return startWith(prefix, root.children.get(c), index + 1);
    }
    /**
     * 定义TrieNode节点
     */
    class TrieNode {
        //当前节点对应的字符
        char c;
        //以当前节点作为尾节点的字符串出现的次数
        int count = 0;
        //子节点
        TreeMap<Character, TrieNode> children;

        public TrieNode() {
        }

        public TrieNode(char c) {
            this.c = c;
        }
    }
}

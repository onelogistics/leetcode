package basic.search;

import basic.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode208
 */

public class Trie2 {
    TrieNode root;
    /**
     * 节点定义
     */
    class TrieNode{
        //前缀匹配此节点的字符串个数
        int prefixCount;
        //以此节点结尾的字符串个数
        int endCount;
        Map<Character,TrieNode> children = new HashMap<>();
    }
    /** Initialize your data structure here. */
    public Trie2() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            if(!cur.children.containsKey(c)) {
                cur.children.put(c,new TrieNode());
            }
            cur.prefixCount++;
            cur=cur.children.get(c);
        }
        cur.endCount++;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            if(!cur.children.containsKey(c)) {
                return false;
            }
            cur=cur.children.get(c);
        }
        return cur.endCount!=0;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        for (char c : prefix.toCharArray()) {
            if(!cur.children.containsKey(c)) {
                return false;
            }
            cur=cur.children.get(c);
        }
        return cur.prefixCount!=0 || cur.endCount!=0;
    }
}

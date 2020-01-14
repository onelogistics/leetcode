package com.ai.algorithm.search;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 实现一个类似于搜索引擎智能提示字符串的功能：用户输入一个字符串、给出所有前缀匹配此字符串的集合，且按照字符串搜索次数排序
 *
 * @author JunjunYang
 * @date 2020/1/14 21:16
 */
public class SmartTipsByPrefix {
    private static final Logger LOG = LoggerFactory.getLogger(SmartTipsByPrefix.class);

    public static void main(String[] args) {
        SmartTipsByPrefix smartTipsByPrefix = new SmartTipsByPrefix();
        System.out.println(smartTipsByPrefix.compareStr("abawe", "abacd", -1));
        new SmartTipsByPrefix().solution("aba", ImmutableMap.of("aba", 5, "bbb", 10, "abacd", 6, "wrer", 2, "abawe", 20), 2);

    }

    /**
     * 解决思路(也可以用trie树+topK来处理，不过trie树比较复杂)
     * 对于用户搜索次数使用map存储，用户搜索一个字符串、不存在则value设为1，存在则value+1，处理完所有字符串，时间复杂度为O(N)
     * 此处省略此步骤，直接作为入参输入。
     * 整体思路，先对字符串集合用快排字典序排序，排序结束后找出前缀匹配的首字符串下标和尾字符串下标，此范围内即为符合条件的字符串。
     * 然后使用最小堆依次输入符合条件的字符串及次数，找出次数最大的K个字符串，时间复杂度N*log(K)
     *
     * @param target
     * @param existingStr
     * @return
     */
    public List<String> solution(String target, Map<String, Integer> existingStr, int K) {
        String[] strings = existingStr.keySet().toArray(new String[existingStr.size()]);
        //排序
        quickSort(strings, 0, strings.length - 1);
        LOG.info("sorted array:{}", Arrays.toString(strings));
        int firstIndex = findFirstIndex(target, strings);
        int lastIndex = findLastIndex(target, strings, firstIndex);
        if (firstIndex == -1 || lastIndex == -1) {
            LOG.info("not find match string");
            return Collections.EMPTY_LIST;
        }
        LOG.info("firstIndex:{},lastIndex:{}", firstIndex, lastIndex);
        //小顶堆
        List<Tuple2> list = new ArrayList<>();
        for (int i = firstIndex; i <= lastIndex; i++) {
            list.add(new Tuple2(strings[i], existingStr.get(strings[i])));
        }
        PriorityQueue<Tuple2> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.t2));
        for (Tuple2 tuple2 : list) {
            if (priorityQueue.size() < K) {
                priorityQueue.add(tuple2);
            } else if (priorityQueue.peek().t2 < tuple2.t2) {
                priorityQueue.poll();
                priorityQueue.add(tuple2);
            }
        }
        List<String> result = new ArrayList<>();
        while (!priorityQueue.isEmpty()) {
            Tuple2 tuple2 = priorityQueue.poll();
            result.add(tuple2.t1);
        }
        result = result.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        LOG.info("result:{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 找到符合前缀匹配的首字符串下标
     * 如果当前字符串小于目标字符串，则向后继续二分查找
     * 如果当前字符串大于等于目标字符串，且当前下标-1的字符串小于目标字符串，则返回当前字符串下标，否则向前继续二分查找
     * 找不到返回-1
     *
     * @param target
     * @param str
     * @return
     */
    public int findFirstIndex(String target, String[] str) {
        int low = 0;
        int high = str.length - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            if (compareStr(str[mid], target, -1) < 0) {
                low = mid + 1;
            } else {
                if (mid == 0 || compareStr(str[mid - 1], target, -1) < 0) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 找到符合条件的最后一个字符串
     * 如果当前字符串的前n个字符与target相等且当前下标+1的字符前n个字符大于target，则返回当前下标，否则继续向后寻找
     * 如果当前字符串的前n个字符串大于target，向前寻找
     *
     * @param target
     * @param str
     * @param firstIndex
     * @return
     */
    public int findLastIndex(String target, String[] str, int firstIndex) {
        int low = firstIndex;
        int high = str.length - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            if (compareStr(str[mid], target, target.length()) > 0) {
                high = mid - 1;
            } else if (compareStr(str[mid], target, target.length()) == 0) {
                if (compareStr(str[mid + 1], target, target.length()) > 0) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    public void quickSort(String[] str, int left, int right) {
        if (left < right) {
            int pivot = partition(str, left, right);
            quickSort(str, left, pivot - 1);
            quickSort(str, pivot + 1, right);
        }
    }

    private int partition(String[] str, int left, int right) {
        String pivot = str[left];
        while (left < right) {
            //注意循环内还需要再判断一次left和right，避免right无限左移
            while (left < right && compareStr(str[right], pivot, -1) >= 0) right--;
            str[left] = str[right];
            while (left < right && compareStr(str[left], pivot, -1) < 0) left++;
            str[right] = str[left];
        }
        str[left] = pivot;
        return left;
    }

    /**
     * 字典序比较两个字符串,按照前len个字符比较字符串
     *
     * @param len len=-1时比较所有字符，len>0时比较前n个字符
     * @param s1
     * @param s2
     * @return s1>s2返回1，s1<s2返回-1，s1==s2返回0
     */
    private int compareStr(String s1, String s2, int len) {
        int i = 0;
        while (i < s1.length() && i < s2.length() && len != 0) {
            len--;
            if (s1.charAt(i) == s2.charAt(i)) {
                i++;
            } else if (s1.charAt(i) < s2.charAt(i)) {
                return -1;
            } else {
                return 1;
            }
        }
        if (len == 0) {
            return 0;
        }
        return s1.length() - s2.length();
    }

    static class Tuple2 {
        public Tuple2(String t1, int t2) {
            this.t1 = t1;
            this.t2 = t2;
        }

        public String t1;
        public int t2;
    }
}

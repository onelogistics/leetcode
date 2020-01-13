package com.ai.algorithm.search;

/**
 * 实现类似{@link String#indexOf(String)}
 * leetcode 28
 * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 *
 * @author JunjunYang
 * @date 2020/1/13 20:00
 */
public class IndexOfImpl {
    public static void main(String[] args) {
        System.out.println(new IndexOfImpl().solution("mississippi", "issipi"));

    }

    /**
     * 时间复杂度O(M*N)
     * 暴力搜索法
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int solution(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        /**
         * 最大需要比较的边界
         */
        int max = haystack.length() - needle.length();
        if (max < 0) {
            return -1;
        }
//        for (int i = 0; i < haystack.length(); i++) {
        for (int i = 0; i <= max; i++) {
            int cur = i;
            int j = 0;
            for (; j < needle.length() && cur < haystack.length(); j++) {
                if (haystack.charAt(cur++) != needle.charAt(j)) {
                    break;
                }
            }
            //匹配到needle字符串尾
            if (j == needle.length()) {
                return i;
            }
        }
        return -1;
    }
}

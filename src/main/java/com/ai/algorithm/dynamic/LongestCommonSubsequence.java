package com.ai.algorithm.dynamic;

/**
 * Given two strings text1 and text2, return the length of their longest common subsequence.
 * A subsequence of a string is a new string generated from the original string with some characters(can be none) deleted without changing the relative order of the remaining characters. (eg, "ace" is a subsequence of "abcde" while "aec" is not). A common subsequence of two strings is a subsequence that is common to both strings.
 * If there is no common subsequence, return 0.
 * https://blog.csdn.net/afei__/article/details/83153399
 * https://www.kancloud.cn/digest/pieces-algorithm/163624
 */
public class LongestCommonSubsequence {
    public int solution(String text1, String text2) {
        int[][] ans = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 0; i < text1.length(); i++) {
            for (int j = 0; j < text2.length(); j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    ans[i + 1][j + 1] = ans[i][j] + 1;
                } else {
                    //与最长子串不同，最长子序列的动态规划表中如果相应位置i和j不同时，当前位置需要取左上中较大的值
                    ans[i + 1][j + 1] = Math.max(ans[i][j + 1], ans[i + 1][j]);
                }
            }
        }
        return ans[text1.length()][text2.length()];
    }
}

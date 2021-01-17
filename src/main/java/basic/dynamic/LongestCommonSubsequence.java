package basic.dynamic;

/**
 * leetcode 1143. Longest Common Subsequence
 * https://leetcode.com/problems/longest-common-subsequence/
 * 给定两个string，返回这两个string的最长公共子序列
 * Example 1:
 *
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 * Example 2:
 *
 * Input: text1 = "abc", text2 = "abc"
 * Output: 3
 * Explanation: The longest common subsequence is "abc" and its length is 3.
 * Example 3:
 *
 * Input: text1 = "abc", text2 = "def"
 * Output: 0
 * Explanation: There is no such common subsequence, so the result is 0.
 *
 * Given two strings text1 and text2, return the length of their longest common subsequence.
 * A subsequence of a string is a new string generated from the original string with some characters(can be none) deleted without changing the relative order of the remaining characters. (eg, "ace" is a subsequence of "abcde" while "aec" is not). A common subsequence of two strings is a subsequence that is common to both strings.
 * If there is no common subsequence, return 0.
 * https://blog.csdn.net/afei__/article/details/83153399
 * https://www.kancloud.cn/digest/pieces-algorithm/163624
 */
public class LongestCommonSubsequence {
    static LongestCommonSubsequence main=new LongestCommonSubsequence();
    public static void main(String[] args) {
        System.out.println(main.solution("abcde","ace"));
    }
    public int solution(String text1, String text2) {
        int[][] ans = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 0; i < text1.length(); i++) {
            for (int j = 0; j < text2.length(); j++) {
                //当前遍历到的两个字符相等，取左上角的元素+1，而不能取左方和上方元素，这样可能会引起重复计算问题，比如text1="abcde",text2="acc"
                //之所以直接取左上角的元素+1作为最终结果，而不和左方上方元素进行最大值比较，是由于左方和上方元素，最多就比左上方元素大1，因此左上方元素+1就是这三者的最大值了
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
